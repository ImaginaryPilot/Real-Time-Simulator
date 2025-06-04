package nl.rug.oop.rts.model.panel;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.model.army.Army;

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

    /**
     * add army to the armyList.
     *
     * @param army the army
     * */
    public void addArmy(Army army){
        armyList.add(army);
    }

    /**
     * remove army from the armyList using index.
     *
     * @param index where army lies
     * */
    public void removeArmy(int index){
        armyList.remove(index);
    }

    /**
     * remove army from the armyList using army object.
     *
     * @param army the army
     * */
    public void removeArmy(Army army){
        armyList.remove(army);
    }

    public List<Army> copyArmies(){
        List<Army> armyCopy = new ArrayList<>();

        for(Army army : armyList){
            armyCopy.add(army.copy());
        }

        return armyCopy;
    }

    public void setArmies(List<Army> newArmy){
        armyList.clear();
        armyList.addAll(newArmy);
    }
}
