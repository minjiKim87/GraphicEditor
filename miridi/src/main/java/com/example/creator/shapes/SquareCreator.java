package com.example.creator.shapes;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SquareCreator implements IShapeCreator {
    @Override
    public Shape create(double startX, double startY, double endX, double endY) {
        double sideLength = Math.min(Math.abs(endX - startX), Math.abs(endY - startY));
        double topLeftX = (endX > startX) ? startX : startX - sideLength;
        double topLeftY = (endY > startY) ? startY : startY - sideLength;

        Rectangle square = new Rectangle(topLeftX, topLeftY, sideLength, sideLength);
        square.setId("shapeRectangle");
        return square;
    }
}
