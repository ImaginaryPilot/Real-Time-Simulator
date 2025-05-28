package nl.rug.oop.rts.view;

import lombok.Getter;
import nl.rug.oop.rts.controller.*;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.ViewModel;
import nl.rug.oop.rts.view.optionMenu.SideMenuPanel;

/**
 * The main class that starts the game.
 */
@Getter
public class Game {
    /**
     * The graph model of the game.
     * Stores all the information of the graph.
     * All the edges and nodes are stored in this model.
     */
    private final GraphModel graphModel;
    /**
     * The view model of the game.
     * Stores all the information of the view.
     * The zoom level, view position, etc. are stored in this model.
     */
    private final ViewModel viewModel;

    /**
     * The main controller of the game.
     */
    private final MainController mainController;
    /**
     * The graph controller of the game.
     */
    private final GraphController graphController;
    /**
     * The mouse controller of the game.
     */
    private final MouseController mouseController;

    private final TopMenuController topMenuController;

    private final SideMenuController sideMenuController;
    /**
     * The top menu panel of the game.
     * Shows all kinds of buttons.
     */
    private final TopMenuPanel topMenuPanel;
    /**
     * The side menu panel of the game
     * Shows information about the node and buttons that influence it.
     */
    private final SideMenuPanel sideMenuPanel;
    /**
     * The graph panel of the game.
     * Shows the map with all the nodes and edges.
     */
    private final GraphPanel graphPanel;
    /**
     * The main view of the game.
     * Shows all the panels.
     */
    private final MainView mainView;

    /**
     * Constructor for the Game class.
     */
    public Game() {
        this.graphModel = new GraphModel();
        this.viewModel = new ViewModel();

        this.mainController = new MainController(graphModel, viewModel);
        this.graphController = new GraphController(mainController, graphModel);
        this.mouseController = new MouseController(mainController, viewModel, graphModel, graphController);
        this.topMenuController = new TopMenuController(graphController, viewModel, graphModel);
        this.sideMenuController = new SideMenuController(viewModel, graphModel);

        this.graphPanel = new GraphPanel(viewModel, graphModel);
        this.topMenuPanel = new TopMenuPanel(graphController, graphModel, viewModel, topMenuController);
        this.sideMenuPanel = new SideMenuPanel(viewModel, sideMenuController);

        this.mainView = new MainView(graphPanel, topMenuPanel, sideMenuPanel);

        graphPanel.addMouseListener(mouseController);
        graphPanel.addMouseMotionListener(mouseController);

        graphPanel.observe(viewModel);
        graphPanel.observe(graphModel);
        topMenuPanel.observe(viewModel);
        sideMenuPanel.observe(viewModel);
    }

    /**
     * Starts the game by showing the main menu.
     */
    public void start() {
        mainView.setVisible(true);
    }
}
