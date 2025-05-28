package nl.rug.oop.rts.view.optionMenu;

import nl.rug.oop.rts.controller.SideMenuController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class NameTextField extends JTextField {
    private final SideMenuController sideMenuController;
    private String lastValidName = "";

    public NameTextField(SideMenuController sideMenuController) {
        this.sideMenuController = sideMenuController;
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        ((AbstractDocument) this.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
                    throws BadLocationException {
                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                if (isValidName(newText)) {
                    super.insertString(fb, offset, text, attr);
                    lastValidName = newText;
                } else {
                    System.err.println("Invalid name: " + newText);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                String current = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = current.substring(0, offset) + text + current.substring(offset + length);
                if (isValidName(newText)) {
                    super.replace(fb, offset, length, text, attrs);
                    lastValidName = newText;
                } else {
                    System.err.println("Invalid name: " + newText);
                    System.err.println("Current text: " + current);
                    System.err.println("Length: " + text.length() + " offset: " + offset + " length: " + fb.getDocument().getLength() + " newText: " + newText);
                }
            }
        });

        this.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                handleChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleChange();
            }

            private void handleChange() {
                String text = NameTextField.this.getText();
                sideMenuController.rename(text);
            }
        });
    }

    public boolean isValidName(String text) {
        return text.matches("[a-zA-Z0-9 ]*") && text.length() <= 20;
    }

    public void setValidName(String name) {
        lastValidName = name;
        setText(name);
    }
}
