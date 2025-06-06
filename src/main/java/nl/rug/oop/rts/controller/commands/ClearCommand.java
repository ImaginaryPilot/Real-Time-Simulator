package nl.rug.oop.rts.controller.commands;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;
import nl.rug.oop.rts.model.panel.ViewModel;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ClearCommand implements Command {
    private final GraphModel graphModel;
    private final ViewModel viewModel;
    private final List<Edge> edges;
    private final List<Node> nodes;

    @Override
    public void execute() {
        graphModel.setNodes(new ArrayList<>());
        graphModel.setEdges(new ArrayList<>());
        viewModel.setSelectedNode(null);
        viewModel.setSelectedEdge(null);
    }

    @Override
    public void undo() {
        graphModel.setNodes(nodes);
        graphModel.setEdges(edges);
    }
}
