package nl.rug.oop.rts.controller.commands;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;

/**
 * Command for changing the name of a node.
 */
@Getter
@ToString
public class ChangeNodeNameCommand implements Command {
    /**
     * The graph of the game.
     */
    private final GraphModel graphModel;
    /**
     * The node to change the name of.
     */
    private final Node node;
    /**
     * The new name of the node.
     */
    private final String newName;
    /**
     * The old name of the node.
     */
    @Setter
    private String oldName;

    /**
     * Constructor for the changeNodeNameCommand class.
     *
     * @param node       The node to change the name of.
     * @param oldName    The old name of the node.
     * @param newName    The new name of the node.
     * @param graphModel The graph of the game.
     */
    public ChangeNodeNameCommand(Node node, String oldName, String newName, GraphModel graphModel) {
        this.node = node;
        this.oldName = oldName;
        this.newName = newName;
        this.graphModel = graphModel;
    }

    @Override
    public void execute() {
        graphModel.setNodeName(node, newName);
    }

    @Override
    public void undo() {
        graphModel.setNodeName(node, oldName);
    }
}
