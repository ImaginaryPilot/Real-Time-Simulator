package nl.rug.oop.rts.model.simulation;

import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {
    private final Random random = new Random();
    private final GraphModel graphModel;
    private final ResolveBattle resolveBattle;
    private List<Move> moves;

    public Simulation(GraphModel graphModel){
        this.graphModel = graphModel;
        resolveBattle = new ResolveBattle();
    }

    public void simulationStep(){
        moves = new ArrayList<>();

        // phase 1: resolve conflicts before moving
        resolveConflictAtNode();

        // phase 2: prepare movements for armies
        prepareMove();

        // phase 3: move armies to edges
        moveArmyToEdge();

        // phase 4: resolve conflict at edges
        resolveConflictAtEdge();

        // phase 5: encounter events at edge

        // phase 6: move armies to destination nodes
        moveArmyToNode();

        // phase 7: resolve conflicts after moving
        resolveConflictAtNode();

        // phase 8: encounter events at node

        graphModel.updateAllObservers();
    }

    private void resolveConflictAtNode(){
        for(Node node: graphModel.getNodes()){
            resolveBattle.resolveBattleOnNode(node);
        }
    }

    private void prepareMove(){
        for(Node node: graphModel.getNodes()){
            List<Army> armies = node.getArmyList();
            for(Army army : armies.toArray(new Army[0])){
                List<Edge> edges = graphModel.getConnectedEdges(node);

                if(!edges.isEmpty()){
                    Edge chosenEdge = edges.get(random.nextInt(edges.size()));
                    Node otherNode = graphModel.getOtherNode(chosenEdge, node);

                    moves.add(new Move(army, node, otherNode, chosenEdge));
                }
            }
        }
    }

    private void resolveConflictAtEdge(){
        for(Move move : moves){
            resolveBattle.resolveBattleOnEdge(move.edge, move.from, move.to);
        }
    }

    private void moveArmyToEdge(){
        for(Move move : moves){
            move.from.removeArmy(move.army);
            move.edge.addArmy(move.army);
        }
    }

    private void moveArmyToNode(){
        for(Move move: moves){
            if(move.edge.getArmyList().contains(move.army)){
                move.edge.removeArmy(move.army);
                move.to.addArmy(move.army);
            }
        }
    }
}
