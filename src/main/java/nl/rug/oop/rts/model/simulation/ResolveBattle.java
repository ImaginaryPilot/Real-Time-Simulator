package nl.rug.oop.rts.model.simulation;

import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.army.Faction;
import nl.rug.oop.rts.model.army.Team;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.Node;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for resolving the conflicts.
 * */
public class ResolveBattle {
    /**
     * Empty constructor since we have nothing to pass.
     * */
    public ResolveBattle(){

    }

    /**
     * Resolve the conflicts on the nodes.
     *
     * @param node contains the location we wish to resolve the conflict on
     * */
    public void resolveBattleOnNode(Node node){
        List<Army> toRemove = resolveBattle(node.getArmyList(), node.getName());
        if(toRemove == null) {
            return;
        }
        // kill armies lost
        for (Army army : toRemove){
            node.removeArmy(army);
        }
    }

    /**
     * Resolve the conflicts on the edges.
     *
     * @param edge contains the location we wish to resolve the conflict on
     * @param from source node the armies move from.
     * @param to destination nodes the armies move to.
     * */
    public void resolveBattleOnEdge(Edge edge, Node from, Node to){
        List<Army> toRemove = resolveBattle(edge.getArmyList(), edge.getName());
        if(toRemove == null) {
            return;
        }
        // kill armies lost
        for (Army army : toRemove){
            edge.removeArmy(army);
        }
    }

    /**
     * resolve the battle using battleIndex.
     *
     * @param armies list of armies on the location
     * @param locationName the location name to pop up a message
     * @return returns the armies to be removed
     * */
    private List<Army> resolveBattle(List<Army> armies, String locationName){
        if (armies.size() <= 1) {
            return null;
        }

        List<Army> teamBlue = new ArrayList<>();
        List<Army> teamRed = new ArrayList<>();

        for(Army army : armies){
            switch (army.getFaction().getTeam()){
                case BLUE -> teamBlue.add(army);
                case RED -> teamRed.add(army);
            }
        }

        if(teamBlue.isEmpty() || teamRed.isEmpty()) {
            return null;
        }

        Team winner = strengthCalculator(teamBlue, teamRed);

        List<Army> toRemove = new ArrayList<>();
        for(Army army : armies){
            if(army.getFaction().getTeam() != winner) {
                toRemove.add(army);
            } else {
                army.incrementWin();
                army.takeDamage();
            }
        }

        JOptionPane.showMessageDialog(
                null,
                "Faction " + winner.name() + " won the battle at " + locationName + "!",
                "Winner Announcement",
                JOptionPane.INFORMATION_MESSAGE,
                null
        );
        return toRemove;
    }

    /**
     * calculates the strengths of the factions.
     *
     * @param teamBlue first faction.
     * @param teamRed second faction.
     * @return winning faction.
     * */
    public Team strengthCalculator(List<Army> teamBlue, List<Army> teamRed){
        int teamBlueBattleIndex = 0;
        int teamRedBattleIndex = 0;

        for(Army army : teamBlue) {
            teamBlueBattleIndex += army.getTotalDamage() * army.getTotalHealth();
        }
        for(Army army: teamRed) {
            teamRedBattleIndex += army.getTotalDamage() * army.getTotalHealth();
        }
        return (teamBlueBattleIndex >= teamRedBattleIndex ? Team.BLUE : Team.RED);
    }
}
