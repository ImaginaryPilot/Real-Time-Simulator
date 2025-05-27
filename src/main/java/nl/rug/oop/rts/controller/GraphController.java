package nl.rug.oop.rts.controller;

import lombok.RequiredArgsConstructor;
import nl.rug.oop.rts.controller.commands.*;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;

import java.util.List;

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
     * @param x X coordinate of the node.
     * @param y Y coordinate of the node.
     */
    public Node addNode(int x, int y) {
        int id = graphModel.createNodeId();
        String name = "Node " + id;
        Node node = new Node(id, name, x, y);
        Command command = new AddNodeCommand(graphModel, node);
        mainController.createCommand(command);
        return node;
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
    public Edge addEdge(Node nodeA, Node nodeB) {
        int id = graphModel.createEdgeId();
        Edge edge = new Edge(id, nodeA, nodeB);
        Command command = new AddEdgeCommand(graphModel, edge);
        mainController.createCommand(command);
        return edge;
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

    public boolean existEdge(Node nodeA, Node nodeB) {
        for (Edge edge : graphModel.getEdges()) {
            if (edge.getNodeA() == nodeA && edge.getNodeB() == nodeB) {
                return true;
            }
            if (edge.getNodeA() == nodeB && edge.getNodeB() == nodeA) {
                return true;
            }
        }
        return false;
    }

    /**
     * Generates a test graph.
     */
    public void generateTest() {

        addNode(100, 100);
        addNode(200, 200);
        addNode(300, 100);
        addNode(200, 500);
        addNode(500, 500);

        List Nodes = graphModel.getNodes();
        addEdge((Node) Nodes.get(0), (Node) Nodes.get(1));
        addEdge((Node) Nodes.get(0), (Node) Nodes.get(2));
        addEdge((Node) Nodes.get(0), (Node) Nodes.get(3));
        addEdge((Node) Nodes.get(1), (Node) Nodes.get(2));
        addEdge((Node) Nodes.get(1), (Node) Nodes.get(3));
        addEdge((Node) Nodes.get(4), (Node) Nodes.get(3));
    }
}