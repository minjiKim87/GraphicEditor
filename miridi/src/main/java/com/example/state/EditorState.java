package com.example.state;

import com.example.controller.*;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.util.List;

public abstract class EditorState {
    protected AnchorPane centerAnchorPane;
    protected List<Shape> selectedShapes;
    protected MoveController moveController;
    protected ShapeController shapeController;
    protected ShapeCreatorController shapeCreatorController;
    protected MultiSelectController multiSelectController;
    protected PropertyController propertyController;
    protected List<Button> buttons;

    public EditorState(AnchorPane centerAnchorPane, List<Shape> selectedShapes,
                       MoveController moveController, ShapeController shapeController,
                       ShapeCreatorController shapeCreatorController,
                       MultiSelectController multiSelectController,
                       PropertyController propertyController, List<Button> buttons) {
        this.centerAnchorPane = centerAnchorPane;
        this.selectedShapes = selectedShapes;
        this.moveController = moveController;
        this.shapeController = shapeController;
        this.shapeCreatorController = shapeCreatorController;
        this.multiSelectController = multiSelectController;
        this.propertyController = propertyController;
        this.buttons = buttons;
    }

    public abstract void handleMouseClick(MouseEvent event);
    public abstract void handleKeyPress(KeyEvent event);
    public abstract void handleMouseDrag(MouseEvent event);
    public abstract void handleMouseRelease(MouseEvent event);

    public abstract void activate();
    public abstract void deactivate();
}
