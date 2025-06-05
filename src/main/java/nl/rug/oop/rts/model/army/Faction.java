package nl.rug.oop.rts.model.army;

import java.util.List;

/**
 * Race Enum.
 */
public enum Faction {
    Men{
        @Override
        public List<String> getUnitNames() {
            return List.of("Gondor Soldier", "Tower Guard", "Ithilien Ranger");
        }

        @Override
        public Team getTeam() {
            return Team.BLUE;
        }
    },
    Elves{
        @Override
        public List<String> getUnitNames() {
            return List.of("Lorien Warrior", "Mirkwood Archer", "Rivendell Lancer");
        }

        @Override
        public Team getTeam() {
            return Team.BLUE;
        }
    },
    Dwarves{
        @Override
        public List<String> getUnitNames() {
            return List.of("Guardian", "Phalanx", "Axe Thrower");
        }

        @Override
        public Team getTeam() {
            return Team.BLUE;
        }
    },
    Mordor{
        @Override
        public List<String> getUnitNames() {
            return List.of("Orc Warrior", "Orc Pikeman", "Haradrim Archer");
        }

        @Override
        public Team getTeam() {
            return Team.RED;
        }
    },
    Isengard{
        @Override
        public List<String> getUnitNames() {
            return List.of("Uruk-hai", "Uruk Crossbowman", "Warg Rider");
        }

        @Override
        public Team getTeam() {
            return Team.RED;
        }
    };

    /**
     * Gets the unit names of the faction.
     *
     * @return The unit names of the faction.
     */
    public abstract List<String> getUnitNames();

    public abstract Team getTeam();
}
