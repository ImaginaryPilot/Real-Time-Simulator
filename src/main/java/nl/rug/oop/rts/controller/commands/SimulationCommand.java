package nl.rug.oop.rts.controller.commands;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.panel.GraphModel;

import java.util.List;


@AllArgsConstructor
public class SimulationCommand implements Command {
    private final GraphModel graphModel;

    private List<List<Army>> nodeArmies;

    private List<List<Army>> nodeArmiesNew;

    @Override
    public void execute() {
        for (int i = 0; i < graphModel.getNodes().size(); i++) {
            graphModel.getNodes().get(i).setArmies(nodeArmiesNew.get(i));
        }
        graphModel.updateAllObservers();

    }

    @Override
    public void undo() {
        for (int i = 0; i < graphModel.getNodes().size(); i++) {
            graphModel.getNodes().get(i).setArmies(nodeArmies.get(i));
        }
        graphModel.updateAllObservers();
    }
}
