package nl.rug.oop.rts.model.panel;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.events.Event;
import nl.rug.oop.rts.model.interfaces.Renamable;

import java.util.ArrayList;
import java.util.List;

/**
 * Edge class that holds all the information of an edge in the graph.
 */
@Getter
@Setter
public class Edge implements Renamable {
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
     * The number of armies on the edge.
     */
    private List<Army> armyList = new ArrayList<>();
    /**
     * The list of events possible.
     */
    private List<Event> events = new ArrayList<>();

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

    /**
     * Add an army to the armyList.
     *
     * @param army the army
     */
    public void addArmy(Army army) {
        armyList.add(army);
    }

    /**
     * Remove an army from the armyList.
     *
     * @param army the army
     */
    public void removeArmy(Army army) {
        armyList.remove(army);
    }

    /**
     * Make a copy of the armies stored in the edge.
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
     * clear the current army and set it to the new one.
     *
     * @param newArmy the army to replace with
     */
    public void setArmies(List<Army> newArmy) {
        armyList.clear();
        armyList.addAll(newArmy);
    }

    /**
     * Method to add an event to the edge.
     *
     * @param event event added.
     */
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Method to remove an event from the edge.
     *
     * @param event removed event.
     */
    public void removeEvent(Event event) {
        events.remove(event);
    }
}
