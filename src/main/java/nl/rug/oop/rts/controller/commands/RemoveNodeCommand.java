package nl.rug.oop.rts.controller.commands;

import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;

/**
 * Command for adding a node to the graph.
 */
public class RemoveNodeCommand implements Command {
    /**
     * The graph of the game.
     */
    private final GraphModel graphModel;
    /**
     * The node to add.
     */
    private final Node node;

    /**
     * Constructor for the RemoveNodeCommand class.
     *
     * @param graphModel The graph of the game.
     * @param node       The node to remove.
     */
    public RemoveNodeCommand(GraphModel graphModel, Node node) {
        this.graphModel = graphModel;
        this.node = node;
    }

    @Override
    public void redo() {
        graphModel.removeNode(node);
    }

    @Override
    public void undo() {
        graphModel.addNode(node);
    }
}
