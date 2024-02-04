package com.example.command;

import com.example.controller.MultiSelectController;
import com.example.controller.PropertyController;
import com.example.state.ControlState;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

public class SelectCommand implements Command {
    private MultiSelectController multiSelectController;
    private AnchorPane centerAnchorPane;

    private PropertyController propertyController;

    public SelectCommand(MultiSelectController multiSelectController, AnchorPane centerAnchorPane,  PropertyController propertyController) {
        this.multiSelectController = multiSelectController;
        this.centerAnchorPane = centerAnchorPane;
        this.propertyController = propertyController;
    }

    @Override
    public void execute(MouseEvent event) {

    }

    @Override
    public void execute() {

    }


    public void handleDrag(MouseEvent event) {

        multiSelectController.updateSelection(event.getX(), event.getY());
    }

    public void handleRelease(MouseEvent event) {
        multiSelectController.finishSelection(event, ControlState.getInstance().isControlPressed());
    }

    public void handleClick(MouseEvent event) {
        multiSelectController.startSelection(event.getX(), event.getY());

        Shape clickedShape = null;
        for (Node node : centerAnchorPane.getChildren()) {
            if (node.contains(event.getX(), event.getY()) && node instanceof Shape && !(node == multiSelectController.selectionRectangle)) {
                clickedShape = (Shape) node;
                break;
            }
        }

        if (clickedShape == null && !ControlState.getInstance().isControlPressed()) {
            multiSelectController.deselectShapes();
            multiSelectController.clearSelection();
        } else if (clickedShape != null) {
            multiSelectController.selectShape(clickedShape);
            propertyController.handleInfoAnchorPaneClick(clickedShape, event);
        }
    }

}