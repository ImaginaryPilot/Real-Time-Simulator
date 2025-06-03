package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.controller.commands.Command;
import nl.rug.oop.rts.controller.commands.MoveMapCommand;
import nl.rug.oop.rts.controller.commands.MoveNodeCommand;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;
import nl.rug.oop.rts.model.panel.ViewModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

/**
 * Mouse controller class.
 */
public class MouseController extends MouseAdapter {
    /**
     * The main controller of the game.
     */
    private final MainController mainController;
    /**
     * The graph controller of the game.
     */
    private final GraphController graphController;
    /**
     * The view of the game.
     */
    private final ViewModel viewModel;
    /**
     * The graph of the game.
     */
    private final GraphModel graphModel;
    /**
     * Whether the mouse is currently dragging.
     */
    private boolean dragging = false;
    /**
     * The node that is currently being clicked.
     */
    private Node clickedNode = null;

    private boolean movingNode = false;
    private boolean movingMap = false;

    /**
     * The last x coordinate of the mouse.
     * View coordinates.
     */
    private int lastX;
    /**
     * The last y coordinate of the mouse.
     * View coordinates.
     */
    private int lastY;

    /**
     * The start x coordinate of the mouse.
     * Used to create an undo/redo command.
     * Real coordinates.
     */
    private int startX;
    /**
     * The start y coordinate of the mouse.
     * Used to create an undo/redo command.
     * Real coordinates.
     */
    private int startY;

    private int startViewX;
    private int startViewY;

    /**
     * Constructor for the MouseController class.
     *
     * @param viewModel       The view of the game.
     * @param graphModel      The graph of the game.
     * @param graphController The graph controller.
     */
    public MouseController(
            ViewModel viewModel, GraphModel graphModel, GraphController graphController, MainController mainController) {
        this.viewModel = viewModel;
        this.graphModel = graphModel;
        this.graphController = graphController;
        this.mainController = mainController;
    }

    /**
     * Finds the node that is clicked.
     *
     * @param x The x coordinate of the mouse.
     * @param y The y coordinate of the mouse.
     * @return The node that is clicked. If no node is clicked, return null.
     */
    private Node clickedNode(int x, int y) {
        int realX = x - viewModel.getViewX();
        int realY = y - viewModel.getViewY();

        int nodeWidth = viewModel.getNodeWidth();
        int nodeHeight = viewModel.getNodeHeight();

        for (Node node : graphModel.getNodes()) {
            int left = node.getX() - nodeWidth / 2;
            int right = node.getX() + nodeWidth / 2;
            int top = node.getY() - nodeHeight / 2;
            int bottom = node.getY() + nodeHeight / 2;

            if (realX >= left && realX <= right && realY >= top && realY <= bottom) {
                return node;
            }
        }
        return null;
    }

    /**
     * Finds the edge that is clicked.
     *
     * @param x The x coordinate of the mouse.
     * @param y The y coordinate of the mouse.
     * @return The edge that is clicked. If no edge is clicked, return null.
     */
    private Edge clickedEdge(int x, int y) {
        int realX = x - viewModel.getViewX();
        int realY = y - viewModel.getViewY();

        int edgeWidth = viewModel.getEdgeWidth();

        for (Edge edge : graphModel.getEdges()) {
            int xA = edge.getNodeA().getX();
            int yA = edge.getNodeA().getY();
            int xB = edge.getNodeB().getX();
            int yB = edge.getNodeB().getY();

            double distance = Line2D.ptSegDist(xA, yA, xB, yB, realX, realY);
            if (distance <= edgeWidth) {
                return edge;
            }
        }
        return null;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        dragging = true;
        clickedNode = clickedNode(e.getX(), e.getY());
        if (clickedNode != null) {
            movingNode = true;
            movingMap = false; // Just to be sure
        } else {
            movingMap = true;
            movingNode = false; // Just to be sure
        }

        lastX = e.getX();
        lastY = e.getY();
        int realX = e.getX() - viewModel.getViewX();
        int realY = e.getY() - viewModel.getViewY();
        startX = realX;
        startY = realY;
        startViewX = e.getX();
        startViewY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragging = false;
        int x = e.getX();
        int y = e.getY();
        int realX = e.getX() - viewModel.getViewX();
        int realY = e.getY() - viewModel.getViewY();
        if (x != startViewX || y != startViewY) {
            if (movingNode) {
                Command moveNodeCommand = new MoveNodeCommand(clickedNode, startX, startY, realX, realY, graphModel);
                mainController.addCommand(moveNodeCommand);
            } else if (movingMap) {
                Command command = new MoveMapCommand(startX, startY, x, y, viewModel);
//                mainController.addCommand(command);
            }
        } else {
            System.out.println("not movign?");
            System.out.println("coordS:" + realX + " " + startX + "  Y: " + realY + "  " + startY);
            System.out.println("CODDDDOOORDS: " + e.getX() + " " + e.getY());
        }
        clickedNode = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("COOOORDS: " + e.getX() + " " + e.getY());
        int x = e.getX();
        int y = e.getY();
        int realX = e.getX() - viewModel.getViewX();
        int realY = e.getY() - viewModel.getViewY();
        if (viewModel.isCreateNodeMode()) {
            Node node = graphController.addNode(realX, realY);
            viewModel.setCreateNodeMode(false);
            viewModel.setSelectedNode(node);
            return;
        }
        if (viewModel.isCreateEdgeMode()) {
            Node node = clickedNode(x, y);
            if (node != null) {
                if (node != viewModel.getSelectedNode()) {
                    if (!graphModel.existEdge(viewModel.getSelectedNode(), node)) {
                        Edge edge = graphController.addEdge(node, viewModel.getSelectedNode());
                        viewModel.setSelectedEdge(edge);
                    }
                }
                viewModel.setCreateEdgeMode(false);
                return;
            }
            viewModel.setSelectedNode(null);
            viewModel.setCreateEdgeMode(false);
            return;

        }
        Node thisNode = clickedNode(x, y);
        if (thisNode == viewModel.getSelectedNode() && thisNode != null) {
            return;
        }
        viewModel.setSelectedNode(thisNode);
        if (viewModel.getSelectedNode() == null) {
            Edge edge = clickedEdge(x, y);
            viewModel.setSelectedEdge(clickedEdge(x, y));
        }
        dragging = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragging && clickedNode != null) { // move the node
            int realX = e.getX() - viewModel.getViewX();
            int realY = e.getY() - viewModel.getViewY();

            realX = Math.max(0, Math.min(realX, viewModel.getMapWidth()));
            realY = Math.max(0, Math.min(realY, viewModel.getMapHeight()));

            graphModel.setNodePosition(clickedNode, realX, realY);

        } else { // move the map
            int x = e.getX() - lastX;
            int y = e.getY() - lastY;
            int newX = viewModel.getViewX() + x;
            int newY = viewModel.getViewY() + y;

            int finalX = Math.max(-(viewModel.getMapWidth() - viewModel.getPanelWidth()), Math.min(0, newX));
            int finalY = Math.max(-(viewModel.getMapHeight() - viewModel.getPanelHeight()), Math.min(0, newY));
            viewModel.setViewPosition(finalX, finalY);

            lastX = e.getX();
            lastY = e.getY();
        }
    }

}
