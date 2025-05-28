package nl.rug.oop.rts;

import com.formdev.flatlaf.FlatDarculaLaf;
import nl.rug.oop.rts.view.Game;

import javax.swing.*;

/**
 * The main class that starts the game.
 */
public class Main {
    /**
     * The main method of the game.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        //JFrame.setDefaultLookAndFeelDecorated(true);
        FlatDarculaLaf.setup(); // Dark mode
        Game game = new Game();
        game.start();
    }
}