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
     * Constructor for the Army class.
     *
     * @param faction The faction of the army.
     */
    public Army(Faction faction) {
        this.faction = faction;
        this.units = new ArrayList<>();
    }

    /**
     * Generates the units of the army.
     */
    private void generateUnits() {
        Random random = new Random();
        int numberOfUnits = 10 + random.nextInt(41); // 10 - 50
        List<String> unitNames = faction.getUnitNames();

        for (int i = 0; i < numberOfUnits; i++) {
            String name = unitNames.get(random.nextInt(unitNames.size()));
            int damage = 10 + random.nextInt(11); // 10 - 20
            int health = 50 + random.nextInt(51); // 50 - 100
            units.add(new Unit(name, damage, health));
        }
    }
}
