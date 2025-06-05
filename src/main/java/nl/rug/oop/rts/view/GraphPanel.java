package nl.rug.oop.rts.view;

import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;
import nl.rug.oop.rts.model.panel.ViewModel;
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

        drawEdges(g);
        drawNodes(g);
        g.translate(-model.getViewX(), -model.getViewY());
    }

    @Override
    public void update() {
        repaint();
    }

    /**
     * Draw the edges in the graph.
     *
     * @param g the <code>Graphics</code> object to protect
     */
    public void drawEdges(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        float[] dash = {10};
        Stroke dashed = new BasicStroke(2.5F, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 4, dash, 0);
        g2d.setStroke(dashed);
        for (Edge edge : graphModel.getEdges()) {
            if (edge == model.getSelectedEdge()) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.ORANGE);
            }
            g2d.drawLine(
                    edge.getNodeA().getX(), edge.getNodeA().getY(), edge.getNodeB().getX(), edge.getNodeB().getY());
        }
    }

    /**
     * Draw the nodes in the graph.
     *
     * @param g the <code>Graphics</code> object to protect
     */
    private void drawNodes(Graphics g) {
        for (Node node : graphModel.getNodes()) {
            Image nodeIcon;
            if (node == model.getSelectedNode()) {
                nodeIcon = TextureLoader.getInstance().getTexture("node3", nodeWidth, nodeHeight);
            } else {
                nodeIcon = TextureLoader.getInstance().getTexture("node4", nodeWidth, nodeHeight);
            }
            g.drawImage(nodeIcon, node.getX() - nodeWidth / 2, node.getY() - nodeHeight / 2, null);

            g.setColor(Color.ORANGE);
            String name = node.getName();
            FontMetrics frontMetrics = g.getFontMetrics();
            int textWidth = frontMetrics.stringWidth(name);
            int x = node.getX() - textWidth / 2;
            g.drawString(name, x, node.getY());

            int iconStartX = node.getX() - nodeWidth / 2;
            int iconY = node.getY() + nodeHeight / 2 - nodeHeight / 5;
            int armyImageWidth = (int) (model.getNodeWidth() / 2.5);
            int armyImageHeight = (int) (model.getNodeHeight() / 2.5);
            for (int i = 0; i < node.getArmyList().size(); i++) {
                Army army = node.getArmyList().get(i);
                String textureName = army.getTextureName();

                Image icon = TextureLoader.getInstance().getTexture(
                        textureName, armyImageWidth, armyImageHeight);
                g.drawImage(icon, iconStartX + i * armyImageWidth, iconY, null);

                String armySizeText = String.valueOf(army.getUnits().size());
                int armyTextWidth = frontMetrics.stringWidth(armySizeText);

                int textX = iconStartX + i * armyImageWidth + (armyImageWidth - armyTextWidth)/2;
                int textY = iconY + armyImageHeight + frontMetrics.getAscent();

                g.setColor(Color.BLACK);
                g.drawString(armySizeText, textX, textY);
            }
        }
    }
}