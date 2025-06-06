package nl.rug.oop.rts.controller.commands;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;

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
    }

    @Override
    public void undo() {
        graphModel.removeArmy(node, army);
    }
}
