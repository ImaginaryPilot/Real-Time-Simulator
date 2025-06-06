package nl.rug.oop.rts.model.simulation;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.Node;

/**
 * The class that stores every army move to another edge/node.
 */
@Data
@AllArgsConstructor
public class Move {
    /**
     * Army in question about to move.
     */
    private Army army;
    /**
     * Source node that the army is moving FROM.
     */
    private Node from;
    /**
     * Destination node that the army is moving TO.
     */
    private Node to;
    /**
     * The edge the army is using to travel.
     */
    private Edge edge;
}
