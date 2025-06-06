package nl.rug.oop.rts.view.sidepanel;

import nl.rug.oop.rts.controller.SideMenuController;
import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.army.Unit;
import nl.rug.oop.rts.model.panel.Node;
import nl.rug.oop.rts.view.utilities.NameTextField;

import javax.swing.*;
import java.awt.*;
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
     * Selected index from JList for removal or stats.
     */
    private int selectedIndex;

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
        JButton statsButton = new JButton("Stats");
        statsButton.setEnabled(false);
        removeArmyButton.setEnabled(false);

        buttonPanel.add(addArmyButton);
        buttonPanel.add(removeArmyButton);
        buttonPanel.add(statsButton);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.setMaximumSize(buttonPanel.getPreferredSize());
        add(buttonPanel);

        armyScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(armyScrollPane);

        armyList.addListSelectionListener(e -> {
            boolean selected = armyList.getSelectedIndex() != -1;
            selectedIndex = armyList.getSelectedIndex();
            removeArmyButton.setEnabled(selected);
            statsButton.setEnabled(selected);
        });
        addArmyButton(addArmyButton);
        removeArmyButton(removeArmyButton, selectedIndex);
        showArmyStats(statsButton, selectedIndex);
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
     * @param selectedIndex    the selected index
     */
    private void removeArmyButton(JButton removeArmyButton, int selectedIndex) {
        removeArmyButton.addActionListener(e -> {
            if (currentNode != null) {
                sideMenuController.removeArmy(currentNode, selectedIndex);
            }
        });
    }

    /**
     * Show some information about the army.
     *
     * @param index index of the army in the armyList.
     */
    private void armyInfo(int index) {
        if (currentNode != null) {
            Army selectedArmy = currentNode.getArmyList().get(index);
            JPanel statsPanel = new JPanel();
            statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
            statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JTextArea textArea = new JTextArea(selectedArmy.getStats());
            textArea.setEditable(false);
            textArea.setBackground(null);
            textArea.setAlignmentX(Component.LEFT_ALIGNMENT);

            JList<Unit> unitJlist = new JList<>(selectedArmy.getUnits().toArray(new Unit[0]));
            unitJlist.setVisibleRowCount(8);
            JScrollPane scrollPane = new JScrollPane(unitJlist);
            scrollPane.setPreferredSize(new Dimension(250, 150));
            scrollPane.setBorder(BorderFactory.createTitledBorder("Units"));

            statsPanel.add(textArea);
            statsPanel.add(Box.createVerticalStrut(10));
            statsPanel.add(scrollPane);

            JOptionPane.showMessageDialog(
                    this,
                    statsPanel,
                    "Army Stats",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    /**
     * Show some army stats.
     *
     * @param statsButton   the clickable button
     * @param selectedIndex the index of the selected army
     */
    private void showArmyStats(JButton statsButton, int selectedIndex) {
        statsButton.addActionListener(e -> {
            armyInfo(selectedIndex);
        });
    }

    /**
     * refreshing the JList everytime an army was added or removed.
     */
    private void refreshArmyList() {
        armyListModel.clear();
        if (currentNode != null) {
            List<Army> armies = currentNode.getArmyList();
            for (Army army : armies) {
                armyListModel.addElement(army.getFaction().name());
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