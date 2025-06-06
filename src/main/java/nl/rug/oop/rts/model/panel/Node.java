package nl.rug.oop.rts.model.panel;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.events.Event;
import nl.rug.oop.rts.model.interfaces.Renamable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Node class that holds all the information of a node in the graph.
 */
@Getter
@Setter
public class Node implements Renamable {
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
    private String name;
    /**
     * The armies on the node.
     */
    private List<Army> armyList = new ArrayList<>();
    /**
     * List of events on the node.
     */
    private List<Event> events = new ArrayList<>();

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

    /**
     * Add an army to the armyList.
     *
     * @param army the army
     */
    public void addArmy(Army army) {
        armyList.add(army);
    }

    /**
     * Remove an army from the armyList using index.
     *
     * @param index where army lies
     */
    public void removeArmy(int index) {
        armyList.remove(index);
    }

    /**
     * Remove an army from the armyList using an army object.
     *
     * @param army the army
     */
    public void removeArmy(Army army) {
        armyList.remove(army);
    }

    /**
     * Make a copy of the armies stored in the node.
     *
     * @return armyCopy, the copy of the army
     */
    public List<Army> copyArmies() {
        List<Army> armyCopy = new ArrayList<>();

        for (Army army : armyList) {
            armyCopy.add(army.copy());
        }

        return armyCopy;
    }

    /**
     * Clear the current army and set it to the new one.
     *
     * @param newArmy the army to replace with
     */
    public void setArmies(List<Army> newArmy) {
        armyList.clear();
        armyList.addAll(newArmy);
    }

    /**
     * Add an event on the node.
     *
     * @param event event added.
     */
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Remove an event from the node.
     *
     * @param event event removed.
     */
    public void removeEvent(Event event) {
        events.remove(event);
    }
}
