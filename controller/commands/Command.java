package nl.rug.oop.rts.controller.commands;

/**
 * Command interface.
 */
public interface Command {
    /**
     * Execute the command.
     */
    void execute();

    /**
     * Undo the command.
     * Do the opposite of executing.
     */
    void undo();
}
