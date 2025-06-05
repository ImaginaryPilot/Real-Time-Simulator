package nl.rug.oop.rts.view.sidepanel;

import nl.rug.oop.rts.controller.SideMenuController;
import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.panel.Node;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel for managing armies on a node, including the list and detailed info.
 */
public class ArmyPanel extends JPanel{
    /**
     * Side Menu Controller.
     * */
    private final SideMenuController sideMenuController;
    /**
     * Current node.
     * */
    private Node currentNode;
    /**
     * The list of the armies listed in a JList.
     * */
    private final DefaultListModel<String> armyListModel;
    /**
     * The visual component that shows all the armies stored.
     * */
    private final JList<String> armyList;
    /**
     * stores list of armies to check their stats.
     * */
    private List<Army> armyObjects;

    /**
     * Constructor to show the panel containing everything related to armies.
     *
     * @param sideMenuController sideMenuController the Army Panel is a part of
     * */
    public ArmyPanel(SideMenuController sideMenuController){
        this.sideMenuController = sideMenuController;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Army list setup
        armyListModel = new DefaultListModel<>();
        armyList = new JList<>(armyListModel);
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

        selectItem();
        addArmyButton(addArmyButton);
        removeArmyButton(removeArmyButton);
    }

    /**
     * select item from JList and show stats.
     * */
    private void selectItem(){
        armyList.addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting() && currentNode != null){
                int selectedIndex = armyList.getSelectedIndex();
                if(selectedIndex >= 0 && armyObjects != null && selectedIndex < armyObjects.size()){
                    showArmyInfo(armyObjects.get(selectedIndex));
                }
            }
        });
    }

    /**
     * button responsible for adding army.
     *
     * @param addArmyButton button to add army
     * */
    private void addArmyButton(JButton addArmyButton){
        addArmyButton.addActionListener(e ->{
            if(currentNode != null){
                sideMenuController.addArmy(currentNode);
            }
        });
    }

    /**
     * button responsible for removing army.
     *
     * @param removeArmyButton button to remove army
     * */
    private void removeArmyButton(JButton removeArmyButton){
        removeArmyButton.addActionListener(e ->{
            if(currentNode != null){
                int selectedIndex = armyList.getSelectedIndex();
                if(selectedIndex == -1){
                    JOptionPane.showMessageDialog(
                            this,
                            "Please select an army to remove",
                            "No Selection",
                            JOptionPane.WARNING_MESSAGE);
                } else{
                    sideMenuController.removeArmy(currentNode, selectedIndex);
                }
            }
        });
    }

    /**
     * refreshing the JList everytime an army was added or removed.
     * */
    private void refreshArmyList() {
        armyListModel.clear();
        if (currentNode != null) {
            List<Army> armies = currentNode.getArmyList();
            armyObjects = armies;
            for (Army army : armies) {
                armyListModel.addElement(army.getFaction().name());
            }
        }
    }

    /**
     * getting and setting the current node.
     *
     * @param node where the army panel will personalised to
     * */
    public void setNode(Node node){
        this.currentNode = node;
        refreshArmyList();
    }

    /**
     * Show the army stats.
     *
     * @param army the army we want the stats in question
     * */
    private void showArmyInfo(Army army) {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Faction: " + army.getFaction().name()));
        panel.add(new JLabel("Units: " + army.getUnits().size()));
        panel.add(new JLabel("Battles Won: " + army.getBattlesWon()));

        JOptionPane.showMessageDialog(this, panel, "Army Info", JOptionPane.INFORMATION_MESSAGE);
    }

}

