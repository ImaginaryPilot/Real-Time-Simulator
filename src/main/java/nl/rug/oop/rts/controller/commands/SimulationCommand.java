package nl.rug.oop.rts.controller.commands;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;

import java.util.HashMap;
import java.util.List;


@AllArgsConstructor
public class SimulationCommand implements Command {
    private final GraphModel graphModel;

    HashMap<Integer, List<Army>> armiesMap;

    HashMap<Integer, List<Army>> armiesMapNew;

    @Override
    public void execute() {
        for (Node node : graphModel.getNodes()) {
            List<Army> copiedArmies = armiesMapNew.get(node.getId());
            node.setArmies(copiedArmies);  // Safe because we already made copies
        }
        graphModel.updateAllObservers();
    }

    @Override
    public void undo() {
        for (Node node : graphModel.getNodes()) {
            List<Army> copiedArmies = armiesMap.get(node.getId());
            node.setArmies(copiedArmies);  // Safe because we already made copies
        }
        graphModel.updateAllObservers();
    }
}
