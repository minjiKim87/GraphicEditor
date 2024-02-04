//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.state;

import com.example.command.Command;
import com.example.command.CreateCircleCommand;
import com.example.controller.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.util.List;

public class CircleDrawingState extends DrawingModeState {
    private Command createCircleCommand;
    private ShapeCreatorController shapeCreatorController;

    public CircleDrawingState(AnchorPane centerAnchorPane, List<Shape> selectedShapes, MoveController moveController, ShapeController shapeController, ShapeCreatorController shapeCreatorController, MultiSelectController multiSelectController, PropertyController propertyController, List<Button> buttons) {
        super(centerAnchorPane, selectedShapes, moveController, shapeController, shapeCreatorController, multiSelectController, propertyController, buttons);
        this.createCircleCommand = new CreateCircleCommand(shapeCreatorController, shapeController);
        this.shapeCreatorController = shapeCreatorController;
    }
    @Override
    public void handleMouseClick(MouseEvent event) {
        ((CreateCircleCommand) createCircleCommand).startDrawing(event);
    }
    @Override
    public void handleMouseDrag(MouseEvent event){
        ((CreateCircleCommand) createCircleCommand).updateDrawing(event);
    }
    @Override
    public void handleMouseRelease(MouseEvent event){
        ((CreateCircleCommand) createCircleCommand).finishDrawing(event);
    }
    @Override
    public void activate() {
        super.activate();
        shapeCreatorController.handleShapeCreation("circle");
    }
    @Override
    public void deactivate() {
        super.deactivate();
    }
}
