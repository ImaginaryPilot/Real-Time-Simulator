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
public class ViewModel implements Observable {
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
     * The width of a node.
     */
    private final int nodeWidth = 60;
    /**
     * The height of a node.
     */
    private final int nodeHeight = 60;
    /**
     *
     */
    private final int edgeWidth = 5;
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
     * The selected edge.
     */
    private Edge selectedEdge;
    /**
     * List of all the observers of the view.
     */
    private List<Observer> observers;

    /**
     * Constructor for the View class.
     */
    public ViewModel() {
        this.viewX = 0;
        this.viewY = 0;
        this.selectedNode = null;
        this.selectedEdge = null;
        this.observers = new ArrayList<>();
    }

    /**
     * Adds an observer to the view.
     *
     * @param x The x coordinate of the new view.
     * @param y The y coordinate of the new view.
     */
    public void setViewPosition(int x, int y) {
        this.viewX = x;
        this.viewY = y;
        updateAllObservers();
    }

    /**
     * Set the selected node.
     *
     * @param node The node to set as selected.
     */
    public void setSelectedNode(Node node) {
        this.selectedNode = node;
        this.selectedEdge = null;
        updateAllObservers();
    }

    /**
     * Set the selected edge.
     *
     * @param edge The edge that is selected.
     */
    public void setSelectedEdge(Edge edge){
        this.selectedEdge = edge;
        this.selectedNode = null;
        updateAllObservers();
    }
}
