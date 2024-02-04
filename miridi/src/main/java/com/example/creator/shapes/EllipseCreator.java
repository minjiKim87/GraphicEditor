package com.example.creator.shapes;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

public class EllipseCreator implements IShapeCreator {
    @Override
    public Shape create(double startX, double startY, double endX, double endY) {
        return new Ellipse((startX + endX) / 2, (startY + endY) / 2, Math.abs(endX - startX) / 2, Math.abs(endY - startY) / 2);
    }
}
