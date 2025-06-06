package nl.rug.oop.rts.view.sidepanel;

import nl.rug.oop.rts.controller.SideMenuController;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.Node;
import nl.rug.oop.rts.model.panel.ViewModel;
import nl.rug.oop.rts.observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The side menu panel.
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
     * The current panel that gets shown.
     */
    private JPanel currentViewPanel;
    /**
     * The panel for when no node or edge is selected.
     */
    private final JPanel emptyPanel;
    /**
     * The panel for the node properties.
     */
    private final NodePanel nodePanel;
    /**
     * The panel for the edge properties.
     */
    private final EdgePanel edgePanel;
    /**
     * The panel for the logs of the simulation.
     */
    private final LogPanel logPanel;

    /**
     * Constructor for the SideMenuPanel class.
     *
     * @param viewModel          The view model of the game.
     * @param sideMenuController The controller for the side menu.
     */
    public SideMenuPanel(ViewModel viewModel, SideMenuController sideMenuController) {
        this.viewModel = viewModel;
        this.sideMenuController = sideMenuController;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(200, 700));
        setBackground(Color.DARK_GRAY);

        emptyPanel = new EmptyPanel();
        nodePanel = new NodePanel(sideMenuController);
        edgePanel = new EdgePanel(sideMenuController, viewModel);
        logPanel = new LogPanel();
        logPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 150)); // max height 150px, width unlimited

        // Align left
        emptyPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        nodePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        edgePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        logPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        currentViewPanel = emptyPanel;
        add(currentViewPanel, 0);
        add(Box.createVerticalStrut(10));  // spacing
        add(logPanel);
        update();
    }

    @Override
    public void update() {
        remove(currentViewPanel);
        Node node = viewModel.getSelectedNode();
        Edge edge = viewModel.getSelectedEdge();

        if (node != null) {
            nodePanel.setNode(node);
            currentViewPanel = nodePanel;
        } else if (edge != null) {
            edgePanel.setEdge(edge);
            currentViewPanel = edgePanel;
        } else {
            currentViewPanel = emptyPanel;
        }
        add(currentViewPanel, 0);

        List<String> combinedLog = new ArrayList<>();
        if (viewModel.getBattleLog() != null) {
            combinedLog.addAll(viewModel.getBattleLog());
        }
        if (viewModel.getEventLog() != null) {
            combinedLog.addAll(viewModel.getEventLog());
        }

        logPanel.showLog(combinedLog);

        revalidate();
        repaint();
    }
}
