package nl.rug.oop.rts.model;

import lombok.Getter;
import nl.rug.oop.rts.observer.Observable;
import nl.rug.oop.rts.observer.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * View class that holds all the information of the current view of the game.
 * Location of the view, selected node.
 * <p>
 * Later, zoom level etc.
 */
@Getter
public class View implements Observable {
    /**
     * The width of the map.
     */
    private final int mapWidth = 2400;
    /**
     * The height of the map.
     */
    private final int mapHeight = 1400;
    /**
     * The width of the panel.
     */
    private final int panelWidth = 1200;
    /**
     * The height of the panel.
     */
    private final int panelHeight = 700;

    /**
     * The x coordinate of the view.
     */
    private int viewX;
    /**
     * The y coordinate of the view.
     */
    private int viewY;
    /**
     * The selected node.
     */
    private Node selectedNode;
    /**
     * List of all the observers of the view.
     */
    private List<Observer> observers;

    /**
     * Constructor for the View class.
     */
    public View() {
        this.viewX = 0;
        this.viewY = 0;
        this.selectedNode = null;
        this.observers = new ArrayList<>();
    }

    /*
    private void addTestData() {
        Node node1 = new Node(1, "Node 1");
        Node node2 = new Node(2, "Node 2");
        Node node3 = new Node(3, "Node 3");

        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);

        edges.add(new Edge(1, node1, node2));
        edges.add(new Edge(2, node2, node3));
        edges.add(new Edge(3, node1, node3));
    }
    */
}
