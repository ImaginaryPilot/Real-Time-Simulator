package nl.rug.oop.rts.view;

import nl.rug.oop.rts.controller.GraphController;
import nl.rug.oop.rts.model.GraphModel;

import javax.swing.*;
import java.awt.*;

/**
 * The top menu panel of the game.
 * Having all kinds of buttons.
 */
public class TopMenuPanel extends JPanel {
    /**
     * The graph controller of the game.
     */
    private final GraphController graphController;
    /**
     * The graph model of the game.
     */
    private final GraphModel graphModel;

    /**
     * Constructor for the TopMenuPanel class.
     *
     * @param graphController The graph controller of the game.
     *                        Used to add and remove nodes and edges.
     * @param graphModel      The graph model of the game.
     *                        Used to get the map width and height.
     */
    public TopMenuPanel(GraphController graphController, GraphModel graphModel) {
        this.graphModel = graphModel;
        this.graphController = graphController;
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
        JButton button = new JButton("Button 1");
        button.addActionListener(e -> {
            System.out.println("Button 1 clicked");
            graphController.addNode("Node 1", 100, 100);
        });

        JButton button2 = new JButton("Button 2");
        button2.addActionListener(e -> {
            System.out.println("Button 2 clicked");
            while (!graphModel.getNodes().isEmpty()) {
                graphController.removeNode(graphModel.getNodes().get(0));
            }
            //            graphController.addNode("Node 2", 200, 200);

        });
        JButton button3 = new JButton("Button buttonaosndf 3");
        button3.addActionListener(e -> {
            System.out.println("Button 3 clicked");
            graphController.addNode("Node 3", 300, 300);
        });
        JButton button4 = new JButton("Generate somthing cool :KJ");
        button4.addActionListener(e -> {
            System.out.println("Button 4 clicked");
            graphController.generateTest();
        });
        add(button);
        add(button2);
        add(button3);
        add(button4);
    }

}