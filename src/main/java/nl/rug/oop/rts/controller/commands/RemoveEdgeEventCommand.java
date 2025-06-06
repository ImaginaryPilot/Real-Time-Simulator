package nl.rug.oop.rts.controller.commands;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.events.Event;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.GraphModel;

/**
 * Command for removing an edge event.
 */
@AllArgsConstructor
public class RemoveEdgeEventCommand implements Command{
    /**
     * The graph of the game.
     */
    private final GraphModel graphModel;
    /**
     * The edge from which the event is removed.
     */
    private final Edge edge;
    /**
     * The event removed.
     */
    private final Event event;

    @Override
    public void execute() {
        graphModel.removeEdgeEvent(edge, event);
    }

    @Override
    public void undo() {
        graphModel.addEdgeEvent(edge, event);
    }
}
