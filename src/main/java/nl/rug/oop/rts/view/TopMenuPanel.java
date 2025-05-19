package nl.rug.oop.rts.view;

import javax.swing.*;
import java.awt.*;

/**
 * The top menu panel of the game.
 * Having all kinds of buttons.
 */
public class TopMenuPanel extends JPanel {
    /**
     * The graph panel of the game.
     */
    private final GraphPanel graphPanel;

    /**
     * Constructor for the TopMenuPanel class.
     * @param graphPanel The graph panel of the game.
     */
    public TopMenuPanel(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;

        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        setBackground(Color.DARK_GRAY);
        setBounds(0, 0, 1200, 40);

        setupButtons();

        setVisible(true);
    }

    /**
     * Sets up the buttons of the top menu panel.
     */
    private void setupButtons() {
        JButton button = new JButton("Button 1");
        button.addActionListener(e -> System.out.println("Button 1 clicked"));

        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button buttonaosndf 3");

        add(button);
        add(button2);
        add(button3);
    }

}