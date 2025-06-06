package nl.rug.oop.rts.view.sidepanel;

import nl.rug.oop.rts.controller.SideMenuController;
import nl.rug.oop.rts.model.events.Disaster;
import nl.rug.oop.rts.model.events.Event;
import nl.rug.oop.rts.model.events.Reinforcements;
import nl.rug.oop.rts.model.events.Weaponry;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.ViewModel;
import nl.rug.oop.rts.view.utilities.NameTextField;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The panel for the edge properties.
 */

class EdgePanel extends JPanel {
    /**
     * The name field for the edge.
     */
    private final NameTextField edgeNameField;
    /**
     * The controller for the side menu.
     */
    private final SideMenuController sideMenuController;
    /**
     * current edge.
     */
    private Edge currentEdge;
    /**
     * The label for the node A.
     */
    private final JLabel nodeALabel;
    /**
     * The label for the node B.
     */
    private final JLabel nodeBLabel;
    /**
     * The list of the events listed in a JList.
     */
    private final DefaultListModel<String> eventListModel = new DefaultListModel<>();
    /**
     * The visual component that shows all the events stored.
     */
    private final JList<String> eventList = new JList<>(eventListModel);

    /**
     * Constructor for the EdgePanel class.
     *
     * @param sideMenuController The controller for the side menu.
     * @param viewModel          The view model of the game.
     */
    EdgePanel(SideMenuController sideMenuController, ViewModel viewModel) {
        this.sideMenuController = sideMenuController;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.DARK_GRAY);
        edgeNameField = new NameTextField(sideMenuController);

        add(Box.createVerticalStrut(10));
        edgeNameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(edgeNameField);
        add(Box.createVerticalStrut(5));
        add(new JSeparator(SwingConstants.HORIZONTAL));

        JLabel label = new JLabel("Edge properties");
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setForeground(Color.WHITE);
        add(label);
        add(Box.createVerticalStrut(10));
        add(new JSeparator(SwingConstants.HORIZONTAL));
        add(Box.createVerticalStrut(10));
        add(new JSeparator(SwingConstants.HORIZONTAL));
        nodeALabel = new JLabel("Node 1: ");

        nodeALabel.setForeground(Color.WHITE);
        nodeALabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(nodeALabel);
        add(Box.createVerticalStrut(10));
        add(new JSeparator(SwingConstants.HORIZONTAL));
        add(Box.createVerticalStrut(10));
        add(new JSeparator(SwingConstants.HORIZONTAL));

        nodeBLabel = new JLabel("Node 2: ");
        nodeBLabel.setForeground(Color.WHITE);
        nodeBLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(nodeBLabel);
        add(Box.createVerticalStrut(10));
        add(new JSeparator(SwingConstants.HORIZONTAL));
        add(Box.createVerticalStrut(10));
        add(new JSeparator(SwingConstants.HORIZONTAL));
        eventPanel();
    }

    /**
     * Panel for events.
     */
    private void eventPanel() {
        JLabel titleLabel = new JLabel("Events");
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(titleLabel);

        JComboBox<String> eventSelector = new JComboBox<>(new String[]{"Disaster", "Reinforcements", "Weaponry"});
        eventSelector.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(eventSelector);

        eventList.setVisibleRowCount(5);
        JScrollPane eventScrollPane = new JScrollPane(eventList);
        eventScrollPane.setPreferredSize(new Dimension(200, 100));
        eventScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(eventScrollPane);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JButton addEventButton = new JButton("+");
        JButton removeEventButton = new JButton("-");
        removeEventButton.setEnabled(false);

        buttonPanel.add(addEventButton);
        buttonPanel.add(removeEventButton);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(buttonPanel);

        eventList.addListSelectionListener(e -> {
            boolean selected = eventList.getSelectedIndex() != -1;
            removeEventButton.setEnabled(selected);
        });

        addEventButton(addEventButton, eventSelector);
        removeEventButton(removeEventButton);
        add(Box.createVerticalStrut(10));
    }

    /**
     * Button to add event to node.
     *
     * @param addEventButton add event button.
     * @param eventSelector  the event selector.
     */
    private void addEventButton(JButton addEventButton, JComboBox<String> eventSelector) {
        addEventButton.addActionListener(e -> {
            if (currentEdge != null) {
                String selectedEventType = (String) eventSelector.getSelectedItem(); // **Begin Highlight**
                nl.rug.oop.rts.model.events.Event newEvent = createEventByType(selectedEventType);

                if (newEvent != null) {
                    sideMenuController.addEdgeEvent(currentEdge, newEvent);
                }
            }
        });
    }

    /**
     * Create event based on type.
     *
     * @param type the type of event.
     * @return the event.
     */
    private Event createEventByType(String type) {
        return switch (type) {
            case "Disaster" -> new Disaster();
            case "Reinforcements" -> new Reinforcements();
            case "Weaponry" -> new Weaponry();
            default -> throw new IllegalArgumentException("Unknown event type: " + type);
        };
    }

    /**
     * Button to remove event from node.
     *
     * @param removeEventButton the button.
     */
    private void removeEventButton(JButton removeEventButton) {
        removeEventButton.addActionListener(e -> {
            if (currentEdge != null && eventList.getSelectedValue() != null) {
                int selectedEventType = eventList.getSelectedIndex();
                Event selectedEvent = currentEdge.getEvents().get(selectedEventType);
                if (selectedEvent != null) {
                    sideMenuController.removeEdgeEvent(currentEdge, selectedEvent);
                }
            }
        });
    }

    /**
     * refreshing the JList everytime an event was added or removed.
     */
    private void refreshEventEdgeList() {
        System.out.println("refreshing the JList everytime an event was added or removed.");
        eventListModel.clear();
        if (currentEdge != null) {
            List<Event> events = currentEdge.getEvents();
            for (Event event : events) {
                eventListModel.addElement(event.getClass().getSimpleName());
            }
        }
    }

    /**
     * Sets the edge.
     *
     * @param edge The edge to set.
     */
    public void setEdge(Edge edge) {
        this.currentEdge = edge;
        edgeNameField.setValidName(edge.getName());
        nodeALabel.setText("Node 1: " + edge.getNodeA().getName());
        nodeBLabel.setText("Node 2: " + edge.getNodeB().getName());
        refreshEventEdgeList();
    }
}
