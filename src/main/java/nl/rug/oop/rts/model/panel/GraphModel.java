package nl.rug.oop.rts.model.panel;

import lombok.Getter;
import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.events.Event;
import nl.rug.oop.rts.observer.Observable;
import nl.rug.oop.rts.observer.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph class that holds all the nodes and edges of the graph.
 */
@Getter
public class GraphModel implements Observable {
    /**
     * List of all the nodes in the graph.
     */
    private List<Node> nodes;
    /**
     * List of all the edges in the graph.
     */
    private List<Edge> edges;
    /**
     * Counter for the unique identifier of a node.
     */
    private int nodeIdCounter;
    /**
     * Counter for the unique identifier of an edge.
     */
    private int edgeIdCounter;
    /**
     * Counter for the unique identifier of an army.
     */
    private int armyIdCounter;
    /**
     * List of all the observers of the graph.
     */
    private List<Observer> observers;

    /**
     * Constructor for the Graph class.
     */
    public GraphModel() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.nodeIdCounter = 0;
        this.edgeIdCounter = 0;
        this.armyIdCounter = 0;
        this.observers = new ArrayList<>();
    }

    /**
     * Adds a node to the graph.
     *
     * @param node The node to add to the graph.
     */
    public void addNode(Node node) {
        nodes.add(node);
        updateAllObservers();
    }

    /**
     * Removes a node from the graph.
     *
     * @param node The node to remove from the graph.
     */
    public void removeNode(Node node) {
        List<Edge> edgesToRemove = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.getNodeA() == node || edge.getNodeB() == node) {
                edgesToRemove.add(edge);
            }
        }
        for (Edge edge : edgesToRemove) {
            removeEdge(edge);
        }

        nodes.remove(node);
        updateAllObservers();
    }

    /**
     * Adds an edge to the graph.
     *
     * @param edge The edge to add to the graph.
     */
    public void addEdge(Edge edge) {
        edges.add(edge);
        updateAllObservers();
    }

    /**
     * Removes an edge from the graph.
     *
     * @param edge The edge to remove from the graph.
     */
    public void removeEdge(Edge edge) {
        edges.remove(edge);
        updateAllObservers();
    }

    /**
     * Set the position of a node.
     *
     * @param node The node to set the position of.
     * @param x    The x coordinate of the node.
     * @param y    The y coordinate of the node.
     */
    public void setNodePosition(Node node, int x, int y) {
        node.setX(Math.max(0, Math.min(x, 2400))); //For security if something bugs out
        node.setY(Math.max(0, Math.min(y, 1400))); //And prevent nodes outside the map
        updateAllObservers();
    }

    /**
     * Set the name of a node.
     *
     * @param node The node to set the name of.
     * @param name The new name of the node.
     */
    public void setNodeName(Node node, String name) {
        node.setName(name);
        updateAllObservers();
    }

    /**
     * Set the name of an edge.
     *
     * @param edge The edge to set the name of.
     * @param name The new name of the edge.
     */
    public void setEdgeName(Edge edge, String name) {
        edge.setName(name);
        updateAllObservers();
    }

    /**
     * Checks if a node exists in the graph.
     *
     * @param nodeA The node to check.
     * @param nodeB The node to check.
     * @return True if the node exists, false if not.
     */
    public boolean existEdge(Node nodeA, Node nodeB) {
        for (Edge edge : getEdges()) {
            if (edge.getNodeA() == nodeA && edge.getNodeB() == nodeB) {
                return true;
            }
            if (edge.getNodeA() == nodeB && edge.getNodeB() == nodeA) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a unique identifier for a node.
     *
     * @return The unique identifier of the node.
     */
    public int createNodeId() {
        nodeIdCounter++;
        return nodeIdCounter;
    }

    /**
     * Creates a unique identifier for an edge.
     *
     * @return The unique identifier of the edge.
     */
    public int createEdgeId() {
        edgeIdCounter++;
        return edgeIdCounter;
    }

    /**
     * Creates a unique identifier for an army.
     *
     * @return The unique identifier of the army.
     */
    public int createArmyId() {
        armyIdCounter++;
        return armyIdCounter;
    }

    /**
     * Get all edges connected to the node.
     *
     * @param node the node we want to find all edges connected to
     * @return all edges connected to the node
     */
    public List<Edge> getConnectedEdges(Node node) {
        List<Edge> connectedEdges = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.getNodeA().equals(node) || edge.getNodeB().equals(node)) {
                connectedEdges.add(edge);
            }
        }
        return connectedEdges;
    }

    /**
     * Get the other node on the same edge.
     *
     * @param node the source node
     * @param edge the edge that connected both nodes
     * @return the edge connected on the other end
     */
    public Node getOtherNode(Edge edge, Node node) {
        if (edge.getNodeA().equals(node)) {
            return edge.getNodeB();
        } else if (edge.getNodeB().equals(node)) {
            return edge.getNodeA();
        }
        return null;
    }

    /**
     * Add an army to a node.
     *
     * @param node the node
     * @param army the army
     */
    public void addArmy(Node node, Army army) {
        node.addArmy(army);
        updateAllObservers();
    }

    /**
     * Remove an army from a node.
     *
     * @param node the node
     * @param army the army
     */
    public void removeArmy(Node node, Army army) {
        node.removeArmy(army);
        updateAllObservers();
    }

    /**
     * @param node
     * @param event
     */
    public void addNodeEvent(Node node, Event event) {
        node.addEvent(event);
        updateAllObservers();
    }

    /**
     * @param node
     * @param event
     */
    public void removeNodeEvent(Node node, Event event) {
        node.removeEvent(event);
        updateAllObservers();
    }

    /**
     * @param edge
     * @param event
     */
    public void addEdgeEvent(Edge edge, Event event) {
        edge.addEvent(event);
        updateAllObservers();
    }

    /**
     * @param edge
     * @param event
     */
    public void removeEdgeEvent(Edge edge, Event event) {
        edge.removeEvent(event);
        updateAllObservers();
    }

    /**
     * Set nodes.
     *
     * @param newNodes the new nodes
     */
    public void setNodes(List<Node> newNodes) {
        this.nodes = newNodes;
        updateAllObservers();
    }

    /**
     * set edges.
     *
     * @param newEdges the new edges
     */
    public void setEdges(List<Edge> newEdges) {
        this.edges = newEdges;
        updateAllObservers();
    }
}
