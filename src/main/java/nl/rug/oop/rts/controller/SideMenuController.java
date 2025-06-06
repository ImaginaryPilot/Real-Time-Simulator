package nl.rug.oop.rts.controller;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.controller.commands.*;
import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.army.Faction;
import nl.rug.oop.rts.model.events.Event;
import nl.rug.oop.rts.model.interfaces.Renamable;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;
import nl.rug.oop.rts.model.panel.ViewModel;

import javax.swing.*;

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
        Renamable element;
        if (viewModel.getSelectedNode() != null) {
            element = viewModel.getSelectedNode();
        } else if (viewModel.getSelectedEdge() != null) {
            element = viewModel.getSelectedEdge();
        } else {
            System.err.println("No node or edge selected"); // Should never happen
            return;
        }
        mainController.addRenameCommand(element, newName);
    }

    /**
     * Add army.
     *
     * @param node the node
     */
    public void addArmy(Node node) {
        Faction[] factions = Faction.values();

        Faction selectedFaction = (Faction) JOptionPane.showInputDialog(
                null,
                "Select a faction to add an army:",
                "Add Army",
                JOptionPane.PLAIN_MESSAGE,
                null,
                factions,
                factions[0]
        );

        if (selectedFaction != null) {
            Command command = new AddArmyCommand(graphModel, new Army(selectedFaction), node);
            mainController.addCommand(command);
            mainController.executeCommand(command);
            viewModel.updateAllObservers();
        }
    }

    /**
     * Remove army.
     *
     * @param node      the node
     * @param armyIndex the army index
     */
    public void removeArmy(Node node, int armyIndex) {
        if (armyIndex < 0 || armyIndex >= node.getArmyList().size()) {
            JOptionPane.showMessageDialog(null,
                    "No army selected or invalid index.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        node.removeArmy(armyIndex);
        viewModel.updateAllObservers();
    }

    /**
     * Adds event to node.
     *
     * @param node to where it's added to.
     * @param event event to add.
     */
    public void addEvent(Node node, Event event) {
        Command command = new AddNodeEventCommand(graphModel, event, node);
        mainController.addCommand(command);
        mainController.executeCommand(command);
    }

    /**
     * Removes event from node.
     *
     * @param node from where it's removed.
     * @param event event to remove.
     */
    public void removeEvent(Node node, Event event) {
        Command command = new RemoveNodeEventCommand(graphModel, node, event);
        mainController.addCommand(command);
        mainController.executeCommand(command);
    }

    /**
     * Adds event to edge.
     *
     * @param edge to where it's added to.
     * @param event the event to add.
     */
    public void addEvent(Edge edge, Event event) {
        Command command = new AddEdgeEventCommand(graphModel, event, edge);
        mainController.addCommand(command);
        mainController.executeCommand(command);
    }

    /**
     * Removes event from edge.
     *
     * @param edge from where it's removed
     * @param event the event to remove
     */
    public void removeEvent(Edge edge, Event event) {
        Command command = new RemoveEdgeEventCommand(graphModel, edge, event);
        mainController.addCommand(command);
        mainController.executeCommand(command);
    }
}