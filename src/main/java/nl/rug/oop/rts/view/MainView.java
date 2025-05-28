package nl.rug.oop.rts.view;

import lombok.Getter;
import nl.rug.oop.rts.observer.Observer;
import nl.rug.oop.rts.view.optionMenu.SideMenuPanel;

import javax.swing.*;
import java.awt.*;

/**
 * The main menu of the game.
 */
@Getter
public class MainView extends JFrame implements Observer {
    /**
     * The graph panel of the game.
     */
    private final GraphPanel graphPanel;
    /**
     * The top menu panel of the game.
     */
    private final TopMenuPanel topMenuPanel;

    private final SideMenuPanel sideMenuPanel;

    /**
     * Constructor for the MainView class.
     *
     * @param graphPanel    The graph panel of the game.
     * @param topMenuPanel  The top menu panel of the game.
     * @param sideMenuPanel
     */
    public MainView(GraphPanel graphPanel, TopMenuPanel topMenuPanel, SideMenuPanel sideMenuPanel) {
        this.graphPanel = graphPanel;
        this.topMenuPanel = topMenuPanel;
        this.sideMenuPanel = sideMenuPanel;

        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(topMenuPanel, BorderLayout.NORTH);
        add(graphPanel, BorderLayout.CENTER);
        add(sideMenuPanel, BorderLayout.WEST);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void update() {
        repaint();
    }

}