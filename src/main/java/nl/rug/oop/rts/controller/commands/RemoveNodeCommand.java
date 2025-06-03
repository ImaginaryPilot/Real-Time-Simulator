package nl.rug.oop.rts.controller.commands;

import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;
import nl.rug.oop.rts.model.panel.ViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Command for adding a node to the graph.
 */
public class RemoveNodeCommand implements Command {
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
    /**
     * The edges to / from the node.
     * Needed to recreate with undo.
     */
    private List<Edge> edges;

    /**
     * Instantiates a new Remove node command.
     *
     * @param graphModel the graph model
     * @param viewModel  the view model
     * @param node       the node
     */
    public RemoveNodeCommand(GraphModel graphModel, ViewModel viewModel, Node node) {
        this.graphModel = graphModel;
        this.viewModel = viewModel;
        this.node = node;
        this.edges = new ArrayList<>();
    }

    @Override
    public void execute() {
        for (Edge edge : graphModel.getEdges()) {
            if (edge.getNodeA() == node || edge.getNodeB() == node) {
                edges.add(edge);
            }
        }
        graphModel.removeNode(node);
        viewModel.setSelectedNode(null);
    }

    @Override
    public void undo() {
        graphModel.addNode(node);
        for (Edge edge : edges) {
            graphModel.addEdge(edge);
        }
        viewModel.setSelectedNode(node);
    }
}
