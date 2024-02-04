package com.example.command;

import com.example.controller.MoveController;
import com.example.controller.MultiSelectController;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;

import java.util.List;

public class MoveCommand implements Command {
    private MoveController moveController;

    public MoveCommand(MoveController moveController) {
        this.moveController = moveController;
    }

    public void startMove(MouseEvent event) {

        moveController.startMove(event);
    }

    public void moveShapes(MouseEvent event, List<Shape> selectedShapes, MultiSelectController multiSelectController) {

        moveController.moveShapes(event, selectedShapes, multiSelectController);
    }

    @Override
    public void execute(MouseEvent event) {

    }

    @Override
    public void execute() {
            moveController.activateMoveMode();
    }
}
