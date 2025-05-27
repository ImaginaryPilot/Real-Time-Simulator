package nl.rug.oop.rts.view;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.Node;
import nl.rug.oop.rts.model.ViewModel;


@AllArgsConstructor
public class SideMenuController {
    private final ViewModel viewModel;

    public void renameSelectedNode(String newName) {
        Node node = viewModel.getSelectedNode();
        if (node != null) {
            node.setName(newName);
        }
    }
}
