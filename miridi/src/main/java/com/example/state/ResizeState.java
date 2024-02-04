package com.example.state;
import com.example.command.MoveCommand;
import com.example.command.ResizeCommand;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import com.example.controller.*;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;

import java.util.List;

public class ResizeState extends EditorState {
    private ResizeCommand resizeCommand;

    public ResizeState(AnchorPane centerAnchorPane, List<Shape> selectedShapes,
                       MoveController moveController, ShapeController shapeController,
                       ShapeCreatorController shapeCreatorController,
                       MultiSelectController multiSelectController,
                       PropertyController propertyController, List<Button> buttons,
                       SizeController sizeController) {
        super(centerAnchorPane, selectedShapes, moveController, shapeController,
                shapeCreatorController, multiSelectController, propertyController, buttons);
        this.resizeCommand = new ResizeCommand(sizeController);
    }

    @Override
    public void handleMouseClick(MouseEvent event) {

    }

    @Override
    public void handleKeyPress(KeyEvent event) {

    }

    @Override
    public void handleMouseDrag(MouseEvent event) {
      //  System.out.println("resize drag");
        resizeCommand.handleDrag(event);
    }

    @Override
    public void handleMouseRelease(MouseEvent event) {
        this.deactivate();
    }

    @Override
    public void activate() {
        resizeCommand.execute();
    }

    @Override
    public void deactivate() {

    }

}
