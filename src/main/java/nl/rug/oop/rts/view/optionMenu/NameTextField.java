package nl.rug.oop.rts.view.optionMenu;

import nl.rug.oop.rts.controller.SideMenuController;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;

/**
 * The text field for the name of a node or edge.
 */
public class NameTextField extends JTextField {
    /**
     * The controller for the side menu.
     */
    private final SideMenuController sideMenuController;

    /**
     * Constructor for the NameTextField class.
     *
     * @param sideMenuController The controller for the side menu.
     */
    public NameTextField(SideMenuController sideMenuController) {
        this.sideMenuController = sideMenuController;
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        ((AbstractDocument) this.getDocument()).setDocumentFilter(new NameFilter());

        this.getDocument().addDocumentListener(new MyDocumentListener(sideMenuController));
    }

    /**
     * Sets the name of the text field.
     *
     * @param name The name to set.
     */
    public void setValidName(String name) {
        setText(name);
    }
}
