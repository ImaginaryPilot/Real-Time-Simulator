package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.army.Unit;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Export.
 * Save all the data of the nodes and edges to a file.
 */
public class Export {
    /**
     * The graphModel.
     */
    private final GraphModel graphModel;
    /**
     * The outputFile.
     */
    private File outputFile;
    /**
     * The stringBuilder.
     * This saves all the data with the correct indentation before exporting to json file.
     */
    private final StringBuilder jsonBuilder = new StringBuilder();
    /**
     * Template string with 3 spaces. Used to get the right indentation.
     */
    private final String template = "   ";

    /**
     * String for a new line.
     */
    private final String newL = "\n";
    /**
     * String for "\",\n".
     */
    private final String str1 = "\",\n";
    /**
     * String for "],".
     */
    private final String str2 = "],";
    /**
     * String for ",".
     */
    private final String dot = ",";

    /**
     * Instantiates a new Export.
     *
     * @param graphModel the graph model
     * @param outputFile the output file
     */
    public Export(GraphModel graphModel, File outputFile) {
        this.graphModel = graphModel;
        this.outputFile = outputFile;
    }

    /**
     * Save all the data to the output file.
     */
    public void export() {
        if (!outputFile.getName().toLowerCase().endsWith(".json")) {
            outputFile = new File(outputFile.getAbsolutePath() + ".json");
        }
        jsonBuilder.append("{\n");
        String spaces = template.repeat(1);
        jsonBuilder.append(spaces).append("\"Nodes\": [");
        addNode();
        if (graphModel.getNodes().isEmpty()) {
            jsonBuilder.append(str2);
        } else {
            jsonBuilder.append(newL).append(spaces).append(str2);
        } // ],
        jsonBuilder.append(newL);
        jsonBuilder.append(spaces).append("\"Edges\": [");
        addEdge();
        if (graphModel.getEdges().isEmpty()) {
            jsonBuilder.append("]");
        } else {
            jsonBuilder.append(newL);
            jsonBuilder.append(spaces).append("]\n");
        }
        jsonBuilder.append("}");
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(jsonBuilder.toString());
        } catch (IOException e) {
            System.err.println("Error while writing to file: " + e.getMessage());
        }
    }

    /**
     * Do the indentation for the edges.
     */
    private void addEdge() {
        List<Edge> edges = graphModel.getEdges();
        String spaces = template.repeat(2);
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            jsonBuilder.append(newL);
            jsonBuilder.append(spaces).append("{\n"); // Open Edge
            spaces = template.repeat(3);
            jsonBuilder.append(spaces).append("\"Id\": ").append(edge.getId()).append(",\n");
            jsonBuilder.append(spaces).append("\"Name\": \"").append(edge.getName()).append(str1);
            jsonBuilder.append(spaces).append("\"Node1\": \"").append(edge.getNodeA().getName()).append(str1);
            jsonBuilder.append(spaces).append("\"Node2\": \"").append(edge.getNodeB().getName()).append(str1);
            jsonBuilder.append(spaces).append("\"Events\": ["); // Open Events;
            spaces = template.repeat(4);
            for (int l = 0; l < edge.getEvents().size(); l++) {
                // Events
                jsonBuilder.append(newL);
                jsonBuilder.append(spaces).append("\"").append(edge.getEvents().get(l).getName()).append("\"");
                if (l < edge.getEvents().size() - 1) {
                    jsonBuilder.append(dot);
                }
            }
            spaces = template.repeat(3);
            if (edge.getEvents().isEmpty()) {
                jsonBuilder.append(str2);
            } else {
                jsonBuilder.append(newL);
                jsonBuilder.append(spaces).append(str2); // Close Events
            }
            jsonBuilder.append(newL);
            jsonBuilder.append(spaces).append("\"Army\": []\n"); // Open Events;
            spaces = template.repeat(2);
            jsonBuilder.append(spaces).append("}"); // Close Edge;
            if (i < edges.size() - 1) {
                jsonBuilder.append(dot);
            }
        }
    }

    /**
     * Do the indentation for the nodes.
     */
    private void addNode() {
        List<Node> nodes = graphModel.getNodes();
        String spaces = template.repeat(2);
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);// Nodes
            jsonBuilder.append(newL).append(spaces).append("{\n");
            spaces = template.repeat(3);
            jsonBuilder.append(spaces).append("\"Id\": ").append(node.getId()).append(",\n");
            jsonBuilder.append(spaces).append("\"Name\": \"").append(node.getName()).append(str1);
            jsonBuilder.append(spaces).append("\"Armies\": [");
            List<Army> armies = node.getArmyList();
            addArmies(armies);
            spaces = template.repeat(3);
            if (armies.isEmpty()) {
                jsonBuilder.append(str2);
            } else {
                jsonBuilder.append(newL).append(spaces).append(str2);
            }
            jsonBuilder.append(newL).append(spaces).append("\"Events\": ["); // Open Events
            addNodeEvents(node);
            spaces = template.repeat(3);
            if (node.getEvents().isEmpty()) {
                jsonBuilder.append("]");
            } else {
                jsonBuilder.append(newL);
                jsonBuilder.append(spaces).append("]"); // Close Events
            }
            spaces = template.repeat(2);
            jsonBuilder.append(newL);
            jsonBuilder.append(spaces).append("}"); // Close Node
            if (i < nodes.size() - 1) {
                jsonBuilder.append(dot);
            }
        }
    }

    /**
     * Do the indentation for the node events.
     *
     * @param node the node of which events
     */
    private void addNodeEvents(Node node) {
        String spaces = template.repeat(4);
        for (int l = 0; l < node.getEvents().size(); l++) {// Events
            jsonBuilder.append(newL);
            jsonBuilder.append(spaces).append("\"").append(node.getEvents().get(l).getName()).append("\"");
            if (l < node.getEvents().size() - 1) {
                jsonBuilder.append(dot);
            }
        }
    }

    /**
     * Do the indentation for the units.
     *
     * @param army the army, which has the units.
     */
    private void addUnits(Army army) {
        String spaces;
        for (int k = 0; k < army.getUnits().size(); k++) {
            //Units
            Unit unit = army.getUnits().get(k);
            jsonBuilder.append(newL);
            spaces = template.repeat(6);
            jsonBuilder.append(spaces).append("{\n");
            spaces = template.repeat(7);
            jsonBuilder.append(spaces).append("\"Name\": \"").append(unit.getName()).append(str1);
            jsonBuilder.append(spaces).append("\"Strength\": ").append(unit.getDamage()).append(",\n");
            jsonBuilder.append(spaces).append("\"Health\": ").append(unit.getHealth()).append(newL);
            spaces = template.repeat(6);
            jsonBuilder.append(spaces).append("}");
            if (k < army.getUnits().size() - 1) {
                jsonBuilder.append(dot);
            }
        }
    }

    /**
     * Do the indentation for the armies.
     *
     * @param armies all the armies
     */
    private void addArmies(List<Army> armies) {
        String spaces;
        for (int j = 0; j < armies.size(); j++) {
            // Armies
            Army army = armies.get(j);
            jsonBuilder.append(newL);
            spaces = template.repeat(4);
            jsonBuilder.append(spaces).append("{\n");
            spaces = template.repeat(5);
            jsonBuilder.append(spaces).append("\"Faction\": \"").append(army.getFaction()).append(str1);
            jsonBuilder.append(spaces).append("\"Team\": \"").append(army.getFaction().getTeam()).append(str1);
            jsonBuilder.append(spaces).append("\"Units\": [");
            addUnits(army);
            spaces = template.repeat(5);
            if (army.getUnits().isEmpty()) {
                jsonBuilder.append("]"); // should never be empty tbh
            } else {
                jsonBuilder.append(newL);
                jsonBuilder.append(spaces).append("]"); // Close Units
            }
            spaces = template.repeat(4);

            jsonBuilder.append(newL);
            jsonBuilder.append(spaces).append("}"); // Close One Army
            if (j < armies.size() - 1) {
                jsonBuilder.append(dot);
            } // end armies
        }
    }
}
