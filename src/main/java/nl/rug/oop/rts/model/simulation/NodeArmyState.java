package nl.rug.oop.rts.model.simulation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.rug.oop.rts.model.army.Army;

import java.util.List;


/**
 * The Node army state.
 * Stores the nodeID with the list of armies.
 */
@Getter
@AllArgsConstructor
public class NodeArmyState {
    /**
     * The nodeID.
     */
    private final int nodeId;
    /**
     * The list of armies.
     */
    private final List<Army> armies;
}
