package nl.rug.oop.rts.model.simulation;

import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;

import java.util.ArrayList;
import java.util.List;

public class SimulationSnapshot {
    private final GraphModel graphModel;

    private List<List<Army>> nodeArmies;
    private List<List<Army>> edgeArmies;

    public SimulationSnapshot(GraphModel graphModel){
        this.graphModel = graphModel;
    }

    public void storeNodeArmies(){
        nodeArmies = new ArrayList<>();

        for(Node node : graphModel.getNodes()){
            nodeArmies.add(node.copyArmies());
        }
    }

    public void storeEdgeArmies(){
        edgeArmies = new ArrayList<>();

        for(Edge edge : graphModel.getEdges()){
            edgeArmies.add(edge.copyArmies());
        }
    }

    public void restoreNodeArmies(){
        for(int i = 0; i < graphModel.getNodes().size(); i++){
            graphModel.getNodes().get(i).setArmies(nodeArmies.get(i));
        }
    }

    public void restoreEdgeArmies(){
        for(int i = 0; i < graphModel.getEdges().size(); i++){
            graphModel.getEdges().get(i).setArmies(edgeArmies.get(i));
        }
    }
}
