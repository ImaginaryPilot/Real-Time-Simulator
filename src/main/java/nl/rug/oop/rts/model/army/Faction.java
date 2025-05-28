package nl.rug.oop.rts.model.army;

import java.util.List;

/**
 * Faction enum.
 */
public enum Faction {
    MEN, ELVES, DWARVES, MORDOR, ISENGARD;

    /**
     * Gets the unit names of the faction.
     *
     * @return The unit names of the faction.
     */
    public List<String> getUnitNames() {
        return switch (this) {
            case MEN -> List.of("Gondor Soldier", "Tower Guard", "Ithilien Ranger");
            case ELVES -> List.of("Lorien Warrior", "Mirkwood Archer", "Rivendell Lancer");
            case DWARVES -> List.of("Guardian", "Phalanx", "Axe Thrower");
            case MORDOR -> List.of("Orc Warrior", "Orc Pikeman", "Haradrim Archer");
            case ISENGARD -> List.of("Uruk-hai", "Uruk Crossbowman", "Warg Rider");
        };
    }

    /**
     * Checks if the factions are teamed.
     *
     * @param other The other faction.
     * @return True if the factions are teamed, false otherwise.
     */
    public boolean isTeamedWith(Faction other) {
        return (this == MEN || this == ELVES || this == DWARVES) &&
                (other == MEN || other == ELVES || other == DWARVES)
                || (this == MORDOR || this == ISENGARD) &&
                (other == MORDOR || other == ISENGARD);
    }
}
