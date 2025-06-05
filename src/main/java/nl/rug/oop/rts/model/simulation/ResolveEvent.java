package nl.rug.oop.rts.model.simulation;

import nl.rug.oop.rts.model.events.Disaster;
import nl.rug.oop.rts.model.events.Event;
import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.events.Reinforcements;
import nl.rug.oop.rts.model.events.Weaponry;

import javax.swing.*;

/**
 * Class for displaying information about a triggered event.
 */
public class ResolveEvent extends JPanel {
    /**
     * Displays a dialog showing information about a triggered event.
     *
     * @param event the event that was triggered
     * @param army the army affected by the event
     * @param locationName the location where the event occurred
     */
    public void displayEventDialog(Event event, Army army, String locationName) {

        String specificDetails = "";

        if (event instanceof Disaster) {
            specificDetails = "Units Removed: " + ((Disaster) event).getUnitsRemoved();
        } else if (event instanceof Reinforcements) {
            specificDetails = "Units Added: " + ((Reinforcements) event).getUnitsAdded();
        } else if (event instanceof Weaponry) {
            specificDetails = "Damage Increased By: " + ((Weaponry) event).getDamageIncrease();
        }

        JOptionPane.showMessageDialog(null,
                "Event: " + event.getName() + "\n" +
                        "Description: " + event.getDescription() + "\n" +
                        "Faction: " + army.getFaction() + "\n" +
                        "Location: " + locationName+ "\n" +
                        specificDetails,
                "Event Triggered",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
