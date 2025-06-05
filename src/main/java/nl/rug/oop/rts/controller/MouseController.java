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
    /**
     * Whether the mouse is currently moving a node.
     */
    private boolean movingNode;
    /**
     * Whether the mouse is currently moving the map.
     */
    private boolean movingMap;

    /**
     * The last x coordinate of the mouse.
     * Used to move the map.
     * View coordinates.
     */
    private int lastX;
    /**
     * The last y coordinate of the mouse.
     * Used to move the map.
     * View coordinates.
     */
    private int lastY;

    /**
     * The start x coordinate of the mouse.
     * Used to create the move node undo/redo command.
     * Real coordinates.
     */
    private int startRealX;
    /**
     * The start y coordinate of the mouse.
     * Used to create the move node undo/redo command.
     * Real coordinates.
     */
    private int startRealY;

    /**
     * The start x coordinate of the mouse.
     * Used to create the move map undo/redo command.
     * View coordinates.
     */
    private int startViewX;
    /**
     * The start y coordinate of the mouse.
     * Used to create the move map undo/redo command.
     * View coordinates.
     */
    private int startViewY;

    /**
     * Constructor for the MouseController class.
     *
     * @param viewModel       The view of the game.
     * @param graphModel      The graph of the game.
     * @param graphController The graph controller.
     * @param mainController  The main controller.
     */
    public MouseController(
            ViewModel viewModel, GraphModel graphModel,
            GraphController graphController, MainController mainController) {
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
        movingNode = (clickedNode != null);
        movingMap = !movingNode;

        lastX = e.getX();
        lastY = e.getY();
        int realX = e.getX() - viewModel.getViewX();
        int realY = e.getY() - viewModel.getViewY();
        startRealX = realX;
        startRealY = realY;
        startViewX = viewModel.getViewX();
        startViewY = viewModel.getViewY();
    }

    /**
     * Used to create the corresponding commands if we have been dragging something.
     * Commands for undo/redo.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        dragging = false;
        int currentViewX = viewModel.getViewX();
        int currentViewY = viewModel.getViewY();
        if (movingNode) {
            if (currentViewX != clickedNode.getX() || currentViewY != clickedNode.getY()) {
                int realX = e.getX() - currentViewX;
                int realY = e.getY() - currentViewY;
                Command command = new MoveNodeCommand(clickedNode, startRealX, startRealY, realX, realY, graphModel);
                mainController.addCommand(command);
            }
        } else if (movingMap) {
            if (currentViewX != startViewX || currentViewY != startViewY) {
                int deltaX = currentViewX - startViewX;
                int deltaY = currentViewY - startViewY;
                Command command = new MoveMapCommand(deltaX, deltaY, viewModel);
                mainController.addCommand(command);
            }
        }
        clickedNode = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int realX = e.getX() - viewModel.getViewX();
        int realY = e.getY() - viewModel.getViewY();
        if (viewModel.isCreateNodeMode()) { // create a new node
            Node node = graphController.addNode(realX, realY);
            viewModel.setCreateNodeMode(false);
            viewModel.setSelectedNode(node);
            return;
        }
        if (viewModel.isCreateEdgeMode()) { // create a new edge
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
            viewModel.setSelectedEdge(clickedEdge(x, y));
        }
        dragging = false;
    }

    /**
     * Either drag a node, or the map.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragging && clickedNode != null) {
            moveNode(e.getX(), e.getY());
        } else {
            moveMap(e.getX(), e.getY());
        }
    }

    /**
     * Move the node.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    private void moveNode(int x, int y) {
        int realX = x - viewModel.getViewX();
        int realY = y - viewModel.getViewY();
        int halfNodeWidth = viewModel.getNodeWidth() / 2;
        int halfNodeHeight = viewModel.getNodeHeight() / 2;

        graphModel.setNodePosition(
                clickedNode,
                Math.max(halfNodeWidth, Math.min(realX, viewModel.getMapWidth() - halfNodeWidth)),
                Math.max(halfNodeHeight, Math.min(realY, viewModel.getMapHeight() - halfNodeHeight))
        );
    }

    /**
     * Move the map.
     *
     * @param latestX the x coordinate
     * @param latestY the y coordinate
     */
    private void moveMap(int latestX, int latestY) {
        int dx = latestX - lastX;
        int dy = latestY - lastY;

        viewModel.setViewPosition(
                Math.max(viewModel.getPanelWidth() - viewModel.getMapWidth(), Math.min(0, viewModel.getViewX() + dx)),
                Math.max(viewModel.getPanelHeight() - viewModel.getMapHeight(), Math.min(0, viewModel.getViewY() + dy))
        );
        lastX = latestX;
        lastY = latestY;
    }
}
