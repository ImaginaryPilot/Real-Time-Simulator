package nl.rug.oop.rts.model;

import lombok.Getter;
import lombok.Setter;


/**
 * Node class that holds all the information of a node in the graph.
 */
@Getter
@Setter
public class Node {
    /**
     * The x coordinate of the node.
     */
    private int x;
    /**
     * The y coordinate of the node.
     */
    private int y;
    /**
     * Unique identifier of the node.
     */
    private final int id;
    /**
     * Name of the node.
     */
    private final String name;

    /**
     * Constructor for the Node class.
     *
     * @param id   Unique identifier of the node.
     * @param name Name of the node.
     * @param x    X coordinate of the node.
     * @param y    Y coordinate of the node.
     */
    public Node(int id, String name, int x, int y) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.name = name;
    }
}
