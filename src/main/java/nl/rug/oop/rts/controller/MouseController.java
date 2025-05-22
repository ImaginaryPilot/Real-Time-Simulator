package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.GraphModel;
import nl.rug.oop.rts.model.Node;
import nl.rug.oop.rts.model.ViewModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Mouse controller class.
 */
public class MouseController extends MouseAdapter {
    /**
     * The main controller of the game.
     */
    private final MainController mainController;
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
    public MouseController(MainController mainController, ViewModel viewModel, GraphModel graphModel) {
        this.mainController = mainController;
        this.viewModel = viewModel;
        this.graphModel = graphModel;

    }

    /**
     * Finds the node that is clicked.
     *
     * @param x The x coordinate of the mouse.
     * @param y The y coordinate of the mouse.
     * @return The node that is clicked. If no node is clicked, returns null.
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
        System.out.println("Mouse clicked: " + x + " " + y);

        viewModel.setSelectedNode(clickedNode(x, y));
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
