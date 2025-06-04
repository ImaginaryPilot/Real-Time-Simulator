package nl.rug.oop.rts.controller.commands;

import nl.rug.oop.rts.model.panel.GraphModel;
import nl.rug.oop.rts.model.simulation.Simulation;
import nl.rug.oop.rts.model.simulation.SimulationSnapshot;

/**
 * Command for simulating command.
 */
public class SimulationCommand implements Command {
    /**
     * The graph model.
     * */
    private final GraphModel graphModel;

    /**
     * The simulation to simulate.
     */
    private final Simulation simulation;

    private SimulationSnapshot previousState;

    public SimulationCommand(GraphModel graphModel, Simulation simulation){
        this.graphModel = graphModel;
        this.simulation = simulation;
    }

    @Override
    public void execute() {
        previousState = new SimulationSnapshot(graphModel);
        previousState.storeEdgeArmies();
        previousState.storeNodeArmies();

        simulation.simulationStep();
    }

    @Override
    public void undo() {
        if(previousState != null){
            previousState.restoreEdgeArmies();
            previousState.restoreNodeArmies();
            graphModel.updateAllObservers();
        }
    }
}
