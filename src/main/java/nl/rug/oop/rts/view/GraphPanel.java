package nl.rug.oop.rts.view;

import nl.rug.oop.rts.model.View;
import nl.rug.oop.rts.util.TextureLoader;

import javax.swing.*;
import java.awt.*;

/**
 * The graph panel of the game.
 */
public class GraphPanel extends JPanel {
    /**
     * The width of a node.
     * For selecting.
     */
    private final int nodeWidth = 60;
    /**
     * The height of a node.
     * For selecting.
     */
    private final int nodeHeight = 60;
    /**
     * The model of the graph panel.
     */
    private final View model;
    /**
     * The background image of the map.
     */
    private final Image backgroundImage;

    /**
     * Constructor for the GraphPanel class.
     *
     * @param model The model of the graph panel.
     */
    public GraphPanel(View model) {
        this.model = model;
        setLayout(null);
        setBounds(0, 40, 1200, 700);

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
        /*
        Not sure if this is drawing in the right place now.
         */

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, null);
        }

        /*
         * Paint nodes and edges.
         */
        g.translate(-model.getViewX(), -model.getViewY());
    }

}