package nl.rug.oop.rts.controller;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.GraphModel;
import nl.rug.oop.rts.model.Node;
import nl.rug.oop.rts.model.ViewModel;

@AllArgsConstructor
public class TopMenuController {
    private final GraphController graphController;
    private final ViewModel viewModel;
    private final GraphModel graphModel;

    public void addNodeButton() {
        viewModel.toggleCreateNodeMode();
    }

    public void removeNodeButton() {
        Node selectedNode = viewModel.getSelectedNode();
        if (selectedNode != null) {
            graphController.removeNode(selectedNode);
            viewModel.setSelectedNode(null);
        }
    }

    public void addEdgeButton() {
        viewModel.toggleCreateEdgeMode();
    }

    public void removeEdgeButton() {
        Edge selectedEdge = viewModel.getSelectedEdge();
        if (selectedEdge != null) {
            graphController.removeEdge(selectedEdge);
            viewModel.setSelectedEdge(null);
        }
    }

    public void clearButton() {
        while (!graphModel.getNodes().isEmpty()) {
            graphController.removeNode(graphModel.getNodes().get(0));
        }
    }
}
