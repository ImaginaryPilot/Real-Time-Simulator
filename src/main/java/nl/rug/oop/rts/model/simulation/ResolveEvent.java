package nl.rug.oop.rts.model.simulation;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.events.Disaster;
import nl.rug.oop.rts.model.events.Event;
import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.events.Reinforcements;
import nl.rug.oop.rts.model.events.Weaponry;

import javax.swing.*;
import java.util.List;

/**
 * Class for displaying information about a triggered event.
 */
@AllArgsConstructor
public class ResolveEvent extends JPanel {
    /**
     * The list of events that have occurred.
     */
    private List<String> eventLog;

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
}
