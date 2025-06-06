package nl.rug.oop.rts.controller;

import lombok.Getter;
import nl.rug.oop.rts.controller.commands.Command;
import nl.rug.oop.rts.controller.commands.RenameCommand;
import nl.rug.oop.rts.model.interfaces.Renamable;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.ViewModel;
import nl.rug.oop.rts.model.simulation.Simulation;

import java.util.EmptyStackException;
import java.util.Objects;
import java.util.Stack;

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
     * The simulation.
     */
    private final Simulation simulation;

    /**
     * The stack of commands that have been executed.
     */
    private final Stack<Command> undoStack;

    /**
     * The stack of commands that have been undone.
     */
    private final Stack<Command> redoStack;

    /**
     * Constructor for the MainController class.
     *
     * @param graphModel The graph of the game.
     * @param viewModel  The view of the game.
     */
    public MainController(GraphModel graphModel, ViewModel viewModel) {
        this.graphModel = graphModel;
        this.viewModel = viewModel;
        simulation = new Simulation(graphModel);
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();

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
     * Renames a renamable element.
     * Is either a node or an edge.
     *
     * @param element The element to rename.
     * @param newName The new name of the element.
     */
    public void addRenameCommand(Renamable element, String newName) {
        RenameCommand command;
        try {
            if (undoStack.peek() instanceof RenameCommand lastCmd) {
                if (lastCmd.getElement() == element) {  // Renaming same element
                    undoStack.pop();
                    String originalName = lastCmd.getOldName();
                    if (Objects.equals(originalName, newName)) {
                        command = new RenameCommand(graphModel, viewModel, element, originalName, newName);
                        executeCommand(command);
                        return;
                    }
                    command = new RenameCommand(graphModel, viewModel, element, originalName, newName);
                } else {
                    command = new RenameCommand(graphModel, viewModel, element, element.getName(), newName);
                }
            } else {
                command = new RenameCommand(graphModel, viewModel, element, element.getName(), newName);
            }
        } catch (EmptyStackException e) {
            command = new RenameCommand(graphModel, viewModel, element, element.getName(), newName);
        }
        executeCommand(command);
        command.setShouldUpdate(true);
        addCommand(command);
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
