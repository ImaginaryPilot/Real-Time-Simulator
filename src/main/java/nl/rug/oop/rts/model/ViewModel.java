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
    public ViewModel() {
        this.viewX = 0;
        this.viewY = 0;
        this.selectedNode = null;
        this.observers = new ArrayList<>();
    }

}
