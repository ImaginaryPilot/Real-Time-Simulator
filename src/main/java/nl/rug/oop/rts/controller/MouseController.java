package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.GraphModel;
import nl.rug.oop.rts.model.Node;
import nl.rug.oop.rts.model.ViewModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

/**
 * Mouse controller class.
 */
public class MouseController extends MouseAdapter {
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
     * The last x coordinate of the mouse.
     */
    private int lastX;
    /**
     * The last y coordinate of the mouse.
     */
    private int lastY;

    /**
     * Constructor for the MouseController class.
     *
     * @param mainController The main controller of the game.
     * @param viewModel      The view of the game.
     * @param graphModel     The graph of the game.
     */
    public MouseController(MainController mainController, ViewModel viewModel, GraphModel graphModel, GraphController graphController) {
        this.viewModel = viewModel;
        this.graphModel = graphModel;
        this.graphController = graphController;

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
        lastX = e.getX();
        lastY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragging = false;
        clickedNode = null;
        /*
        send the full command to the mainController
         */
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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
                    if (!graphController.existEdge(viewModel.getSelectedNode(), node)) {
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
        viewModel.setSelectedNode(clickedNode(x, y));
        if (viewModel.getSelectedNode() == null) {
            viewModel.setSelectedEdge(clickedEdge(x, y));
        }
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
