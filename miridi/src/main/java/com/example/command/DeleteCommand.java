package com.example.command;

import com.example.controller.MultiSelectController;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.util.List;

public class DeleteCommand implements Command {
    private MultiSelectController multiSelectController;
    private AnchorPane centerAnchorPane;

    public DeleteCommand(MultiSelectController multiSelectController, AnchorPane centerAnchorPane) {
        this.multiSelectController = multiSelectController;
        this.centerAnchorPane = centerAnchorPane;
    }

    @Override
    public void execute(MouseEvent event) {

    }

    @Override
    public void execute() {

        List<Shape> selectedShapes = multiSelectController.getSelectedShapes();
        for (Shape shape : selectedShapes) {
            centerAnchorPane.getChildren().remove(shape);
        }
        multiSelectController.clearSelection(); // 선택 해제
    }
}
