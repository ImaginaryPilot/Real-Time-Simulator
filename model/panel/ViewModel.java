package nl.rug.oop.rts.model.panel;

import lombok.Getter;
import lombok.Setter;
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
@Setter
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
    private final int nodeWidth = 100;
    /**
     * The height of a node.
     */
    private final int nodeHeight = 100;
    /**
     * The width of an edge.
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
     * Whether the user is in create node mode or not.
     * True if the user is in create node mode, false if not.
     */
    private boolean createNodeMode;
    /**
     * Whether the user is in create edge mode or not.
     * True if the user is in create edge mode, false if not.
     */
    private boolean createEdgeMode;
    /**
     * The battle log of the current view.
     */
    private List<String> battleLog = new ArrayList<>();
    /**
     * The event log of the current view.
     */
    private List<String> eventLog = new ArrayList<>();

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
        this.createNodeMode = false;
        this.createEdgeMode = false;
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
        if (node == getSelectedNode()) {
            return;
        }
        this.selectedNode = node;
        this.selectedEdge = null;
        updateAllObservers();
    }

    /**
     * Set the selected edge.
     *
     * @param edge The edge that is selected.
     */
    public void setSelectedEdge(Edge edge) {
        if (edge == getSelectedEdge()) {
            return;
        }
        this.selectedEdge = edge;
        this.selectedNode = null;
        updateAllObservers();
    }

    /**
     * Toggles the "create node" mode.
     */
    public void toggleCreateNodeMode() {
        this.createNodeMode = !this.createNodeMode;
        updateAllObservers();
    }

    /**
     * Toggles the "create edge" mode.
     */
    public void toggleCreateEdgeMode() {
        this.createEdgeMode = !this.createEdgeMode;
        updateAllObservers();
    }

    /**
     * Sets the "create node" mode.
     *
     * @param createNodeMode What "create node" mode to set. True if the user is in create node mode, false if not.
     */
    public void setCreateNodeMode(boolean createNodeMode) {
        this.createNodeMode = createNodeMode;
        updateAllObservers();
    }

    /**
     * Sets the "create edge" mode.
     *
     * @param createEdgeMode What "create edge" mode to set. True if the user is in create edge mode, false if not.
     */
    public void setCreateEdgeMode(boolean createEdgeMode) {
        this.createEdgeMode = createEdgeMode;
        updateAllObservers();
    }

    /**
     * Set the battleLog.
     *
     * @param newLog the new battleLog
     */
    public void setBattleLog(List<String> newLog) {
        this.battleLog = newLog;
        updateAllObservers();
    }

    /**
     * Set the eventLog.
     *
     * @param newLog the new eventLog
     */
    public void setEventLog(List<String> newLog) {
        this.eventLog = newLog;
        updateAllObservers();
    }
}
