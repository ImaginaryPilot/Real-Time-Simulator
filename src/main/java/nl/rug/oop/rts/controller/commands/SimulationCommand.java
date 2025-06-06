package nl.rug.oop.rts.controller.commands;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;

import java.util.HashMap;
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
     * The armies of the game before the simulation.
     * Stored in a map with the node id as the key and the armies as value.
     */
    private HashMap<Integer, List<Army>> armiesMap;
    /**
     * The armies of the game after the simulation.
     * Stored in a map with the node id as the key and the armies as value.
     */
    private HashMap<Integer, List<Army>> armiesMapNew;

    @Override
    public void execute() {
        for (Node node : graphModel.getNodes()) {
            List<Army> copiedArmies = armiesMapNew.get(node.getId());
            node.setArmies(copiedArmies);
        }
        graphModel.updateAllObservers();
    }

    @Override
    public void undo() {
        for (Node node : graphModel.getNodes()) {
            List<Army> copiedArmies = armiesMap.get(node.getId());
            node.setArmies(copiedArmies);
        }
        graphModel.updateAllObservers();
    }
}
