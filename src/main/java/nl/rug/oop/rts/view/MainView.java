package nl.rug.oop.rts.view;

import nl.rug.oop.rts.observer.Observer;

import javax.swing.*;

/**
 * The main menu of the game.
 */
public class MainView extends JFrame implements Observer {
    /**
     * The graph panel of the game.
     */
    private final GraphPanel graphPanel;
    /**
     * The top menu panel of the game.
     */
    private final TopMenuPanel topMenuPanel;

    /**
     * Constructor for the MainView class.
     * @param graphPanel The graph panel of the game.
     * @param topMenuPanel The top menu panel of the game.
     */
    public MainView(GraphPanel graphPanel, TopMenuPanel topMenuPanel) {
        this.graphPanel = graphPanel;
        this.topMenuPanel = topMenuPanel;

        setLayout(null);
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(graphPanel);
        add(topMenuPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void update() {
        /*
         * Not sure if this is updating enough.
         */
        repaint();
    }

}