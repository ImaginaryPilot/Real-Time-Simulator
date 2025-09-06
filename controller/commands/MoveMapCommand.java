package nl.rug.oop.rts.controller.commands;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.panel.ViewModel;

/**
 * Command for moving the map.
 */
@AllArgsConstructor
public class MoveMapCommand implements Command {
    /**
     * The delta x that the map will be moved.
     */
    private final int deltaX;
    /**
     * The delta y that the map will be moved.
     */
    private final int deltaY;
    /**
     * The view model of the game.
     */
    private final ViewModel viewModel;

    @Override
    public void execute() {
        int newX = viewModel.getViewX() + deltaX;
        int newY = viewModel.getViewY() + deltaY;
        setViewPosition(newX, newY);
    }

    @Override
    public void undo() {
        int newX = viewModel.getViewX() - deltaX;
        int newY = viewModel.getViewY() - deltaY;
        setViewPosition(newX, newY);
    }

    /**
     * Sets the view position to the given x and y coordinates.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    private void setViewPosition(int x, int y) {
        int finalX = Math.max(viewModel.getPanelWidth() - viewModel.getMapWidth(), Math.min(0, x));
        int finalY = Math.max(viewModel.getPanelHeight() - viewModel.getMapHeight(), Math.min(0, y));
        viewModel.setViewPosition(finalX, finalY);
    }
}
