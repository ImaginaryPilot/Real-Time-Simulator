package nl.rug.oop.rts.model.simulation;

import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.events.Event;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The class responsible for the simulation.
 * */
public class Simulation {
    /**
     * The random variable.
     * */
    private final Random random = new Random();
    /**
     * The graph model.
     * */
    private final GraphModel graphModel;
    /**
     * The class responsible for resolving the conflicts.
     * */
    private final ResolveBattle resolveBattle;
    /**
     * Class responsible for resolving the events(more printing the results).
     */
    private final ResolveEvent resolveEvent;
    /**
     * List of all moves the armies would make.
     * */
    private List<Move> moves;

    /**
     * Constructor responsible for instantiating resolveBattle as well.
     *
     * @param graphModel to store the graphModel.
     * */
    public Simulation(GraphModel graphModel){
        this.graphModel = graphModel;
        resolveBattle = new ResolveBattle();
        resolveEvent = new ResolveEvent();
    }

    /**
     * A singular simulation step.
     * */
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
        processEdgeEvents();

        // phase 6: move armies to destination nodes
        moveArmyToNode();

        // phase 7: resolve conflicts after moving
        resolveConflictAtNode();

        // phase 8: encounter events at node
        processNodeEvents();

        graphModel.updateAllObservers();
    }

    /**
     * Resolve the conflict at the nodes.
     * */
    private void resolveConflictAtNode(){
        for(Node node: graphModel.getNodes()){
            resolveBattle.resolveBattleOnNode(node);
        }
    }

    /**
     * Preparing the moves for the armies.
     * */
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

    /**
     * Resolve the conflicts at the edges.
     * */
    private void resolveConflictAtEdge(){
        for(Move move : moves){
            resolveBattle.resolveBattleOnEdge(move.getEdge(), move.getFrom(), move.getTo());
        }
    }

    /**
     * Function that processes the events at the edges.
     */
    private void processEdgeEvents() {
        for (Move move : moves) {
            Edge edge = move.getEdge();
            Army army = move.getArmy();
            Event triggeredEvent = edge.triggerRandomEvent(army, random);
            if (triggeredEvent != null) {
                // Use the EventPanel to display an event notification popup
                resolveEvent.displayEventDialog(triggeredEvent, army, edge.getName());
            }
        }
    }



    /**
     * move armies to the edges.
     * */
    private void moveArmyToEdge(){
        for(Move move : moves){
            move.getFrom().removeArmy(move.getArmy());
            move.getEdge().addArmy(move.getArmy());
        }
    }

    /**
     * move the armies to the nodes.
     * */
    private void moveArmyToNode(){
        for(Move move: moves){
            if(move.getEdge().getArmyList().contains(move.getArmy())){
                move.getEdge().removeArmy(move.getArmy());
                move.getTo().addArmy(move.getArmy());
            }
        }
    }

    /**
     * Function that processes the events at the nodes.
     */
    private void processNodeEvents() {
        for (Node node : graphModel.getNodes()) {
            for (Army army : node.getArmyList()) {
                Event triggeredEvent = node.triggerRandomEvent(army, random);
                if (triggeredEvent != null) {
                    // Use the EventPanel to display an event notification popup
                    resolveEvent.displayEventDialog(triggeredEvent, army, node.getName());
                }
            }
        }
    }


}
