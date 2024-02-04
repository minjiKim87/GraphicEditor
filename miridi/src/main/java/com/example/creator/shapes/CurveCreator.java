package com.example.creator.shapes;

import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Shape;

public class CurveCreator implements IShapeCreator {
    @Override
    public Shape create(double startX, double startY, double endX, double endY) {
        return null;
    }

    @Override
    public Shape create(double startX, double startY, double controlX, double controlY, double endX, double endY) {
        return new QuadCurve(startX, startY, controlX, controlY, endX, endY);
    }
}
