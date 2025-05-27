package nl.rug.oop.rts.view;

import nl.rug.oop.rts.controller.GraphController;
import nl.rug.oop.rts.controller.TopMenuController;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.ViewModel;
import nl.rug.oop.rts.observer.Observer;

import javax.swing.*;
import java.awt.*;

/**
 * The top menu panel of the game.
 * Having all kinds of buttons.
 */
public class TopMenuPanel extends JPanel implements Observer {
    /**
     * The graph controller of the game.
     */
    private final GraphController graphController;
    private final TopMenuController topMenuController;
    /**
     * The graph model of the game.
     */
    private final GraphModel graphModel;
    /**
     * The viewModel of the game (for accessing selected nodes/edges).
     */
    private final ViewModel viewModel;

    private JButton addNode;
    private JButton removeNode;
    private JButton addEdge;
    private JButton removeEdge;


    /**
     * Constructor for the TopMenuPanel class.
     *
     * @param graphController The graph controller of the game.
     *                        Used to add and remove nodes and edges.
     * @param graphModel      The graph model of the game.
     *                        Used to get the map width and height.
     */
    public TopMenuPanel(GraphController graphController, GraphModel graphModel, ViewModel viewModel, TopMenuController topMenuController) {
        this.graphModel = graphModel;
        this.graphController = graphController;
        this.viewModel = viewModel;
        this.topMenuController = topMenuController;
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(1200, 40));
        setupTestButtons();

        setVisible(true);
    }

    /**
     * Set up some buttons to test at the top menu panel.
     */
    private void setupTestButtons() {
        this.addNode = new JButton("Add Node");
        addNode.addActionListener(e -> {
            topMenuController.addNodeButton();
        });

        this.removeNode = new JButton("Remove Node");
        removeNode.addActionListener(e -> {
            topMenuController.removeNodeButton();
        });

        this.addEdge = new JButton("Add Edge");
        addEdge.addActionListener(e -> {
            topMenuController.addEdgeButton();
        });

        this.removeEdge = new JButton("Remove Edge");
        removeEdge.addActionListener(e -> {
            topMenuController.removeEdgeButton();
        });

        JButton clear = new JButton("Clear");
        clear.addActionListener(e -> {
            topMenuController.clearButton();
        });

        JButton generateRandomMap = new JButton("Generate Random Map");
        generateRandomMap.addActionListener(e -> {
            System.out.println("Map generated.");
            graphController.generateTest();
        });
        add(addNode);
        add(removeNode);
        add(addEdge);
        add(removeEdge);
        add(generateRandomMap);
        add(clear);
        update();
    }

    @Override
    public void update() {
        if (viewModel.isCreateNodeMode()) {
            addNode.setBackground(Color.RED);
        } else {
            addNode.setBackground(new JButton().getBackground());
        }
        if (viewModel.isCreateEdgeMode()) {
            addEdge.setBackground(Color.RED);
        } else {
            addEdge.setBackground(new JButton().getBackground());
        }
        if (viewModel.getSelectedNode() == null) {
            removeNode.setEnabled(false);
            addEdge.setEnabled(false);

        } else {
            removeNode.setEnabled(true);
            addEdge.setEnabled(true);
        }
        removeEdge.setEnabled(viewModel.getSelectedEdge() != null);
    }

}