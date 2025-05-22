package nl.rug.oop.rts.model;

import lombok.Getter;
import nl.rug.oop.rts.observer.Observable;
import nl.rug.oop.rts.observer.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph class that holds all the nodes and edges of the graph.
 */
@Getter
public class GraphModel implements Observable {
    /**
     * List of all the nodes in the graph.
     */
    private List<Node> nodes;
    /**
     * List of all the edges in the graph.
     */
    private List<Edge> edges;
    /**
     * Counter for the unique identifier of a node.
     */
    private int nodeIdCounter;
    /**
     * Counter for the unique identifier of an edge.
     */
    private int edgeIdCounter;
    /**
     * List of all the observers of the graph.
     */
    private List<Observer> observers;

    /**
     * Constructor for the Graph class.
     */
    public GraphModel() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.nodeIdCounter = 0;
        this.edgeIdCounter = 0;
        this.observers = new ArrayList<>();
    }

    /**
     * Adds a node to the graph.
     *
     * @param node The node to add to the graph.
     */
    public void addNode(Node node) {
        nodes.add(node);
        updateAllObservers();
    }

    /**
     * Removes a node from the graph.
     *
     * @param node The node to remove from the graph.
     */
    public void removeNode(Node node) {
        /*
        Needs updating, should also remove the edge from the node on the other side)
         */
        node.getEdges().forEach(this::removeEdge);
        nodes.remove(node);
        updateAllObservers();
    }

    /**
     * Adds an edge to the graph.
     *
     * @param edge The edge to add to the graph.
     */
    public void addEdge(Edge edge) {
        edges.add(edge);
        updateAllObservers();
    }

    /**
     * Removes an edge from the graph.
     *
     * @param edge The edge to remove from the graph.
     */
    public void removeEdge(Edge edge) {
        edges.remove(edge);
        updateAllObservers();
    }

    /**
     * Creates a unique identifier for a node.
     *
     * @return The unique identifier of the node.
     */
    public int createNodeId() {
        nodeIdCounter++;
        return nodeIdCounter;
    }

    /**
     * Creates a unique identifier for an edge.
     *
     * @return The unique identifier of the edge.
     */
    public int createEdgeId() {
        edgeIdCounter++;
        return edgeIdCounter;
    }
}
