package nl.rug.oop.rts.controller.commands;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;

/**
 * Command for moving a node.
 */
@AllArgsConstructor
public class MoveNodeCommand implements Command {
    /**
     * The node to move.
     */
    private final Node node;
    /**
     * The old x position of the node.
     */
    private final int oldX;
    /**
     * The old y position of the node.
     */
    private final int oldY;
    /**
     * The new x position of the node.
     */
    private final int newX;
    /**
     * The new y position of the node.
     */
    private final int newY;

    /**
     * The graph of the game.
     */
    private final GraphModel graphModel;

    @Override
    public void execute() {
        graphModel.setNodePosition(node, newX, newY);
    }

    @Override
    public void undo() {
        graphModel.setNodePosition(node, oldX, oldY);
    }

}
