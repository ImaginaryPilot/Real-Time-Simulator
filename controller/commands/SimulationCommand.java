package nl.rug.oop.rts.controller.commands;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;
import nl.rug.oop.rts.model.simulation.NodeArmyState;

import java.util.List;

/**
 * Command for simulating the game.
 */
@AllArgsConstructor
public class SimulationCommand implements Command {
    /**
     * The graph of the game.
     */
    private final GraphModel graphModel;
    /**
     * The armies before the simulation.
     * Stored with nodeID.
     */
    private List<NodeArmyState> nodeArmyStates;
    /**
     * The armies after the simulation.
     * Stored with nodeID.
     */
    private List<NodeArmyState> nodeArmyStatesNew;

    @Override

    public void execute() {
        setArmiesMap(nodeArmyStatesNew);
        graphModel.updateAllObservers();
    }

    @Override
    public void undo() {
        setArmiesMap(nodeArmyStates);
        graphModel.updateAllObservers();
    }

    /**
     * Sets the armies of the nodes in the graph to the armies in the given list.
     *
     * @param nodeArmyState The list of armies to set.
     */
    private void setArmiesMap(List<NodeArmyState> nodeArmyState) {
        for (Node node : graphModel.getNodes()) {
            for (NodeArmyState savedState : nodeArmyState) {
                if (savedState.getNodeId() == node.getId()) {
                    node.setArmies(savedState.getArmies());
                    break;
                }
            }
        }
    }
}
