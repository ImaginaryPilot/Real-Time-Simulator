package nl.rug.oop.rts.controller.commands;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.events.Event;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;
import nl.rug.oop.rts.model.panel.ViewModel;

/**
 * Command to remove the event from a node.
 */
@AllArgsConstructor
public class RemoveNodeEventCommand implements Command {
    /**
     * The graph of the game.
     */
    private final GraphModel graphModel;
    /**
     * The view model of the game.
     */
    private final ViewModel viewModel;
    /**
     * The node from which the event is removed.
     */
    private final Node node;
    /**
     * The event removed.
     */
    private final Event event;

    @Override
    public void execute() {
        graphModel.removeNodeEvent(node, event);
        viewModel.setSelectedNode(node);
        viewModel.updateAllObservers();
    }

    @Override
    public void undo() {
        graphModel.addNodeEvent(node, event);
        viewModel.setSelectedNode(node);
        viewModel.updateAllObservers();
    }

}
