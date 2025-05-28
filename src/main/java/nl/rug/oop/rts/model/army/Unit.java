package nl.rug.oop.rts.model.army;

import lombok.AllArgsConstructor;

/**
 * Unit class.
 */
@AllArgsConstructor
public class Unit {
    /**
     * Name of unit.
     */
    private String name;
    /**
     * Health of unit.
     */
    private int health;
    /**
     * Damage of unit.
     */
    private int damage;
}
