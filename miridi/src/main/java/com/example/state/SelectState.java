package com.example.state;

import com.example.command.SelectCommand;
import com.example.controller.*;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;
import java.util.List;

public class SelectState extends EditorState {
    private SelectCommand selectCommand;

    public SelectState(AnchorPane centerAnchorPane, List<Shape> selectedShapes,
                       MoveController moveController, ShapeController shapeController,
                       ShapeCreatorController shapeCreatorController,
                       MultiSelectController multiSelectController,
                       PropertyController propertyController, List<Button> buttons) {
        super(centerAnchorPane, selectedShapes, moveController, shapeController,
                shapeCreatorController, multiSelectController, propertyController, buttons);
        this.selectCommand = new SelectCommand(multiSelectController, centerAnchorPane,  propertyController);
    }

    @Override
    public void handleMouseClick(MouseEvent event) {
        selectCommand.handleClick(event);
    }

    @Override
    public void handleKeyPress(KeyEvent event) {

    }

    @Override
    public void handleMouseDrag(MouseEvent event) {
        selectCommand.handleDrag(event);
    }

    @Override
    public void handleMouseRelease(MouseEvent event) {
        selectCommand.handleRelease(event);
    }

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }
}
