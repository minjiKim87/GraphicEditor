package com.example.creator.shapes;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RectangleCreator implements IShapeCreator {
    @Override
    public Shape create(double startX, double startY, double endX, double endY) {
        double width = Math.abs(endX - startX);
        double height = Math.abs(endY - startY);
        double topLeftX = Math.min(startX, endX);
        double topLeftY = Math.min(startY, endY);

        Rectangle rectangle = new Rectangle(topLeftX, topLeftY, width, height);
        rectangle.setId("shapeRectangle");
        return rectangle;
    }
}