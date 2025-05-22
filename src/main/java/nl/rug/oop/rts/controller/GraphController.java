package nl.rug.oop.rts.controller;

import lombok.RequiredArgsConstructor;
import nl.rug.oop.rts.controller.commands.*;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.GraphModel;
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
     * The graph of the game.
     */
    private final GraphModel graphModel;

    /**
     * Adding a node to the graph.
     *
     * @param name Name of the node.
     * @param x    X coordinate of the node.
     * @param y    Y coordinate of the node.
     */
    public void addNode(String name, int x, int y) {
        int id = graphModel.createNodeId();
        Node node = new Node(id, name, x, y);
        Command command = new AddNodeCommand(graphModel, node);
        mainController.createCommand(command);
    }

    /**
     * Removing a node from the graph.
     *
     * @param node The node to remove.
     */
    public void removeNode(Node node) {
        Command command = new RemoveNodeCommand(graphModel, node);
        mainController.createCommand(command);
    }

    /**
     * Adding an edge to the graph.
     *
     * @param nodeA The first node connected to the edge.
     * @param nodeB The second node connected to this edge.
     */
    public void addEdge(Node nodeA, Node nodeB) {
        int id = graphModel.createEdgeId();
        Edge edge = new Edge(id, nodeA, nodeB);
        Command command = new AddEdgeCommand(graphModel, edge);
        mainController.createCommand(command);
    }

    /**
     * Removing an edge from the graph.
     *
     * @param edge The edge to remove.
     */
    public void removeEdge(Edge edge) {
        Command command = new RemoveEdgeCommand(graphModel, edge);
        mainController.createCommand(command);
    }

    /*
    Methods for "getting information" from the graph.
    >If mouse clicks, coordinates can be used to get the node/edge.

    >Moving nodes.
     */

}
