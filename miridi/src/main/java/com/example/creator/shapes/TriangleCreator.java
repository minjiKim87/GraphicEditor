package com.example.creator.shapes;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class TriangleCreator implements IShapeCreator {
    private boolean isRegular;

    public TriangleCreator() {
        this.isRegular = false;
    }
    public TriangleCreator(boolean isRegular) {
        this.isRegular = isRegular;
    }
    @Override
    public Shape create(double startX, double startY, double endX, double endY) {
        if (isRegular) {
            double sideLength = Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));
            double height = (Math.sqrt(3) / 2) * sideLength;
            Polygon triangle = new Polygon();
            triangle.getPoints().addAll(startX, startY + height,
                    startX + sideLength, startY + height,
                    startX + sideLength / 2, startY);
            return triangle;
        } else {

            Polygon triangle = new Polygon();
            triangle.getPoints().addAll(startX, startY,
                    endX, endY,
                    2 * startX - endX, endY);
            return triangle;
        }
    }
}
