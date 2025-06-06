package nl.rug.oop.rts.view.sidepanel;

import nl.rug.oop.rts.controller.SideMenuController;
import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.panel.Node;
import nl.rug.oop.rts.view.utilities.NameTextField;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The panel for the node properties.
 */
class NodePanel extends JPanel {
    /**
     * The name field for the node.
     */
    private final NameTextField nodeNameField;
    /**
     * The controller for the side menu.
     */
    private final SideMenuController sideMenuController;
    /**
     * current node.
     */
    private Node currentNode;
    /**
     * The list of the armies listed in a JList.
     */
    private final DefaultListModel<String> armyListModel;
    /**
     * The visual component that shows all the armies stored.
     */
    private final JList<String> armyList;
    /**
     * stores list of armies to check their stats.
     */
    private java.util.List<Army> armyObjects;

    /**
     * Constructor for the NodePanel class.
     *
     * @param sideMenuController The controller for the side menu.
     */
    NodePanel(SideMenuController sideMenuController) {
        this.sideMenuController = sideMenuController;

        // Army list setup
        armyListModel = new DefaultListModel<>();
        armyList = new JList<>(armyListModel);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.DARK_GRAY);
        nodeNameField = new NameTextField(sideMenuController);

        nodeNameField.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(Box.createVerticalStrut(10));
        add(nodeNameField);
        add(Box.createVerticalStrut(5));
        add(new JSeparator(SwingConstants.HORIZONTAL));

        armyPanel();
    }

    /**
     * ArmyPanel.
     */
    private void armyPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        armyObjects = new ArrayList<>();
        armyList.setVisibleRowCount(5);
        JScrollPane armyScrollPane = new JScrollPane(armyList);
        armyScrollPane.setPreferredSize(new Dimension(180, 100));

        // Title of the panel
        JLabel titleLabel = new JLabel("Armies");
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(titleLabel);

        // The button panel for the 2 buttons to add and remove armies
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JButton addArmyButton = new JButton("+");
        JButton removeArmyButton = new JButton("-");

        buttonPanel.add(addArmyButton);
        buttonPanel.add(removeArmyButton);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.setMaximumSize(buttonPanel.getPreferredSize());
        add(buttonPanel);

        armyScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(armyScrollPane);

        addArmyButton(addArmyButton);
        removeArmyButton(removeArmyButton);
    }

    /**
     * button responsible for adding army.
     *
     * @param addArmyButton button to add army
     */
    private void addArmyButton(JButton addArmyButton) {
        addArmyButton.addActionListener(e -> {
            if (currentNode != null) {
                sideMenuController.addArmy(currentNode);
            }
        });
    }

    /**
     * button responsible for removing army.
     *
     * @param removeArmyButton button to remove army
     */
    private void removeArmyButton(JButton removeArmyButton) {
        removeArmyButton.addActionListener(e -> {
            if (currentNode != null) {
                int selectedIndex = armyList.getSelectedIndex();
                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Please select an army to remove\n",
                            "No Selection",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    sideMenuController.removeArmy(currentNode, selectedIndex);
                }
            }
        });
    }

    /**
     * refreshing the JList everytime an army was added or removed.
     */
    private void refreshArmyList() {
        armyListModel.clear();
        if (currentNode != null) {
            List<Army> armies = currentNode.getArmyList();
            armyObjects = armies;
            for (Army army : armies) {
                armyListModel.addElement(army.getFaction().name() + " (Wins: " + army.getBattlesWon() + ")");
            }
        }
    }

    /**
     * Sets the node.
     *
     * @param node The node to set.
     */
    public void setNode(Node node) {
        this.currentNode = node;
        nodeNameField.setValidName(node.getName());
        refreshArmyList();
    }
}