package nl.rug.oop.rts.view;

import nl.rug.oop.rts.controller.GraphController;
import nl.rug.oop.rts.controller.MainController;
import nl.rug.oop.rts.controller.TopMenuController;
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
    /**
     * The controller for the top menu.
     */
    private final TopMenuController topMenuController;
    /**
     * The main controller of the game.
     * Used for the undo/redo buttons.
     */
    private final MainController mainController;
    /**
     * The viewModel of the game (for accessing selected nodes/edges).
     */
    private final ViewModel viewModel;

    /**
     * Button for adding a node.
     */
    private JButton addNode;
    /**
     * Button for removing a node.
     */
    private JButton removeNode;
    /**
     * Button for adding an edge.
     */
    private JButton addEdge;
    /**
     * Button for removing an edge.
     */
    private JButton removeEdge;

    /**
     * Constructor for the TopMenuPanel class.
     *
     * @param graphController   The graph controller of the game.
     * @param viewModel         The viewModel of the game.
     * @param topMenuController The controller for the top menu.
     * @param mainController    The main controller of the game.
     */
    public TopMenuPanel(
            GraphController graphController,
            ViewModel viewModel,
            TopMenuController topMenuController,
            MainController mainController) {

        this.graphController = graphController;
        this.viewModel = viewModel;
        this.topMenuController = topMenuController;
        this.mainController = mainController;
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(1200, 40));
        setButtons();
        setupButtons2();
        setVisible(true);
    }

    /**
     * Set up the buttons at the top menu.
     */
    private void setButtons() {
        this.addNode = new JButton("Add Location");
        addNode.addActionListener(e -> {
            topMenuController.addNodeButton();
        });
        add(addNode);
        this.removeNode = new JButton("Remove Selected Location");
        removeNode.addActionListener(e -> {
            topMenuController.removeNodeButton();
        });
        add(removeNode);
        this.addEdge = new JButton("Add Route");
        addEdge.addActionListener(e -> {
            topMenuController.addEdgeButton();
        });
        add(addEdge);
        this.removeEdge = new JButton("Remove Selected Route");
        removeEdge.addActionListener(e -> {
            topMenuController.removeEdgeButton();
        });
        add(removeEdge);
        add(new JButton("Generate Random Map") {{
                addActionListener(e -> graphController.generateTest());
            }});
        add(new JButton("Clear") {{
                addActionListener(e -> topMenuController.clearButton());
            }});
        add(new JButton("Undo") {{
                addActionListener(e -> mainController.undo());
            }});
        add(new JButton("Redo") {{
                addActionListener(e -> mainController.redo());
            }});

        update();
    }
    /**
     * Set up the buttons at the top menu.
     * Part 2
     */

    private void setupButtons2(){
        add(new JButton("Simulate") {{
                addActionListener(e -> topMenuController.simulationStep());
            }});
        add(new JButton("Save") {{
                addActionListener(e -> topMenuController.exportGraph());
            }});
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