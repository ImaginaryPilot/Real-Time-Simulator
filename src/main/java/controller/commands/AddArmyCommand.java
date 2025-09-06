package nl.rug.oop.rts.controller.commands;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;
import nl.rug.oop.rts.model.panel.ViewModel;

/**
 * Command for adding an army to a node.
 */
@AllArgsConstructor
public class AddArmyCommand implements Command {
    /**
     * The graph of the game.
     */
    private final GraphModel graphModel;
    /**
     * The view model of the game.
     */
    private final ViewModel viewModel;
    /**
     * The army to add.
     */
    private final Army army;
    /**
     * The node the army is being added to.
     */
    private final Node node;

    @Override
    public void execute() {
        graphModel.addArmy(node, army);
        viewModel.setSelectedNode(node);
        viewModel.updateAllObservers();
    }

    @Override
    public void undo() {
        for (Army a : node.getArmyList()) {
            if (a.getId() == army.getId()) {
                graphModel.removeArmy(node, a);
                viewModel.setSelectedNode(node);
                viewModel.updateAllObservers();
                return;
            }
        }
        System.err.println("Army not found in node: " + node + " army: " + army); //Should in theory never happen.
    }
}
