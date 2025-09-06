package nl.rug.oop.rts.model.events;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.army.Unit;

import java.util.Random;

/**
 * Event that equips hidden weaponry, increasing the damage of all units in an army.
 */
@Setter
@Getter
public class Weaponry extends Event {
    /**
     * Damage increase.
     */
    private final Random random = new Random();
    /**
     * Damage increase for all units in the army.
     */
    private int damageIncrease;

    /**
     * Constructor for the Weaponry class.
     */
    public Weaponry() {
        super("damage buff", "The army found a stash of magic weapons.");
    }

    /**
     * Increases the damage of all units in the army by a random number.
     *
     * @param army army that gets the weaponry.
     */
    @Override
    public void apply(Army army) {
        damageIncrease = random.nextInt(10);
        for (Unit unit : army.getUnits()) {
            unit.setDamage(unit.getDamage() + damageIncrease); // Increase unit damage by 5
        }
    }
}