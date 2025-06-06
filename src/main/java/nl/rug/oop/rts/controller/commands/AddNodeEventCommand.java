package nl.rug.oop.rts.controller.commands;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.events.Event;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;

/**
 * Command for adding an event to a node.
 */
@AllArgsConstructor
public class AddNodeEventCommand implements Command{
    /**
     * The graph of the game.
     */
    private final GraphModel graphModel;
    /**
     * The event to add.
     */
    private final Event event;
    /**
     * The node to which the event is added.
     */
    private final Node node;

    @Override
    public void execute() {
        graphModel.addNodeEvent(node, event);
    }

    @Override
    public void undo() {
        graphModel.removeNodeEvent(node, event);
    }
}
