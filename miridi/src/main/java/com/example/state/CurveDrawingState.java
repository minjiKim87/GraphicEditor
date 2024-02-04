package com.example.state;

import com.example.command.Command;
import com.example.command.CreateCurveCommand;
import com.example.controller.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.util.List;

public class CurveDrawingState extends DrawingModeState {
    private Command createCurveCommand;
    private ShapeCreatorController shapeCreatorController;

    public CurveDrawingState(AnchorPane centerAnchorPane, List<Shape> selectedShapes, MoveController moveController, ShapeController shapeController, ShapeCreatorController shapeCreatorController, MultiSelectController multiSelectController, PropertyController propertyController, List<Button> buttons) {
        super(centerAnchorPane, selectedShapes, moveController, shapeController, shapeCreatorController, multiSelectController, propertyController, buttons);
        this.createCurveCommand = new CreateCurveCommand(shapeCreatorController, shapeController);
        this.shapeCreatorController = shapeCreatorController;
    }

    @Override
    public void handleMouseClick(MouseEvent event) {
        ((CreateCurveCommand) createCurveCommand).startDrawing(event);
    }

    @Override
    public void handleMouseDrag(MouseEvent event){
        ((CreateCurveCommand) createCurveCommand).updateDrawing(event);
    }

    @Override
    public void handleMouseRelease(MouseEvent event){
        ((CreateCurveCommand) createCurveCommand).finishDrawing(event);
    }

    @Override
    public void activate() {
        super.activate();
        shapeCreatorController.handleShapeCreation("curve");
    }

    @Override
    public void deactivate() {
        super.deactivate();
    }
}
