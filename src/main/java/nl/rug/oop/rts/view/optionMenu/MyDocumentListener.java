package nl.rug.oop.rts.view.optionMenu;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.controller.SideMenuController;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

@AllArgsConstructor
public class MyDocumentListener implements DocumentListener {
    private final SideMenuController sideMenuController;

    public void insertUpdate(DocumentEvent e) {
        handleChange(e);
    }

    public void removeUpdate(DocumentEvent e) {
        handleChange(e);
    }

    public void changedUpdate(DocumentEvent e) {
        handleChange(e);
    }

    private void handleChange(DocumentEvent e) {
        try {
            String text = e.getDocument().getText(0, e.getDocument().getLength());
            sideMenuController.rename(text);
        } catch (BadLocationException ex) {
            System.err.println("Bad location exception in MyDocumentListener");
        }
    }
}
