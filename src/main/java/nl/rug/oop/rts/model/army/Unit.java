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
     * damage amount of the unit.
     */
    private int damage;

    /**
     * reduce the amount of damage unit will deal.
     *
     * @param  damageReduction the amount the units' damage will be reduced by
     * */
    public void reduceDamage(int damageReduction){
        damage -= damageReduction;
    }

    /**
     * reduce the amount of health the unit has.
     *
     * @param healthReduction the amount the health of the unit is reduced by
     * */
    public void reduceHealth(int healthReduction){
        health -= healthReduction;
    }

    /**
     * boolean to check if the unit is even alive.
     *
     * @return if the unit is alive
     * */
    public boolean isAlive(){
        return (health > 0);
    }

    /**
     * returning the copy of the unit.
     *
     * @return new Unit as a copy.
     * */
    public Unit copy(){
        return new Unit(this.name, this.health, this.damage);
    }
}
