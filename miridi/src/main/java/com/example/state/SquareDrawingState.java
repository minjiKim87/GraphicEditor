package com.example.state;

import com.example.command.Command;
import com.example.command.CreateSquareCommand;
import com.example.controller.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.util.List;

public class SquareDrawingState extends DrawingModeState {
    private Command createSquareCommand;
    private ShapeCreatorController shapeCreatorController;

    public SquareDrawingState(AnchorPane centerAnchorPane, List<Shape> selectedShapes, MoveController moveController, ShapeController shapeController, ShapeCreatorController shapeCreatorController, MultiSelectController multiSelectController, PropertyController propertyController, List<Button> buttons) {
        super(centerAnchorPane, selectedShapes, moveController, shapeController, shapeCreatorController, multiSelectController, propertyController, buttons);
        this.createSquareCommand = new CreateSquareCommand(shapeCreatorController, shapeController);
        this.shapeCreatorController = shapeCreatorController;
    }

    @Override
    public void handleMouseClick(MouseEvent event) {
        ((CreateSquareCommand) createSquareCommand).startDrawing(event);
    }

    @Override
    public void handleMouseDrag(MouseEvent event){
        ((CreateSquareCommand) createSquareCommand).updateDrawing(event);
    }

    @Override
    public void handleMouseRelease(MouseEvent event){
        ((CreateSquareCommand) createSquareCommand).finishDrawing(event);
    }

    @Override
    public void activate() {
        super.activate();
        shapeCreatorController.handleShapeCreation("square");
    }

    @Override
    public void deactivate() {
        super.deactivate();
    }
}
