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
        System.out.println("Army: Node: " + node + " Army: " + army + " added.");
        graphModel.addArmy(node, army);
        viewModel.updateAllObservers();
    }

    @Override
    public void undo() {
        System.out.println("all armies in node: " + node + ", armies: " + node.getArmyList());
        System.out.println("Army: Node: " + node + " Army: " + army + " removed.");
        graphModel.removeArmy(node, army);
        viewModel.updateAllObservers();
    }
}
