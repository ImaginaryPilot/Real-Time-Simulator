package nl.rug.oop.rts.controller;

import lombok.Getter;
import nl.rug.oop.rts.controller.commands.ChangeEdgeNameCommand;
import nl.rug.oop.rts.controller.commands.ChangeNodeNameCommand;
import nl.rug.oop.rts.controller.commands.Command;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;
import nl.rug.oop.rts.model.panel.ViewModel;

import java.util.EmptyStackException;
import java.util.Objects;

/**
 * The main controller class that holds all the logic of the game.
 */
@Getter
public class MainController {
    /**
     * The graph of the game.
     */
    private final GraphModel graphModel;
    /**
     * The view of the game.
     */
    private final ViewModel viewModel;

    /**
     * The stack of commands that have been executed.
     */
    private final SizedStack<Command> undoStack;

    /**
     * The stack of commands that have been undone.
     */
    private final SizedStack<Command> redoStack;

    /**
     * Constructor for the MainController class.
     *
     * @param graphModel The graph of the game.
     * @param viewModel  The view of the game.
     */
    public MainController(GraphModel graphModel, ViewModel viewModel) {
        this.graphModel = graphModel;
        this.viewModel = viewModel;
        this.undoStack = new SizedStack<>(10);
        this.redoStack = new SizedStack<>(10);

    }

    /**
     * Adds a command to the stack of commands.
     * If the undo stack is full, the oldest command will be removed from the undo stack.
     * The redo stack will be cleared.
     *
     * @param command The command to add.
     */
    public void addCommand(Command command) {
        undoStack.push(command);
        redoStack.clear();
        System.out.println("Added command to stack: " + command);
    }

    /**
     * Executes a command.
     *
     * @param command The command to execute.
     */
    public void executeCommand(Command command) {
        command.execute();
    }

    /**
     * Renames a node.
     *
     * @param node    The node to rename.
     * @param newName The new name of the node.
     */
    public void addRenameNodeCommand(Node node, String newName) {
        Command command;
        try {
            if (undoStack.peek().getClass() == ChangeNodeNameCommand.class &&
                    ((ChangeNodeNameCommand) undoStack.peek()).getNode().equals(node)) { // renaming the same node
                ChangeNodeNameCommand oldChangeNodeNameCommand = (ChangeNodeNameCommand) undoStack.pop();
                String oldName = oldChangeNodeNameCommand.getOldName();
                if (Objects.equals(oldName, newName)) {
                    command = new ChangeNodeNameCommand(node, oldName, newName, graphModel, viewModel);
                    executeCommand(command);
                    return;
                }
                command = new ChangeNodeNameCommand(node, oldName, newName, graphModel, viewModel);
            } else {
                command = new ChangeNodeNameCommand(node, node.getName(), newName, graphModel, viewModel);
            }
        } catch (EmptyStackException e) {
            command = new ChangeNodeNameCommand(node, node.getName(), newName, graphModel, viewModel);
        }
        addCommand(command);
        executeCommand(command);
    }

    /**
     * Renames a edge.
     *
     * @param edge    The edge to rename.
     * @param newName The new name of the edge.
     */
    public void addRenameEdgeCommand(Edge edge, String newName) {
        Command command;
        try {
            if (undoStack.peek().getClass() == ChangeEdgeNameCommand.class &&
                    ((ChangeEdgeNameCommand) undoStack.peek()).getEdge().equals(edge)) { // renaming the same edge
                ChangeEdgeNameCommand oldChangeEdgeNameCommand = (ChangeEdgeNameCommand) undoStack.pop();
                String oldName = oldChangeEdgeNameCommand.getOldName();
                if (Objects.equals(oldName, newName)) {
                    command = new ChangeEdgeNameCommand(edge, oldName, newName, graphModel, viewModel);
                    executeCommand(command);
                    return;
                }
                command = new ChangeEdgeNameCommand(edge, oldName, newName, graphModel, viewModel);
            } else {
                command = new ChangeEdgeNameCommand(edge, edge.getName(), newName, graphModel, viewModel);
            }
        } catch (EmptyStackException e) {
            command = new ChangeEdgeNameCommand(edge, edge.getName(), newName, graphModel, viewModel);
        }
        addCommand(command);
        executeCommand(command);
    }

    /**
     * Undo.
     * If there is a command to undo, the command will be undone and added to the redo stack.
     */
    public void undo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            redoStack.push(command);
            command.undo();
            System.out.println(command);
        }
    }

    /**
     * Redo.
     * If there is a command to redo, the command will be redone and added to the undo stack.
     */
    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            undoStack.push(command);
            command.execute();
        }
    }
}
