package nl.rug.oop.rts.view.sidepanel;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.controller.SideMenuController;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

/**
 * Document listener for the name text fields.
 */
@AllArgsConstructor
public class MyDocumentListener implements DocumentListener {
    /**
     * The controller for the side menu.
     */
    private final SideMenuController sideMenuController;
    /**
     * The nameTextField, used to check for fakeCommands.
     */
    private final NameTextField nameTextField;

    /**
     * Called when the text in the text field changes.
     *
     * @param e the document event that occurred.
     */
    public void insertUpdate(DocumentEvent e) {
        handleChange(e);
    }

    /**
     * Called when the text in the text field is removed.
     *
     * @param e the document event that occurred.
     */
    public void removeUpdate(DocumentEvent e) {
        handleChange(e);
    }

    /**
     * Called when the text in the text field is changed.
     *
     * @param e the document event that occurred.
     */
    public void changedUpdate(DocumentEvent e) {
        handleChange(e);
    }

    /**
     * Handles the change in the text field.
     *
     * @param e The document event that occurred.
     */
    private void handleChange(DocumentEvent e) {
        if (nameTextField.isFakeChange()) {
            return;
        }
        try {
            String text = e.getDocument().getText(0, e.getDocument().getLength());
            sideMenuController.rename(text);
        } catch (BadLocationException ex) {
            System.err.println("Bad location exception in MyDocumentListener");
        }
    }
}
