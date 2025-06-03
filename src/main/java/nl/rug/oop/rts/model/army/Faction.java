package nl.rug.oop.rts.model.army;

import java.awt.*;
import java.util.List;

/**
 * Faction enum.
 */
public enum Faction {
    Men, Elves, Dwarves, Mordor, Isengard;

    /**
     * The teams of each faction.
     * */
    public enum Team {
        BLUE, YELLOW
    }

    /**
     * Getting the team of the army.
     *
     * @return army team
     * */
    public Team getTeam(){
        return switch (this){
            case Men, Elves, Dwarves -> Team.BLUE;
            case Mordor, Isengard -> Team.YELLOW;
        };
    }

    /**
     * Gets the unit names of the faction.
     *
     * @return The unit names of the faction.
     */
    public List<String> getUnitNames() {
        return switch (this) {
            case Men -> List.of("Gondor Soldier", "Tower Guard", "Ithilien Ranger");
            case Elves -> List.of("Lorien Warrior", "Mirkwood Archer", "Rivendell Lancer");
            case Dwarves -> List.of("Guardian", "Phalanx", "Axe Thrower");
            case Mordor -> List.of("Orc Warrior", "Orc Pikeman", "Haradrim Archer");
            case Isengard -> List.of("Uruk-hai", "Uruk Crossbowman", "Warg Rider");
        };
    }
}
