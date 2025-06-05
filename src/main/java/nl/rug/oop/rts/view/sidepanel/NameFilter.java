package nl.rug.oop.rts.view.sidepanel;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * Document filter for the name text fields.
 */
public class NameFilter extends DocumentFilter {
    /**
     * The maximum number of characters for a name.
     */
    private final int maxCharacters = 20;

    @Override
    public void insertString(FilterBypass fb, int offs, String str, AttributeSet a)
            throws BadLocationException {
        String newName = fb.getDocument().getText(0, fb.getDocument().getLength()) + str;
        if (isValid(newName)) {
            super.insertString(fb, offs, str, a);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a)
            throws BadLocationException {
        String current = fb.getDocument().getText(0, fb.getDocument().getLength());
        String newText = current.substring(0, offs) + str + current.substring(offs + length);
        if (isValid(newText)) {
            super.replace(fb, offs, length, str, a);
        }
    }

    /**
     * Checks if the text is a valid name.
     * Valid names are alphanumeric and no more than 20 characters long.
     *
     * @param text The text to check.
     * @return True if the text is a valid name, false otherwise.
     */
    private boolean isValid(String text) {
        if (text == null) {
            return true;
        }
        return text.matches("[a-zA-Z0-9 ]*") && text.length() <= maxCharacters;
    }

}