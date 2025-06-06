package nl.rug.oop.rts.model.events;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.army.Unit;

import java.util.Random;

/**
 * Event that adds new units to an army.
 */
@Getter
@Setter
public class Reinforcements extends Event {
    /**
     * The number of units added to the army.
     */
    private final Random random = new Random();
    /**
     * The number of units added to the army.
     */
    private int unitsAdded;

    /**
     * Constructor for the Reinforcements class.
     */
    public Reinforcements() {
        super("Restructuring", "A godsent, units have been added to the army.");
    }

    /**
     * Adds new units to the army.
     *
     * @param army the army that gets reinforcements.
     */
    @Override
    public void apply(Army army) {
        unitsAdded = 5 + random.nextInt(16); // Add 5–20 units
        int totalDamage = 0;

        for (Unit unit : army.getUnits()) {
            totalDamage += unit.getDamage();
        }

        int averageDamage = army.getUnits().isEmpty() ? 10 : totalDamage / army.getUnits().size();

        for (int i = 0; i < unitsAdded; i++) {
            String name = army.getFaction().getUnitNames()
                    .get(random.nextInt(army.getFaction().getUnitNames().size()));
            int health = 50 + random.nextInt(51);
            army.getUnits().add(new Unit(name, health, averageDamage));
        }
    }
}