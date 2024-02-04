package com.example.controller;

import com.example.view.SelectionShapeView;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.*;
import com.example.view.SelectionShapeView;

import java.util.List;

public class SizeController {
    private MultiSelectController multiSelectController;
    private double initialX;
    private double initialY;
    private SelectionShapeView selectionShapeView;

    public SizeController(MultiSelectController multiSelectController) {
        this.multiSelectController = multiSelectController;
    }

    public void setInitialPosition(double x, double y) {
        this.initialX = x;
        this.initialY = y;
    }
    public void resizeShapes(MouseEvent event) {

        System.out.println("initial " + initialX+", "+initialY);
        List<Shape> selectedShapes = multiSelectController.getSelectedShapes();

        double offsetX = event.getSceneX() - initialX;
        double offsetY = event.getSceneY() - initialY;


        double scale = 0.1;
        offsetX *= scale;
        offsetY *= scale;


        System.out.println(offsetX+", "+offsetY);

        for (Shape shape : selectedShapes) {
            if (shape instanceof Circle) {
                resizeCircle((Circle) shape, offsetX, offsetY);
            } else if (shape instanceof Ellipse) {
                resizeEllipse((Ellipse) shape, offsetX, offsetY);
            } else if (shape instanceof Rectangle) {
                resizeRectangle((Rectangle) shape, offsetX, offsetY);
            }else if (shape instanceof Polygon) { // triangle
                resizePolygon((Polygon) shape, offsetX, offsetY);
            } else if (shape instanceof Path) { // curve
                resizePath((Path) shape, offsetX, offsetY);
            }

        }

        multiSelectController.updateBoundingBox();

        initialX = event.getSceneX();
        initialY = event.getSceneY();
    }

    private void resizeCircle(Circle circle, double offsetX, double offsetY) {
        double newRadius = Math.max(circle.getRadius() + offsetX, 0);
        circle.setRadius(newRadius);
    }

    private void resizeEllipse(Ellipse ellipse, double offsetX, double offsetY) {
        double newRadiusX = Math.max(ellipse.getRadiusX() + offsetX, 0);
        double newRadiusY = Math.max(ellipse.getRadiusY() + offsetY, 0);
        ellipse.setRadiusX(newRadiusX);
        ellipse.setRadiusY(newRadiusY);
        System.out.println(ellipse);
    }

    private void resizeRectangle(Rectangle rectangle, double offsetX, double offsetY) {
        double newWidth = Math.max(rectangle.getWidth() + offsetX, 0);
        double newHeight = Math.max(rectangle.getHeight() + offsetY, 0);
        rectangle.setWidth(newWidth);
        rectangle.setHeight(newHeight);
    }

    private void resizePolygon(Polygon polygon, double offsetX, double offsetY) {
        ObservableList<Double> points = polygon.getPoints();
        Double[] newPoints = new Double[points.size()];
        for (int i = 0; i < points.size(); i += 2) {
            newPoints[i] = points.get(i) + offsetX;
            newPoints[i + 1] = points.get(i + 1) + offsetY;
        }
        polygon.getPoints().setAll(newPoints);
    }

    private void resizePath(Path path, double offsetX, double offsetY) {
        for (PathElement element : path.getElements()) {
            if (element instanceof MoveTo) {
                MoveTo moveTo = (MoveTo) element;
                moveTo.setX(moveTo.getX() + offsetX);
                moveTo.setY(moveTo.getY() + offsetY);
            } else if (element instanceof LineTo) {
                LineTo lineTo = (LineTo) element;
                lineTo.setX(lineTo.getX() + offsetX);
                lineTo.setY(lineTo.getY() + offsetY);
            }

        }
    }

}
