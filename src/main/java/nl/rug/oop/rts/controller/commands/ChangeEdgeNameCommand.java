package nl.rug.oop.rts.controller.commands;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.ViewModel;

/**
 * Command for changing the name of an edge.
 */
@Getter
public class ChangeEdgeNameCommand implements Command {
    /**
     * The view model.
     */
    private final ViewModel viewModel;
    /**
     * The graph of the game.
     */
    private final GraphModel graphModel;
    /**
     * The edge to change the name of.
     */
    private final Edge edge;
    /**
     * The new name of the edge.
     */
    private final String newName;
    /**
     * The old name of the edge.
     */
    @Setter
    private String oldName;

    /**
     * Constructor for the changeEdgeNameCommand class.
     *
     * @param edge       The edge to change the name of.
     * @param oldName    The old name of the edge.
     * @param newName    The new name of the edge.
     * @param graphModel The graph of the game.
     * @param viewModel  the view model
     */
    public ChangeEdgeNameCommand(
            Edge edge, String oldName, String newName, GraphModel graphModel, ViewModel viewModel) {
        this.edge = edge;
        this.oldName = oldName;
        this.newName = newName;
        this.graphModel = graphModel;
        this.viewModel = viewModel;
    }

    @Override
    public void execute() {
        graphModel.setEdgeName(edge, newName);
        viewModel.setSelectedEdge(edge);
    }

    @Override
    public void undo() {
        graphModel.setEdgeName(edge, oldName);
        viewModel.setSelectedEdge(edge);
    }
}
