package nl.rug.oop.rts.controller.commands;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;

@AllArgsConstructor
public class MoveNodeCommand implements Command {
    private final Node node;
    private final int oldX;
    private final int oldY;

    private final int newX;
    private final int newY;

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
