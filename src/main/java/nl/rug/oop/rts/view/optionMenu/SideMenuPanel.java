package nl.rug.oop.rts.view.optionMenu;

import nl.rug.oop.rts.controller.SideMenuController;
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

    private final JPanel contentPanel;
    private final CardLayout cardLayout;

    // Different "views" we can show
    private final JPanel emptyPanel;
    private final NodePanel nodePanel;
    private final EdgePanel edgePanel;

    public SideMenuPanel(ViewModel viewModel, SideMenuController sideMenuController) {
        this.viewModel = viewModel;
        this.sideMenuController = sideMenuController;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(200, 700));
        setBackground(Color.DARK_GRAY);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(Color.DARK_GRAY);

        // Create the different views
        emptyPanel = new EmptyPanel();
        nodePanel = new NodePanel(sideMenuController);
        edgePanel = new EdgePanel(sideMenuController);

        // Add all views to the card layout
        contentPanel.add(emptyPanel, "EMPTY");
        contentPanel.add(nodePanel, "NODE");
        contentPanel.add(edgePanel, "EDGE");

        add(contentPanel, BorderLayout.CENTER);

        update();
    }


    @Override
    public void update() {
        Node node = viewModel.getSelectedNode();
        Edge edge = viewModel.getSelectedEdge();

        if (node != null) {
            nodePanel.setNode(node);
            cardLayout.show(contentPanel, "NODE");
        } else if (edge != null) {
            edgePanel.setEdge(edge);
            cardLayout.show(contentPanel, "EDGE");
        } else {
            cardLayout.show(contentPanel, "EMPTY");
        }
    }

}
