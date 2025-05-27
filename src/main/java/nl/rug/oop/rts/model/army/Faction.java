package nl.rug.oop.rts.model.army;

import java.util.List;

public enum Faction {
    MEN, ELVES, DWARVES, MORDOR, ISENGARD;

    public List<String> getUnitNames() {
        return switch (this) {
            case MEN -> List.of("Gondor Soldier", "Tower Guard", "Ithilien Ranger");
            case ELVES -> List.of("Lorien Warrior", "Mirkwood Archer", "Rivendell Lancer");
            case DWARVES -> List.of("Guardian", "Phalanx", "Axe Thrower");
            case MORDOR -> List.of("Orc Warrior", "Orc Pikeman", "Haradrim Archer");
            case ISENGARD -> List.of("Uruk-hai", "Uruk Crossbowman", "Warg Rider");
        };
    }

    public boolean isTeamedWith(Faction other) {
        return (this == MEN || this == ELVES || this == DWARVES) &&
                (other == MEN || other == ELVES || other == DWARVES)
                || (this == MORDOR || this == ISENGARD) &&
                (other == MORDOR || other == ISENGARD);
    }
}
