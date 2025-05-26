package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.ViewModel;

public class TopMenuController {
    private final MainController mainController;
    private final ViewModel viewModel;


    public TopMenuController(MainController mainController, ViewModel viewModel) {
        this.mainController = mainController;
        this.viewModel = viewModel;
    }

    public void addNodeButton() {
        viewModel.toggleCreateNodeMode();
    }


}
