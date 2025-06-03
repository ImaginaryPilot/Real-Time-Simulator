package nl.rug.oop.rts.controller.commands;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.simulation.Simulation;

/**
 * Command for simulating command.
 */
@AllArgsConstructor
public class SimulationCommand implements Command {
    /**
     * The simulation to simulate.
     */
    private final Simulation simulation;

    @Override
    public void execute() {
        simulation.simulationStep();
    }

    @Override
    public void undo() {
    }
}
