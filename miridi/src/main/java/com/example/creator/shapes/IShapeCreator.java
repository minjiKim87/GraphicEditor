package com.example.creator.shapes;

import javafx.scene.shape.Shape;

public interface IShapeCreator {
    //Shape create(double startX, double startY, double endX, double endY);
    Shape create(double startX, double startY, double endX, double endY);

    // Overloaded method for curve with control points
    default Shape create(double startX, double startY, double controlX, double controlY, double endX, double endY) {
        // Default implementation (can throw an UnsupportedOperationException if you prefer)
        return create(startX, startY, endX, endY);
    }
}