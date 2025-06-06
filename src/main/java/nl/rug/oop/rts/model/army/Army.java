package nl.rug.oop.rts.model.army;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Army class that holds all the information of an army in the game.
 */
@Getter
@Setter
public class Army {
    /**
     * The faction of the army.
     */
    private final Faction faction;
    /**
     * The list of units in the army.
     */
    private List<Unit> units;
    /**
     * The number of battles won.
     **/
    private int battlesWon = 0;
    /**
     * The random variable.
     */
    private final Random random = new Random();

    /**
     * Constructor for the Army class.
     *
     * @param faction The faction of the army.
     */
    public Army(Faction faction) {
        this.faction = faction;
        this.units = new ArrayList<>();
        generateUnits();
    }

    /**
     * Constructor without generating units.
     *
     * @param faction    the faction
     * @param battlesWon the battles won
     */
    public Army(Faction faction, int battlesWon) {
        this.faction = faction;
        this.battlesWon = battlesWon;
        this.units = new ArrayList<>();
    }

    /**
     * Generates the units of the army.
     */
    private void generateUnits() {
        int numberOfUnits = 10 + random.nextInt(41); // 10 - 50
        List<String> unitNames = faction.getUnitNames();

        for (int i = 0; i < numberOfUnits; i++) {
            String name = unitNames.get(random.nextInt(unitNames.size()));
            int damage = 10 + random.nextInt(11); // 10 - 20
            int health = 50 + random.nextInt(51); // 50 - 100
            units.add(new Unit(name, damage, health));
        }
    }

    /**
     * get the faction name to render the texture of army.
     *
     * @return the string to load texture
     */
    public String getTextureName() {
        return "faction" + faction.name();
    }

    /**
     * increment the number of wins the army has.
     */
    public void incrementWin() {
        battlesWon++;
    }

    /**
     * The total amount of health of the army.
     *
     * @return total amount of health
     */
    public int getTotalHealth() {
        int totalHealth = 0;
        for (Unit unit : units) {
            totalHealth += unit.getHealth();
        }
        return totalHealth;
    }

    /**
     * The total damage amount of the army.
     *
     * @return total amount of damage
     */
    public int getTotalDamage() {
        int totalDamage = 0;
        for (Unit unit : units) {
            totalDamage += unit.getDamage();
        }
        return totalDamage;
    }

    /**
     * function to reduce the army after a conflict has taken place.
     */
    public void takeDamage() {
        for (int i = 0; i < units.size(); i++) {
            int damageReduction = random.nextInt(3); // 0 - 2
            int healthReduction = 10 + random.nextInt(11); // 10 - 20
            units.get(i).reduceDamage(damageReduction);
            units.get(i).reduceHealth(healthReduction);
            if (!units.get(i).isAlive()) {
                units.remove(units.get(i));
            }
        }
    }

    /**
     * The copy of the entire army.
     *
     * @return the copy of the army
     */
    public Army copy() {
        Army armyCopy = new Army(this.faction, this.battlesWon);

        for (Unit unit : units) {
            armyCopy.units.add(unit.copy());
        }

        return armyCopy;
    }
}
