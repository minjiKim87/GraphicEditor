package com.example.state;

import com.example.command.Command;
import com.example.command.CreateTriangleCommand;
import com.example.controller.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.util.List;

public class TriangleDrawingState extends DrawingModeState {
    private Command createTriangleCommand;
    private ShapeCreatorController shapeCreatorController;

    public TriangleDrawingState(AnchorPane centerAnchorPane, List<Shape> selectedShapes, MoveController moveController, ShapeController shapeController, ShapeCreatorController shapeCreatorController, MultiSelectController multiSelectController, PropertyController propertyController, List<Button> buttons) {
        super(centerAnchorPane, selectedShapes, moveController, shapeController, shapeCreatorController, multiSelectController, propertyController, buttons);
        this.createTriangleCommand = new CreateTriangleCommand(shapeCreatorController, shapeController);
        this.shapeCreatorController = shapeCreatorController;
    }

    @Override
    public void handleMouseClick(MouseEvent event) {
        ((CreateTriangleCommand) createTriangleCommand).startDrawing(event);
    }

    @Override
    public void handleMouseDrag(MouseEvent event){
        ((CreateTriangleCommand) createTriangleCommand).updateDrawing(event);
    }

    @Override
    public void handleMouseRelease(MouseEvent event){
        ((CreateTriangleCommand) createTriangleCommand).finishDrawing(event);
    }

    @Override
    public void activate() {
        super.activate();
        shapeCreatorController.handleShapeCreation("triangle");
    }

    @Override
    public void deactivate() {
        super.deactivate();
    }
}
