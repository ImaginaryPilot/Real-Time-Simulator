package nl.rug.oop.rts.model.panel;

import lombok.Getter;
import lombok.Setter;

/**
 * Edge class that holds all the information of an edge in the graph.
 */
@Getter
@Setter
public class Edge {
    /**
     * Unique identifier of the edge.
     */
    private final int id;
    /**
     * The first node connected to the edge.
     */
    private final Node nodeA;
    /**
     * The second node connected to this edge.
     */
    private final Node nodeB;

    /**
     * The name of the edge.
     */
    private String name;

    /**
     * Constructor for the Edge class.
     *
     * @param id    The unique identifier of the edge.
     * @param nodeA The first node connected to the edge.
     * @param nodeB The second node connected to this edge.
     */
    public Edge(int id, Node nodeA, Node nodeB) {
        this.id = id;
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.name = "Edge " + id;
    }
}
