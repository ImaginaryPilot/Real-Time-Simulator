package nl.rug.oop.rts.controller.commands;

import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Edge;

/**
 * Command for adding an edge to the graph.
 */
public class AddEdgeCommand implements Command {
    /**
     * The graph of the game.
     */
    private final GraphModel graphModel;
    /**
     * The edge to add.
     */
    private final Edge edge;

    /**
     * Constructor for the AddEdgeCommand class.
     *
     * @param graphModel The graph of the game.
     * @param edge       The edge to add.
     */
    public AddEdgeCommand(GraphModel graphModel, Edge edge) {
        this.graphModel = graphModel;
        this.edge = edge;
    }

    @Override
    public void redo() {
        graphModel.addEdge(edge);
    }

    @Override
    public void undo() {
        graphModel.removeEdge(edge);
    }
}
