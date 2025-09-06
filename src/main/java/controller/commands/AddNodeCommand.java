package nl.rug.oop.rts.controller.commands;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;
import nl.rug.oop.rts.model.panel.ViewModel;

/**
 * Command for adding a node to the graph.
 */
@AllArgsConstructor
public class AddNodeCommand implements Command {
    /**
     * The graph of the game.
     */
    private final GraphModel graphModel;
    /**
     * The view model of the game.
     */
    private final ViewModel viewModel;
    /**
     * The node to add.
     */
    private final Node node;

    @Override
    public void execute() {
        graphModel.addNode(node);
        viewModel.setSelectedNode(node);
    }

    @Override
    public void undo() {
        graphModel.removeNode(node);
        viewModel.setSelectedNode(null);
    }
}
