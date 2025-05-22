package nl.rug.oop.rts.controller.commands;

/**
 * Command interface.
 */
public interface Command {
    /**
     * Execute the command.
     */
    void redo();

    /**
     * Undo the command.
     * Do the opposite of executing.
     */
    void undo();
}
