package nl.rug.oop.rts.controller;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;
import nl.rug.oop.rts.model.panel.ViewModel;

/**
 * The top menu controller class that holds all the logic of the top buttons in the game.
 */
@AllArgsConstructor
public class TopMenuController {
    /**
     * The graph controller of the game.
     */
    private final GraphController graphController;
    /**
     * The view model of the game.
     */
    private final ViewModel viewModel;
    /**
     * The graph model of the game.
     */
    private final GraphModel graphModel;

    /**
     * Handles the click on the "add node" button.
     * Toggles the "create node" mode on and off.
     */
    public void addNodeButton() {
        viewModel.toggleCreateNodeMode();
    }

    /**
     * Handles the click on the "remove node" button.
     * Removes the selected node from the graph.
     */
    public void removeNodeButton() {
        Node selectedNode = viewModel.getSelectedNode();
        if (selectedNode != null) {
            graphController.removeNode(selectedNode);
            viewModel.setSelectedNode(null);
        }
    }

    /**
     * Handles the click on the "add edge" button.
     * Toggles the "create edge" mode on and off.
     */
    public void addEdgeButton() {
        viewModel.toggleCreateEdgeMode();
    }

    /**
     * Handles the click on the "remove edge" button.
     * Removes the selected edge from the graph.
     */
    public void removeEdgeButton() {
        Edge selectedEdge = viewModel.getSelectedEdge();
        if (selectedEdge != null) {
            graphController.removeEdge(selectedEdge);
            viewModel.setSelectedEdge(null);
        }
    }

    /**
     * Handles the click on the "clear" button.
     * Removes all nodes and edges from the graph.
     */
    public void clearButton() {
        while (!graphModel.getNodes().isEmpty()) {
            graphController.removeNode(graphModel.getNodes().get(0));
        }
    }
}