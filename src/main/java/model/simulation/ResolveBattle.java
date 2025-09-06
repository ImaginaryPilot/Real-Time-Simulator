package nl.rug.oop.rts.model.simulation;

import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.army.Team;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for resolving the conflicts.
 */
public class ResolveBattle {
    /**
     * The battle log of the simulation.
     */
    private List<String> battleLog;

    /**
     * Empty constructor since we have nothing to pass.
     *
     * @param battleLog the battle log
     */
    public ResolveBattle(List<String> battleLog) {
        this.battleLog = battleLog;
    }

    /**
     * Resolve the conflicts on the nodes.
     *
     * @param node contains the location we wish to resolve the conflict on
     */
    public void resolveBattleOnNode(Node node) {
        List<Army> toRemove = resolveBattle(node.getArmyList(), node.getName());
        if (toRemove == null) {
            return;
        }
        // kill armies lost
        for (Army army : toRemove) {
            node.removeArmy(army);
        }
    }

    /**
     * Resolve the conflicts on the edges.
     *
     * @param edge contains the location we wish to resolve the conflict on
     */
    public void resolveBattleOnEdge(Edge edge) {
        List<Army> toRemove = resolveBattle(edge.getArmyList(), edge.getName());
        if (toRemove == null) {
            return;
        }
        // kill armies lost
        for (Army army : toRemove) {
            edge.removeArmy(army);
        }
    }

    /**
     * resolve the battle using battleIndex.
     *
     * @param armies       list of armies on the location
     * @param locationName the location name to pop up a message
     * @return returns the armies to be removed
     */
    private List<Army> resolveBattle(List<Army> armies, String locationName) {
        if (armies.size() <= 1) {
            return null;
        }

        List<Army> teamBlue = new ArrayList<>();
        List<Army> teamRed = new ArrayList<>();

        for (Army army : armies) {
            switch (army.getFaction().getTeam()) {
                case BLUE -> teamBlue.add(army);
                case RED -> teamRed.add(army);
            }
        }

        if (teamBlue.isEmpty() || teamRed.isEmpty()) {
            return null;
        }

        Team winner = strengthCalculator(teamBlue, teamRed);

        List<Army> toRemove = new ArrayList<>();
        for (Army army : armies) {
            if (army.getFaction().getTeam() != winner) {
                toRemove.add(army);
            } else {
                army.incrementWin();
                army.takeDamage();
            }
        }

        battleLog.add("Faction " + winner.name() + " won the battle at " + locationName + "!");
        return toRemove;
    }

    /**
     * calculates the strengths of the factions.
     *
     * @param teamBlue first faction.
     * @param teamRed  second faction.
     * @return winning faction.
     */
    public Team strengthCalculator(List<Army> teamBlue, List<Army> teamRed) {
        int teamBlueBattleIndex = 0;
        int teamRedBattleIndex = 0;

        for (Army army : teamBlue) {
            teamBlueBattleIndex += army.getTotalDamage() * army.getTotalHealth();
        }
        for (Army army : teamRed) {
            teamRedBattleIndex += army.getTotalDamage() * army.getTotalHealth();
        }
        return (teamBlueBattleIndex >= teamRedBattleIndex ? Team.BLUE : Team.RED);
    }
}
