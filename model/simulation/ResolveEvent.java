package nl.rug.oop.rts.model.simulation;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.model.events.Disaster;
import nl.rug.oop.rts.model.events.Event;
import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.events.Reinforcements;
import nl.rug.oop.rts.model.events.Weaponry;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.Node;

import java.util.List;
import java.util.Random;

/**
 * Class for displaying information about a triggered event.
 */
@Getter
@Setter
public class ResolveEvent {
    /**
     * The list of events that have occurred.
     */
    private List<String> eventLog;

    /**
     * Constructor for eventLog.
     *
     * @param eventLog list of events to log
     */
    public ResolveEvent(List<String> eventLog) {
        this.eventLog = eventLog;
    }

    /**
     * Displays a dialog showing information about a triggered event.
     *
     * @param event        the event that was triggered
     * @param army         the army affected by the event
     * @param locationName the location where the event occurred
     */
    public void displayEventDialog(Event event, Army army, String locationName) {

        String specificDetails = "";

        if (event instanceof Disaster) {
            specificDetails = "units removed: " + ((Disaster) event).getUnitsRemoved();
        } else if (event instanceof Reinforcements) {
            specificDetails = "units added: " + ((Reinforcements) event).getUnitsAdded();
        } else if (event instanceof Weaponry) {
            specificDetails = "damage increased by: " + ((Weaponry) event).getDamageIncrease();
        }

        eventLog.add("A " + event.getName() + " has occurred at " +
                locationName + "! " +
                army.getFaction() + " has its' " +
                specificDetails
        );
    }

    /**
     * Method that triggers random event.
     *
     * @param army   army affected.
     * @param random event number.
     * @param node   the node
     * @return the event.
     */
    public Event triggerRandomEventNode(Army army, Random random, Node node) {
        if (node.getEvents().isEmpty()) {
            return null;
        }
        int chance = random.nextInt(100);
        if (chance >= 50) {
            return null;
        }
        Event event = node.getEvents().get(random.nextInt(node.getEvents().size()));
        event.apply(army);
        return event;

    }

    /**
     * The function that triggers a random event on the edge.
     *
     * @param army   army affected.
     * @param random which event is chosen?
     * @param edge   the edge
     * @return event.
     */
    public Event triggerRandomEventEdge(Army army, Random random, Edge edge) {
        if (edge.getEvents().isEmpty()) {
            return null;
        }
        int chance = random.nextInt(100);
        if (chance >= 50) {
            return null;
        }
        Event event = edge.getEvents().get(random.nextInt(edge.getEvents().size()));
        event.apply(army);
        return event;
    }
}
