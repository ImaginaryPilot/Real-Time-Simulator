package nl.rug.oop.rts.controller.commands;

import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;

/**
 * Command for adding a node to the graph.
 */
public class AddNodeCommand implements Command {
    /**
     * The graph of the game.
     */
    private final GraphModel graphModel;
    /**
     * The node to add.
     */
    private final Node node;

    /**
     * Constructor for the AddNodeCommand class.
     *
     * @param graphModel The graph of the game.
     * @param node       The node to add.
     */
    public AddNodeCommand(GraphModel graphModel, Node node) {
        this.graphModel = graphModel;
        this.node = node;
    }

    @Override
    public void execute() {
        graphModel.addNode(node);
        System.out.println("redoing the add note command");
    }

    @Override
    public void undo() {
        graphModel.removeNode(node);
        System.out.println("undoing the add note command");
    }
}
