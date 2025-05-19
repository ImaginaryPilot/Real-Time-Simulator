package nl.rug.oop.rts.controller;

import lombok.RequiredArgsConstructor;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Node;

/**
 * Graph controller class.
 */
@RequiredArgsConstructor
public class GraphController {
    /**
     * The main controller of the game.
     */
    private final MainController mainController;

    /**
     * Adding a node to the graph.
     *
     * @param name Name of the node.
     * @param x    X coordinate of the node.
     * @param y    Y coordinate of the node.
     */
    public void addNode(String name, int x, int y) {
        int id = mainController.getGraph().createNodeId();
        Node node = new Node(id, name, x, y);
        /*
        call to maincontroller to addNode and add to command stack.
         */
        mainController.getGraph().updateAllObservers();
    }

    /**
     * Removing a node from the graph.
     *
     * @param node The node to remove.
     */
    public void removeNode(Node node) {
        /*
        call to maincontroller to removeNode and add to command stack.
         */
        mainController.getGraph().updateAllObservers();
    }

    /**
     * Adding an edge to the graph.
     *
     * @param nodeA The first node connected to the edge.
     * @param nodeB The second node connected to this edge.
     */
    public void addEdge(Node nodeA, Node nodeB) {
        int id = mainController.getGraph().createEdgeId();
        Edge edge = new Edge(id, nodeA, nodeB);
        /*
        call to maincontroller to addEdge and add to command stack.
         */
        mainController.getGraph().updateAllObservers();
    }

    /**
     * Removing an edge from the graph.
     *
     * @param edge The edge to remove.
     */
    public void removeEdge(Edge edge) {
        /*
        call to maincontroller to removeEdge and add to command stack.
         */
        mainController.getGraph().updateAllObservers();
    }

    /*
    Methods for "getting information" from the graph.
    >If mouse clicks, coordinates can be used to get the node/edge.

    >Moving nodes.
     */

}
