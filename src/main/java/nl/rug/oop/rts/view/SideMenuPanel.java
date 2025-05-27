package nl.rug.oop.rts.view;

import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.Node;
import nl.rug.oop.rts.model.panel.ViewModel;
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
     * The change name field.
     */
    private final JTextField nodeNameField;
    private boolean isVisible = false;

    public SideMenuPanel (ViewModel viewModel, SideMenuController sideMenuController){
        this.viewModel = viewModel;
        this.sideMenuController = sideMenuController;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(200, 700));
        setBackground(Color.DARK_GRAY);

        nodeNameField = new JTextField();
        nodeNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); // height: 30px
        nodeNameField.addActionListener(e -> {
            sideMenuController.renameSelectedNode(nodeNameField.getText());
        });

        add(Box.createVerticalStrut(10));
        add(nodeNameField);
        add(Box.createVerticalStrut(5));
        add(new JSeparator(SwingConstants.HORIZONTAL));
        update();
    }



    @Override
    public void update() {
        Node node = viewModel.getSelectedNode();
        Edge edge = viewModel.getSelectedEdge();
        boolean checkVisibility = node != null || edge != null;

        if (checkVisibility != isVisible) {
            isVisible = checkVisibility;
            this.setVisible(isVisible); // THIS is what makes it work
            this.revalidate();
            this.repaint();
        }

        if (node != null) {
            nodeNameField.setText(node.getName());
        } else if (edge != null) {
            nodeNameField.setText("");
        } else {
            nodeNameField.setText("");
        }
    }

}
