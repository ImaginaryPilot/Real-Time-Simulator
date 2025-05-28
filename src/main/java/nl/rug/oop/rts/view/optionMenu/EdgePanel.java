package nl.rug.oop.rts.view.optionMenu;

import nl.rug.oop.rts.controller.SideMenuController;
import nl.rug.oop.rts.model.panel.Edge;

import javax.swing.*;
import java.awt.*;

/**
 * The panel for the edge properties.
 */

class EdgePanel extends JPanel {
    /**
     * The name field for the edge.
     */
    private final NameTextField edgeNameField;
    /**
     * The controller for the side menu.
     */
    private final SideMenuController sideMenuController;

    /**
     * Constructor for the EdgePanel class.
     *
     * @param sideMenuController The controller for the side menu.
     */
    public EdgePanel(SideMenuController sideMenuController) {
        this.sideMenuController = sideMenuController;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.DARK_GRAY);
        edgeNameField = new NameTextField(sideMenuController);

        add(Box.createVerticalStrut(10));
        add(edgeNameField);
        add(Box.createVerticalStrut(5));
        add(new JSeparator(SwingConstants.HORIZONTAL));
    }

    /**
     * Sets the edge.
     *
     * @param edge The edge to set.
     */
    public void setEdge(Edge edge) {
        edgeNameField.setValidName(edge.getName());
        System.out.println("new edge with name: " + edge.getName());
    }
}
