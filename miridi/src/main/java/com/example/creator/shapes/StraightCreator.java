package com.example.creator.shapes;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class StraightCreator implements IShapeCreator {
    @Override
    public Shape create(double startX, double startY, double endX, double endY) {
        return new Line(startX, startY, endX, endY);
    }
}
