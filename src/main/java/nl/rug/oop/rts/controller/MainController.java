package nl.rug.oop.rts.controller;

import lombok.Getter;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.View;

/**
 * The main controller class that holds all the logic of the game.
 */
@Getter
public class MainController {
    /**
     * The graph of the game.
     */
    private final Graph graph;
    /**
     * The view of the game.
     */
    private final View view;
    /**
     * The graph controller of the game.
     */
    private final GraphController graphController;
    /*
    basicly everything that is needed to run the game.
     */

    /**
     * Constructor for the MainController class.
     */
    public MainController() {
        this.graph = new Graph();
        this.view = new View();
        this.graphController = new GraphController(this);

    }
    /*
    Some methods for executing commands.
    Using stacks to keep track of the commands and undos.
     */

}
