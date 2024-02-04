package com.example.controller;

import com.example.creator.ShapeFactory;
import com.example.view.ShapeView;
import javafx.scene.input.MouseEvent;

public class ShapeController{

    private final ShapeView shapeView;
    private final ShapeFactory shapeFactory;
    private final ShapeCreatorController shapeCreatorController;
    private double startX, startY;
    private boolean isControlPressed = false;


    public ShapeController(ShapeView shapeView, ShapeFactory shapeFactory, ShapeCreatorController shapeCreatorController) {
        this.shapeView = shapeView;
        this.shapeFactory = shapeFactory;
        this.shapeCreatorController = shapeCreatorController;
    }


    public boolean isControlPressed() {
        return isControlPressed;
    }

    public void handleCanvasClick(MouseEvent mouseEvent) {
        startX = mouseEvent.getX();
        startY = mouseEvent.getY();
    }

    public void handleCanvasDrag(MouseEvent mouseEvent) {
        if (shapeCreatorController.isDrawingShape()) {
            double currentX = mouseEvent.getX();
            double currentY = mouseEvent.getY();
        }
    }

    public void handleCanvasRelease(MouseEvent mouseEvent) {

        if (shapeCreatorController.isDrawingShape()) {
            double endX = mouseEvent.getX();
            double endY = mouseEvent.getY();

            boolean isRegular = mouseEvent.isShiftDown();

            shapeView.drawShape(shapeCreatorController.getCurrentShapeType(), startX, startY, Math.abs(endX-startX),
                    Math.abs(endY-startY), endX, endY, isRegular);
            }
    }
}
