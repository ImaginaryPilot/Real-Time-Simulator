package nl.rug.oop.rts.model.simulation;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.Node;

@AllArgsConstructor
public class Move {
    Army army;
    Node from;
    Node to;
    Edge edge;
}
