package nl.rug.oop.rts.view.optionMenu;

import nl.rug.oop.rts.controller.SideMenuController;
import nl.rug.oop.rts.model.panel.Node;

import javax.swing.*;
import java.awt.*;

/**
 * The panel for the node properties.
 */
class NodePanel extends JPanel {
    /**
     * The name field for the node.
     */
    private final NameTextField nodeNameField;
    /**
     * The army panel for the node.
     * */
    private final ArmyPanel armyPanel;
    /**
     * The controller for the side menu.
     */
    private final SideMenuController sideMenuController;
    /**
     * Current Node.
     * */
    private Node currentNode;

    /**
     * Constructor for the NodePanel class.
     *
     * @param sideMenuController The controller for the side menu.
     */
    NodePanel(SideMenuController sideMenuController) {
        this.sideMenuController = sideMenuController;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.DARK_GRAY);
        nodeNameField = new NameTextField(sideMenuController);
        armyPanel = new ArmyPanel(sideMenuController);

        nodeNameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        armyPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(Box.createVerticalStrut(10));
        add(nodeNameField);
        add(Box.createVerticalStrut(5));
        add(new JSeparator(SwingConstants.HORIZONTAL));

        add(armyPanel);
    }

    /**
     * Sets the node.
     *
     * @param node The node to set.
     */
    public void setNode(Node node) {
        this.currentNode = node;
        nodeNameField.setValidName(node.getName());
        armyPanel.setNode(node);
        //System.out.println("new node with name: " + node.getName());
    }
}