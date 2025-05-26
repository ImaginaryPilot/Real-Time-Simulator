package nl.rug.oop.rts.view;

import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.GraphModel;
import nl.rug.oop.rts.model.Node;
import nl.rug.oop.rts.model.ViewModel;
import nl.rug.oop.rts.observer.Observer;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class SideMenuPanel extends JPanel implements Observer {
    /**
     * The view model of the game.
     */
    private final ViewModel viewModel;
    /**
     * Used for changing the name.
     */

    private JPanel content;
    private final JTextField objectName = new JTextField();
    /**
     * The header of the pop-up menu.
     */
    private final JLabel header = new JLabel("Details");

    /**
     * @param viewModel
     */
    public SideMenuPanel(ViewModel viewModel) {
        this.viewModel = viewModel;
        setupSidePanel();
        update();
        setVisible(true);
    }

    /**
     *
     */
    private void setupSidePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(250, 700));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel headerLabel = new JLabel("Details");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        headerLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(headerLabel);
        add(Box.createVerticalStrut(20));

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(new Color(70, 70, 70));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        nameField.setBackground(Color.LIGHT_GRAY);
        detailsPanel.add(new JLabel("Name:"));
        detailsPanel.add(nameField);
        detailsPanel.add(Box.createVerticalStrut(10));
    }

    /**
     *
     */
    public void update() {
        if (viewModel.getSelectedNode() != null) {
            Node node = viewModel.getSelectedNode();
            header.setText("NODE DETAILS");
            objectName.setText(node.getName());
            objectName.setEnabled(true);
        } else if (viewModel.getSelectedEdge() != null) {
            Edge edge = viewModel.getSelectedEdge();
            header.setText("EDGE DETAILS");
            objectName.setText(edge.getNodeA().getName() + " ↔ " + edge.getNodeB().getName());
            objectName.setEnabled(false); // Edges don't have editable names in this example
        } else {
            header.setText("NO SELECTION");
            objectName.setText("");
            objectName.setEnabled(false);
        }
    }
}
