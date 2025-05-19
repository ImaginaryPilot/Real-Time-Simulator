package nl.rug.oop.rts.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Mouse controller class.
 */
public class MouseController extends MouseAdapter {
    /**
     * The main controller of the game.
     */
    private final MainController mainController;
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
     * Constructor for the MouseController class.
     *
     * @param mainController The main controller of the game.
     */
    public MouseController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        System.out.println("Mouse clicked");
        /*
        Call to Maincontroller for command
         */
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        System.out.println("Mouse dragged");
        /*
        Call to Maincontroller for command
         */
    }

}
