package com.example.state;
import com.example.command.MoveCommand;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import com.example.controller.*;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;

import java.util.List;

public class MoveState extends EditorState {
    private MoveCommand moveCommand;
    public MoveState(AnchorPane centerAnchorPane, List<Shape> selectedShapes, MoveController moveController, ShapeController shapeController, ShapeCreatorController shapeCreatorController, MultiSelectController multiSelectController, PropertyController propertyController, List<Button> buttons) {
        super(centerAnchorPane, selectedShapes, moveController, shapeController, shapeCreatorController, multiSelectController, propertyController, buttons);
        this.moveCommand = new MoveCommand(moveController);
        this.moveController = moveController;
    }


    @Override
    public void handleMouseClick(MouseEvent event) {
        moveCommand.startMove(event);
    }

    @Override
    public void handleKeyPress(KeyEvent event) {
    }

    @Override
    public void handleMouseDrag(MouseEvent event) {

        if (selectedShapes.isEmpty()) {
            //System.out.println("No shapes are selected for moving.");
        } else {

            for (Shape shape : selectedShapes) {
                System.out.println(" - " + shape);
            }
        }


        moveCommand.moveShapes(event, selectedShapes, multiSelectController);
    }

    @Override
    public void handleMouseRelease(MouseEvent event) {

    }

    @Override
    public void activate() {

        moveController.activateMoveMode();

    }

    @Override
    public void deactivate() {

        moveController.deactivateMoveMode();

    }
}
