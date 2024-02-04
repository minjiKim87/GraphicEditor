package com.example.command;

import com.example.controller.ShapeController;
import com.example.controller.ShapeCreatorController;
import javafx.scene.input.MouseEvent;

public class CreateTriangleCommand extends DrawingCommand {
    private final ShapeCreatorController shapeCreatorController;

    public CreateTriangleCommand(ShapeCreatorController shapeCreatorController, ShapeController shapeController) {
        super(shapeController);
        this.shapeCreatorController = shapeCreatorController;

    }

    @Override
    public void startDrawing(MouseEvent event) {
        shapeController.handleCanvasClick(event);

    }

    @Override
    public void updateDrawing(MouseEvent event) {
        super.shapeController.handleCanvasDrag(event);

    }

    @Override
    public void finishDrawing(MouseEvent event) {
        super.shapeController.handleCanvasRelease(event);

    }

    @Override
    public void execute(MouseEvent event) {

    }

    @Override
    public void execute() {

    }
}
