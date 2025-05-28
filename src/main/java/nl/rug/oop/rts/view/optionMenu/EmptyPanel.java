package nl.rug.oop.rts.view.optionMenu;

import javax.swing.*;
import java.awt.*;

public class EmptyPanel extends JPanel {
    public EmptyPanel() {
        setBackground(Color.DARK_GRAY);
        JLabel label = new JLabel("Nothing selected");
        label.setForeground(Color.WHITE);
        add(label);
    }
}
