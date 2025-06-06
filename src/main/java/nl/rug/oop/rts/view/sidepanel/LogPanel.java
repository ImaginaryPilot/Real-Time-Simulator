package nl.rug.oop.rts.view.sidepanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The panel for displaying the logs of the simulation.
 */
public class LogPanel extends JPanel {
    /**
     * The text area where the logs are displayed.
     */
    private final JTextArea textArea;

    /**
     * Instantiates a new Log panel.
     */
    public LogPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 200));

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Show log.
     *
     * @param logs the logs of the simulation.
     */
    public void showLog(List<String> logs) {
        if (logs == null || logs.isEmpty()) {
            textArea.setText("Nothing has occurred...");
            return;
        }

        String message = String.join("\n", logs);
        textArea.setText(message);
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
