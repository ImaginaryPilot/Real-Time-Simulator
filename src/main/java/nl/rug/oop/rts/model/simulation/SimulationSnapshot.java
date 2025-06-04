package nl.rug.oop.rts.model.simulation;

import nl.rug.oop.rts.model.army.Army;
import nl.rug.oop.rts.model.panel.Edge;
import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.panel.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * The class containing the lists of armies before a simulation begins.
 * */
public class SimulationSnapshot {
    /**
     * The graph model.
     * */
    private final GraphModel graphModel;

    /**
     * List of (list of armies) in a node.
     * */
    private List<List<Army>> nodeArmies;

    /**
     * List of (list of armies) in an edge.
     * */
    private List<List<Army>> edgeArmies;

    /**
     * Constructor.
     * @param graphModel graph model
     * */
    public SimulationSnapshot(GraphModel graphModel){
        this.graphModel = graphModel;
    }

    /**
     * store armies in the node.
     * */
    public void storeNodeArmies(){
        nodeArmies = new ArrayList<>();

        for(Node node : graphModel.getNodes()){
            nodeArmies.add(node.copyArmies());
        }
    }

    /**
     * store armies in the edge.
     * */
    public void storeEdgeArmies(){
        edgeArmies = new ArrayList<>();

        for(Edge edge : graphModel.getEdges()){
            edgeArmies.add(edge.copyArmies());
        }
    }

    /**
     * restore back to the original armies for the node.
     * */
    public void restoreNodeArmies(){
        for(int i = 0; i < graphModel.getNodes().size(); i++){
            graphModel.getNodes().get(i).setArmies(nodeArmies.get(i));
        }
    }

    /**
     * restore back to the original armies for the edge.
     * */
    public void restoreEdgeArmies(){
        for(int i = 0; i < graphModel.getEdges().size(); i++){
            graphModel.getEdges().get(i).setArmies(edgeArmies.get(i));
        }
    }
}
