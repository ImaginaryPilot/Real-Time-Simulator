package nl.rug.oop.rts.controller.commands;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;
import nl.rug.oop.rts.model.panel.ViewModel;

/**
 * Command for removing an army from a node.
 */
@AllArgsConstructor
public class RemoveArmyCommand implements Command {
    /**
     * The graph of the game.
     */
    private final GraphModel graphModel;
    /**
     * The view model of the game.
     */
    private final ViewModel viewModel;
    /**
     * The army to remove.
     */
    private final Army army;
    /**
     * The node the army is being removed from.
     */
    private final Node node;

    @Override
    public void execute() {
        graphModel.removeArmy(node, army);
        viewModel.setSelectedNode(node);
        viewModel.updateAllObservers();
    }

    @Override
    public void undo() {
        graphModel.addArmy(node, army);
        viewModel.setSelectedNode(node);
        viewModel.updateAllObservers();
    }
}
