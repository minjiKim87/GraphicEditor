package com.example.state;

import com.example.command.Command;
import com.example.command.CreateStraightCommand;
import com.example.controller.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.util.List;

public class StraightDrawingState extends DrawingModeState {
    private Command createStraightCommand;
    private ShapeCreatorController shapeCreatorController;

    public StraightDrawingState(AnchorPane centerAnchorPane, List<Shape> selectedShapes, MoveController moveController, ShapeController shapeController, ShapeCreatorController shapeCreatorController, MultiSelectController multiSelectController, PropertyController propertyController, List<Button> buttons) {
        super(centerAnchorPane, selectedShapes, moveController, shapeController, shapeCreatorController, multiSelectController, propertyController, buttons);
        this.createStraightCommand = new CreateStraightCommand(shapeCreatorController, shapeController);
        this.shapeCreatorController = shapeCreatorController;
    }

    @Override
    public void handleMouseClick(MouseEvent event) {
        ((CreateStraightCommand) createStraightCommand).startDrawing(event);
    }

    @Override
    public void handleMouseDrag(MouseEvent event){
        ((CreateStraightCommand) createStraightCommand).updateDrawing(event);
    }

    @Override
    public void handleMouseRelease(MouseEvent event){
        ((CreateStraightCommand) createStraightCommand).finishDrawing(event);
    }

    @Override
    public void activate() {
        super.activate();
        shapeCreatorController.handleShapeCreation("straight");
    }

    @Override
    public void deactivate() {
        super.deactivate();
    }
}
