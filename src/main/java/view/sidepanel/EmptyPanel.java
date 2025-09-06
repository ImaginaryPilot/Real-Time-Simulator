package nl.rug.oop.rts.view.sidepanel;

import javax.swing.*;
import java.awt.*;

/**
 * The panel for when no node or edge is selected.
 */
public class EmptyPanel extends JPanel {
    /**
     * Constructor for the EmptyPanel class.
     */
    public EmptyPanel() {
        setBackground(Color.DARK_GRAY);
        JLabel label = new JLabel("Nothing selected");
        label.setForeground(Color.WHITE);
        add(label);
    }
}
