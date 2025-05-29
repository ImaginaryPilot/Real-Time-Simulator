package nl.rug.oop.rts.controller.commands;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.ViewModel;

/**
 * Command for adding an edge to the graph.
 */
@AllArgsConstructor
public class RemoveEdgeCommand implements Command {
    /**
     * The graph of the game.
     */
    private final GraphModel graphModel;
    /**
     * The view model of the game.
     */
    private final ViewModel viewModel;
    /**
     * The edge to add.
     */
    private final Edge edge;

    @Override
    public void execute() {
        graphModel.removeEdge(edge);
        viewModel.setSelectedEdge(null);
    }

    @Override
    public void undo() {
        graphModel.addEdge(edge);
        viewModel.setSelectedEdge(edge);
    }
}
