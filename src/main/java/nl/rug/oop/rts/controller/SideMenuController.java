package nl.rug.oop.rts.controller;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;
import nl.rug.oop.rts.model.panel.ViewModel;

/**
 * The side menu controller class that holds all the logic of the side buttons in the game.
 */
@AllArgsConstructor
public class SideMenuController {
    /**
     * The main controller of the game.
     */
    private final MainController mainController;
    /**
     * The view model of the game.
     */
    private final ViewModel viewModel;
    /**
     * The graph model of the game.
     */
    private final GraphModel graphModel;

    /**
     * Renames the selected node or edge.
     * Depending on which node or edge is selected, the method will rename the node or edge.
     *
     * @param newName The new name of the node or edge.
     */
    public void rename(String newName) {
        if (viewModel.getSelectedNode() != null) {
            renameSelectedNode(newName);
        } else if (viewModel.getSelectedEdge() != null) {
            renameSelectedEdge(newName);
        } else {
            System.err.println("No node or edge selected");
        }
    }

    /**
     * Renames the selected node.
     *
     * @param newName The new name of the node.
     */
    public void renameSelectedNode(String newName) {
        Node node = viewModel.getSelectedNode();
        if (node != null) {
            mainController.addRenameNodeCommand(node, newName);
        }
    }

    /**
     * Renames the selected edge.
     *
     * @param newName The new name of the edge.
     */
    public void renameSelectedEdge(String newName) {
        Edge edge = viewModel.getSelectedEdge();
        if (edge != null) {
            mainController.addRenameEdgeCommand(edge, newName);
        }
    }
}