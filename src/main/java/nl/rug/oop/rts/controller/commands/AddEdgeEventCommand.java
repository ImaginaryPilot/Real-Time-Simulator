package nl.rug.oop.rts.controller.commands;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.events.Event;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.GraphModel;

/**
 * Command for adding an edge event to an edge.
 */
@AllArgsConstructor
public class AddEdgeEventCommand implements Command{
    /**
     * The graph of the game.
     */
    private final GraphModel graphModel;
    /**
     * The event to add.
     */
    private final Event event;
    /**
     * The edge to which the event is added.
     */
    private final Edge edge;

    @Override
    public void execute() {
        graphModel.addEdgeEvent(edge, event);
    }

    @Override
    public void undo() {
        graphModel.removeEdgeEvent(edge, event);
    }
}
