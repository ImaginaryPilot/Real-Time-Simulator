package nl.rug.oop.rts.controller.commands;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;
import nl.rug.oop.rts.model.panel.ViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Command for clearing the graph.
 */
@AllArgsConstructor
public class ClearCommand implements Command {
    /**
     * The graph of the game.
     */
    private final GraphModel graphModel;
    /**
     * The view model of the game.
     */
    private final ViewModel viewModel;
    /**
     * List of all the edges before clearing them.
     */
    private final List<Edge> edges;
    /**
     * List of all the nodes before clearing them.
     */
    private final List<Node> nodes;

    @Override
    public void execute() {
        graphModel.setNodes(new ArrayList<>());
        graphModel.setEdges(new ArrayList<>());
        viewModel.setSelectedNode(null);
        viewModel.setSelectedEdge(null);
    }

    @Override
    public void undo() {
        graphModel.setNodes(nodes);
        graphModel.setEdges(edges);
    }
}
