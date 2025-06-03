package nl.rug.oop.rts.model.panel;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.army.Faction;

import java.util.ArrayList;
import java.util.List;


/**
 * Node class that holds all the information of a node in the graph.
 */
@Getter
@Setter
public class Node {
    /**
     * The x coordinate of the node.
     */
    private int x;
    /**
     * The y coordinate of the node.
     */
    private int y;
    /**
     * Unique identifier of the node.
     */
    private final int id;
    /**
     * Name of the node.
     */
    private String name;
    /**
     * The number of armies on the node.
     * */
    private List<Army> armyList = new ArrayList<>();

    /**
     * Constructor for the Node class.
     *
     * @param id   Unique identifier of the node.
     * @param name Name of the node.
     * @param x    X coordinate of the node.
     * @param y    Y coordinate of the node.
     */
    public Node(int id, String name, int x, int y) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.name = name;
    }

    public void addArmy(Army army){
        armyList.add(army);
    }

    public void removeArmy(int index){
        armyList.remove(index);
    }

    public void removeArmy(Army army){
        armyList.remove(army);
    }
}
