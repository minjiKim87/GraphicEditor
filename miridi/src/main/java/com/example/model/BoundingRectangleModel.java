package com.example.model;

import javafx.geometry.Bounds;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;
import java.util.List;

public class BoundingRectangleModel {
    private Rectangle boundingRectangle = new Rectangle();
    private AnchorPane centerAnchorPane;

    public BoundingRectangleModel(AnchorPane centerAnchorPane) {
        this.centerAnchorPane = centerAnchorPane;
        boundingRectangle.setFill(null);
        boundingRectangle.setStroke(Color.BLUE);
        boundingRectangle.getStrokeDashArray().addAll(5.0, 5.0);
    }

    private void initializeRectangle() {
        boundingRectangle.setFill(null);
        boundingRectangle.setStroke(Color.BLUE);
        boundingRectangle.getStrokeDashArray().addAll(5.0, 5.0);
    }

    public void showBoundingRectangle(List<Shape> selectedShapes) {

        if (!selectedShapes.isEmpty()) {
            calculateBoundingRectangle(selectedShapes);
            if (!centerAnchorPane.getChildren().contains(boundingRectangle)) {
                centerAnchorPane.getChildren().add(boundingRectangle);
            }
        } else {
            centerAnchorPane.getChildren().remove(boundingRectangle);
            //System.out.println("boundingRectangle remove");
        }
    }

    public void removeBoundingRectangle() {
        centerAnchorPane.getChildren().remove(boundingRectangle);
    }

    public void calculateBoundingRectangle(List<Shape> selectedShapes) {
        double buffer = 5.0;
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;

        for (Shape shape : selectedShapes) {

            Bounds sceneBounds = shape.getBoundsInParent();
            minX = Math.min(minX, sceneBounds.getMinX());
            minY = Math.min(minY, sceneBounds.getMinY());
            maxX = Math.max(maxX, sceneBounds.getMaxX());
            maxY = Math.max(maxY, sceneBounds.getMaxY());
        }

        boundingRectangle.setTranslateX(0);
        boundingRectangle.setTranslateY(0);

        boundingRectangle.setX(minX - buffer);
        boundingRectangle.setY(minY - buffer);
        boundingRectangle.setWidth((maxX - minX) + 2 * buffer);
        boundingRectangle.setHeight((maxY - minY) + 2 * buffer);
    }

    public void move(double offsetX, double offsetY) {
        //System.out.println("boundingBox move : "+offsetX+", "+offsetY);
        boundingRectangle.setTranslateX(boundingRectangle.getTranslateX() + offsetX);
        boundingRectangle.setTranslateY(boundingRectangle.getTranslateY() + offsetY);

    }

    public javafx.geometry.Point2D getCenter() {
        double centerX = boundingRectangle.getX() + boundingRectangle.getWidth() / 2.0;
        double centerY = boundingRectangle.getY() + boundingRectangle.getHeight() / 2.0;
        return new javafx.geometry.Point2D(centerX, centerY);
    }


    public Rectangle getBoundingRectangle() {
        return boundingRectangle;
    }

    public void setBoundingRectangle(Rectangle boundingRectangle) {
        this.boundingRectangle = boundingRectangle;
    }
}
