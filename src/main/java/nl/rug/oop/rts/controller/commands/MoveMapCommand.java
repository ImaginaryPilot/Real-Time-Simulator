package nl.rug.oop.rts.controller.commands;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.panel.ViewModel;

@AllArgsConstructor
public class MoveMapCommand implements Command {
    private final int oldX;
    private final int oldY;

    private final int newX;
    private final int newY;

    private final ViewModel viewModel;

    @Override
    public void execute() {
        int x = newX + oldX;
        int y = newY + oldY;
        int newX = viewModel.getViewX() - x;
        int newY = viewModel.getViewY() - y;

        int finalX = Math.max(-(viewModel.getMapWidth() - viewModel.getPanelWidth()), Math.min(0, newX));
        int finalY = Math.max(-(viewModel.getMapHeight() - viewModel.getPanelHeight()), Math.min(0, newY));
        viewModel.setViewPosition(finalX, finalY);
    }

    @Override
    public void undo() {
        int x = newX - oldX;
        int y = newY - oldY;
        int newX = viewModel.getViewX() - x;
        int newY = viewModel.getViewY() - y;

        int finalX = Math.max(-(viewModel.getMapWidth() - viewModel.getPanelWidth()), Math.min(0, newX));
        int finalY = Math.max(-(viewModel.getMapHeight() - viewModel.getPanelHeight()), Math.min(0, newY));
        viewModel.setViewPosition(finalX, finalY);
    }
}
