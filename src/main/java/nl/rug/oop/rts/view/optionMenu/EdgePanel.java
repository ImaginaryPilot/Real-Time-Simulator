package nl.rug.oop.rts.view.optionMenu;

import nl.rug.oop.rts.controller.SideMenuController;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.ViewModel;

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
     * The label for the node A.
     */
    private final JLabel nodeALabel;
    /**
     * The label for the node B.
     */
    private final JLabel nodeBLabel;

    /**
     * Constructor for the EdgePanel class.
     *
     * @param sideMenuController The controller for the side menu.
     * @param viewModel          The view model of the game.
     */
    EdgePanel(SideMenuController sideMenuController, ViewModel viewModel) {
        this.sideMenuController = sideMenuController;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.DARK_GRAY);
        edgeNameField = new NameTextField(sideMenuController);

        add(Box.createVerticalStrut(10));
        add(edgeNameField);
        add(Box.createVerticalStrut(5));
        add(new JSeparator(SwingConstants.HORIZONTAL));

        JLabel label = new JLabel("Edge properties");
        label.setForeground(Color.WHITE);
        add(label);
        add(Box.createVerticalStrut(10));
        add(new JSeparator(SwingConstants.HORIZONTAL));
        add(Box.createVerticalStrut(10));
        add(new JSeparator(SwingConstants.HORIZONTAL));
        nodeALabel = new JLabel("Node 1: ");

        nodeALabel.setForeground(Color.WHITE);
        add(nodeALabel);
        add(Box.createVerticalStrut(5));
        add(new JSeparator(SwingConstants.HORIZONTAL));
        add(Box.createVerticalStrut(10));
        add(new JSeparator(SwingConstants.HORIZONTAL));
        nodeBLabel = new JLabel("Node 2: ");
        nodeBLabel.setForeground(Color.WHITE);
        add(nodeBLabel);

    }

    /**
     * Sets the edge.
     *
     * @param edge The edge to set.
     */
    public void setEdge(Edge edge) {
        edgeNameField.setValidName(edge.getName());
        nodeALabel.setText("Node 1: " + edge.getNodeA().getName());
        nodeBLabel.setText("Node 2: " + edge.getNodeB().getName());
        //System.out.println("new edge with name: " + edge.getName());
    }
}
