package com.example.creator.shapes;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class CircleCreator implements IShapeCreator {
    @Override
    public Shape create(double startX, double startY, double endX, double endY) {
        double radius = Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));

        return new Circle(startX, startY, radius);
    }
}
