package nl.rug.oop.rts.model.army;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Unit class.
 */
@Data
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

    public void reduceDamage(int damageReduction){
        damage -= damageReduction;
    }

    public void reduceHealth(int healthReduction){
        health -= healthReduction;
    }

    public boolean isAlive(){
        return (health > 0);
    }
}
