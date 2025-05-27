package nl.rug.oop.rts.view;

import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Node;
import nl.rug.oop.rts.model.ViewModel;
import nl.rug.oop.rts.observer.Observer;

import javax.swing.*;
import java.awt.*;

/**
 * The side menu panel
 */
public class SideMenuPanel extends JPanel implements Observer {
    /**
     * The view model of the game.
     */
    private final ViewModel viewModel;
    /**
     * The controller for the side menu.
     */
    private final SideMenuController sideMenuController;
    /**
     * The title of the panel.
     */
    private final JLabel titleLabel;
    /**
     * The change name field.
     */
    private final JTextField nodeNameField;
    /**
     * The button that allows you to rename the node.
     */
    private final JButton renameNodeButton;

    public SideMenuPanel (ViewModel viewModel, SideMenuController sideMenuController){
        this.viewModel = viewModel;
        this.sideMenuController = sideMenuController;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(200, 700));
        setBackground(Color.DARK_GRAY);

        titleLabel = new JLabel("Nothing Selected.");
        nodeNameField = new JTextField();
        renameNodeButton = new JButton("Rename Node");
        renameNodeButton.addActionListener(e -> {
            sideMenuController.renameSelectedNode(nodeNameField.getText());
        });

        add(titleLabel);
        add(new JLabel("Node Name:"));
        add(nodeNameField);
        add(renameNodeButton);
        update();
    }



    @Override
    public void update() {
        Node node = viewModel.getSelectedNode();
        Edge edge = viewModel.getSelectedEdge();

        if (node != null) {
            titleLabel.setText("Node Selected");
            renameNodeButton.setEnabled(true);
            nodeNameField.setText(node.getName());
        } else if (edge != null) {
            titleLabel.setText("Edge Selected");
            renameNodeButton.setEnabled(false);
            nodeNameField.setText("");
        } else {
            titleLabel.setText("No selection");
            renameNodeButton.setEnabled(false);
            nodeNameField.setText("");
        }
    }

}
