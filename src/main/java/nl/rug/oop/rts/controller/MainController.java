package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.controller.commands.Command;
import nl.rug.oop.rts.model.GraphModel;
import nl.rug.oop.rts.model.ViewModel;

/**
 * The main controller class that holds all the logic of the game.
 */
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
     * Constructor for the MainController class.
     *
     * @param graphModel The graph of the game.
     * @param viewModel  The view of the game.
     */
    public MainController(GraphModel graphModel, ViewModel viewModel) {
        this.graphModel = graphModel;
        this.viewModel = viewModel;

    }

    /**
     * Executes a command and adds it to the command stack.
     *
     * @param command The command to execute.
     */
    public void createCommand(Command command) {
        command.redo();
        /*
        add on stack
         */
    }

    /**
     * Undo.
     */
    public void undo() {
        /*
        undo last command
         */
    }

    /**
     * Redo.
     */
    public void redo() {
        /*
        redo last command
         */
    }

    /*
    Some methods for executing commands.
    Using stacks to keep track of the commands and undos.
     */

}
