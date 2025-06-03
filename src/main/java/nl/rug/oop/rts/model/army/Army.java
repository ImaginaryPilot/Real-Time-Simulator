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
     * */
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

    public String getTextureName() {
        return "faction" + faction.name();
    }

    public void incrementWin(){
        battlesWon++;
    }

    public int getTotalHealth() {
        return units.stream().mapToInt(Unit::getHealth).sum();
    }

    public int getTotalDamage() {
        return units.stream().mapToInt(Unit::getDamage).sum();
    }

    public void takeDamage(){
        for (int i = 0; i < units.size(); i++) {
            int damageReduction = 1 + random.nextInt(3); // 1 - 3
            int healthReduction = 10 + random.nextInt(11); // 10 - 20
            units.get(i).reduceDamage(damageReduction);
            units.get(i).reduceHealth(healthReduction);
            if(!units.get(i).isAlive()) {
                units.remove(units.get(i));
            }
        }
    }

}
