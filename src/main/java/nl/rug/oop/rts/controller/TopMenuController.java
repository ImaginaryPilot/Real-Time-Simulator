package nl.rug.oop.rts.controller;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.controller.commands.ClearCommand;
import nl.rug.oop.rts.controller.commands.Command;
import nl.rug.oop.rts.controller.commands.SimulationCommand;
import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;
import nl.rug.oop.rts.model.panel.ViewModel;
import nl.rug.oop.rts.model.simulation.Simulation;

import java.util.ArrayList;
import java.util.List;

/**
 * The top menu controller class that holds all the logic of the top buttons in the game.
 */
@AllArgsConstructor
public class TopMenuController {
    private final MainController mainController;
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
        if (graphModel.getEdges().isEmpty() && graphModel.getNodes().isEmpty()) {
            return;
        }
        List<Edge> copiedEdges = new ArrayList<>(graphModel.getEdges());
        List<Node> copiedNodes = new ArrayList<>(graphModel.getNodes());
        Command command = new ClearCommand(graphModel, copiedEdges, copiedNodes);
        mainController.addCommand(command);
        mainController.executeCommand(command);
    }

    /**
     * Simulate.
     * Allows you to simulate the current state.
     */
    public void simulationStep() {
        if (graphModel.getEdges().isEmpty() && graphModel.getNodes().isEmpty()) {
            return;
        }
        List<List<Army>> nodeArmies = new ArrayList<>();
        for (Node node : graphModel.getNodes()) {
            nodeArmies.add(node.copyArmies());
        }

        Simulation simulation = new Simulation(graphModel);
        simulation.simulationStep();

        List<List<Army>> nodeArmiesNew = new ArrayList<>();
        for (Node node : graphModel.getNodes()) {
            nodeArmiesNew.add(node.copyArmies());
        }

        Command command = new SimulationCommand(graphModel, nodeArmies, nodeArmiesNew);
        mainController.addCommand(command);
    }
}