package nl.rug.oop.rts.controller.commands;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import nl.rug.oop.rts.model.interfaces.Renamable;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;
import nl.rug.oop.rts.model.panel.ViewModel;

import javax.swing.*;

/**
 * Command for renaming an element.
 */
@Getter
@RequiredArgsConstructor
public class RenameCommand implements Command {
    /**
     * The graph of the game.
     */
    private final GraphModel graphModel;
    /**
     * The view model of the game.
     */
    private final ViewModel viewModel;
    /**
     * The element to rename.
     */
    private final Renamable element;
    /**
     * The old name of the element.
     */
    private final String oldName;
    /**
     * The new name of the element.
     */
    private final String newName;

    /**
     * Whether we should update the sidePanel.
     * System name changes should the sidePanel,
     * user changes not.
     */
    @Setter
    private boolean shouldUpdate = false;

    @Override
    public void execute() {
        setName(element, newName);
    }

    @Override
    public void undo() {
        setName(element, oldName);
    }

    /**
     * Sets the name of the given element to the given name.
     *
     * @param element The element to rename.
     * @param name    The new name of the element.
     */
    private void setName(Renamable element, String name) {
        if (element instanceof Node) {
            graphModel.setNodeName((Node) element, name);
            viewModel.setSelectedNode((Node) element);

        } else if (element instanceof Edge) {
            graphModel.setEdgeName((Edge) element, name);
            viewModel.setSelectedEdge((Edge) element);
        }
        if (shouldUpdate) {
            Runnable doHighlight = viewModel::updateAllObservers;
            SwingUtilities.invokeLater(doHighlight);
        }
    }

}
