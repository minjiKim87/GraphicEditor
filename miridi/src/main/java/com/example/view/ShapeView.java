package com.example.view;

import com.example.creator.ShapeFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class ShapeView {

    private final AnchorPane centerAnchorPane;
    private final ShapeFactory shapeFactory = new ShapeFactory();

    public ShapeView(AnchorPane centerAnchorPane) {
        this.centerAnchorPane = centerAnchorPane;
    }

    public void drawShape(String shapeType, double startX, double startY, double controlX, double controlY, double endX, double endY, boolean isRegular) {
        Shape shape;
        switch (shapeType) {
            case "circle":
                if (isRegular) {
                    shape = shapeFactory.createCircle(startX, startY, endX, endY);
                } else {
                    shape = shapeFactory.createEllipse(startX, startY, endX, endY);
                }
                break;
            case "square":
                if (isRegular) {
                    shape = shapeFactory.createSquare(startX, startY, endX, endY);
                } else {
                    shape = shapeFactory.createRectangle(startX, startY, endX, endY);
                }
                break;
            case "triangle":
                shape = shapeFactory.createTriangle(startX, startY, endX, endY, isRegular);
                break;
            case "straight":
                    shape = shapeFactory.createStraight(startX, startY, endX, endY);
                break;
            case "curve":
                shape = shapeFactory.createCurve(startX, startY, controlX, controlY, endX, endY);
                break;
            default:
                shape = null;
                break;
        }

        if (shape != null) {
            shape.setStroke(Color.BLACK);
            shape.setFill(Color.TRANSPARENT);
            centerAnchorPane.getChildren().add(shape);
        }
    }
}
