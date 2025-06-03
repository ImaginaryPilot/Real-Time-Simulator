package nl.rug.oop.rts.model.simulation;

import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.army.Faction;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.Node;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ResolveBattle {
    public ResolveBattle(){

    }

    public void resolveBattleOnNode(Node node){
        List<Army> toRemove = resolveBattle(node.getArmyList(), node.getName());
        if(toRemove == null) return;
        // kill armies lost
        for (Army army : toRemove){
            node.removeArmy(army);
        }
    }

    public void resolveBattleOnEdge(Edge edge, Node from, Node to){
        List<Army> toRemove = resolveBattle(edge.getArmyList(), edge.getName());
        if(toRemove == null) return;
        // kill armies lost
        for (Army army : toRemove){
            edge.removeArmy(army);
        }
    }

    private List<Army> resolveBattle(List<Army> armies, String locationName){
        if (armies.size() <= 1) return null;

        List<Army> teamBlue = new ArrayList<>();
        List<Army> teamYellow = new ArrayList<>();

        for(Army army : armies){
            switch (army.getFaction().getTeam()){
                case BLUE -> teamBlue.add(army);
                case YELLOW -> teamYellow.add(army);
            }
        }

        if(teamBlue.isEmpty() || teamYellow.isEmpty()) return null;

        int teamBlueBattleIndex = 0;
        int teamYellowBattleIndex = 0;

        for(Army army : teamBlue) {
            teamBlueBattleIndex += army.getTotalDamage() * army.getTotalHealth();
        }
        for(Army army: teamYellow) {
            teamYellowBattleIndex += army.getTotalDamage() * army.getTotalHealth();
        }

        Faction.Team winner = teamBlueBattleIndex >= teamYellowBattleIndex ? Faction.Team.BLUE : Faction.Team.YELLOW;

        List<Army> toRemove = new ArrayList<>();
        List<Army> winners = new ArrayList<>();
        for(Army army : armies){
            if(army.getFaction().getTeam() != winner){
                toRemove.add(army);
            }
            else {
                winners.add(army);
            }
        }

        for(Army army : winners){
            army.incrementWin();
            army.takeDamage();
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
}
