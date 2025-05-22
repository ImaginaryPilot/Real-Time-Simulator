package nl.rug.oop.rts.view;

import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.GraphModel;
import nl.rug.oop.rts.model.Node;
import nl.rug.oop.rts.model.ViewModel;
import nl.rug.oop.rts.observer.Observer;
import nl.rug.oop.rts.util.TextureLoader;

import javax.swing.*;
import java.awt.*;

/**
 * The graph panel of the game.
 */
public class GraphPanel extends JPanel implements Observer {
    /**
     * The width of a node.
     * For selecting.
     */
    private final int nodeWidth;
    /**
     * The height of a node.
     * For selecting.
     */
    private final int nodeHeight;
    /**
     * The model of the graph panel.
     */
    private final ViewModel model;
    /**
     * The graph model of the game.
     */
    private final GraphModel graphModel;
    /**
     * The background image of the map.
     */
    private final Image backgroundImage;

    /**
     * Constructor for the GraphPanel class.
     *
     * @param model      The model of the graph panel.
     * @param graphModel The graph model of the game.
     */
    public GraphPanel(ViewModel model, GraphModel graphModel) {
        this.graphModel = graphModel;
        this.model = model;
        this.nodeWidth = model.getNodeWidth();
        this.nodeHeight = model.getNodeHeight();
        setLayout(null);
        setPreferredSize(new Dimension(1200, 700));

        backgroundImage = TextureLoader.getInstance().getTexture(
                "mapTexture", model.getMapWidth(), model.getMapHeight());

        setVisible(true);
    }

    /**
     * Paints the graph panel.
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.translate(model.getViewX(), model.getViewY());

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, null);
        }

        for (Edge edge : graphModel.getEdges()) {
            g.setColor(Color.BLUE);
            g.drawLine(edge.getNodeA().getX(), edge.getNodeA().getY(), edge.getNodeB().getX(), edge.getNodeB().getY());
        }
        for (Node node : graphModel.getNodes()) {
            if (node == model.getSelectedNode()) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLACK);
            }
            g.fillRect(node.getX() - nodeWidth / 2, node.getY() - nodeHeight / 2, nodeWidth, nodeHeight);
            g.setColor(Color.WHITE);
            g.drawString(node.getName(), node.getX(), node.getY());
        }

        g.translate(-model.getViewX(), -model.getViewY());
    }

    @Override
    public void update() {
        repaint();
    }

}