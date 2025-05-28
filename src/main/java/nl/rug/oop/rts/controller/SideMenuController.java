package nl.rug.oop.rts.controller;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;
import nl.rug.oop.rts.model.panel.ViewModel;


@AllArgsConstructor
public class SideMenuController {
    private final ViewModel viewModel;
    private final GraphModel graphModel;

    public void rename(String newName) {
        if (viewModel.getSelectedNode() != null) {
            renameSelectedNode(newName);
        } else if (viewModel.getSelectedEdge() != null) {
            renameSelectedEdge(newName);
        } else {
            System.err.println("No node or edge selected");
        }
    }

    public void renameSelectedNode(String newName) {
        Node node = viewModel.getSelectedNode();
        if (node != null) {
            graphModel.setNodeName(node, newName);
        }
    }

    public void renameSelectedEdge(String text) {
        Edge edge = viewModel.getSelectedEdge();
        if (edge != null) {
            System.out.println("rename edge");
            graphModel.setEdgeName(edge, text);
        }
    }
}