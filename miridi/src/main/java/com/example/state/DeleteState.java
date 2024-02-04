package com.example.state;

import com.example.command.DeleteCommand;
import com.example.controller.*;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;
import java.util.List;

public class DeleteState extends EditorState {
    private DeleteCommand deleteCommand;

    public DeleteState(AnchorPane centerAnchorPane, List<Shape> selectedShapes,
                       MoveController moveController, ShapeController shapeController,
                       ShapeCreatorController shapeCreatorController,
                       MultiSelectController multiSelectController,
                       PropertyController propertyController, List<Button> buttons) {
        super(centerAnchorPane, selectedShapes, moveController, shapeController,
                shapeCreatorController, multiSelectController, propertyController, buttons);
        this.deleteCommand = new DeleteCommand(multiSelectController, centerAnchorPane);
    }

    @Override
    public void handleMouseClick(MouseEvent event) {
        deleteCommand.execute();
    }

    @Override
    public void handleKeyPress(KeyEvent event) {

    }

    @Override
    public void handleMouseDrag(MouseEvent event) {

    }

    @Override
    public void handleMouseRelease(MouseEvent event) {

    }

    @Override
    public void activate() {
        deleteCommand.execute();

    }

    @Override
    public void deactivate() {

    }
}
