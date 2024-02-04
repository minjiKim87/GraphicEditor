package com.example.controller;

import com.example.model.BoundingRectangleModel;
import com.example.view.BoundingRectangleView;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.*;

import java.util.List;

public class MoveController {

    private double lastX;
    private double lastY;
    private BoundingRectangleModel boundingRectangleModel;
    private MultiSelectController multiSelectController;
    private HelloController helloController;
    private BoundingRectangleView boundingRectangleView;

    public MoveController(BoundingRectangleModel boundingRectangleModel, MultiSelectController multiSelectController, HelloController helloController, AnchorPane centerAnchorPane) {
        this.boundingRectangleModel = boundingRectangleModel;
        this.multiSelectController = multiSelectController;
        this.helloController = helloController;
        this.boundingRectangleView = new BoundingRectangleView(centerAnchorPane, boundingRectangleModel);
    }


    public void startMove(MouseEvent event) {

        lastX = event.getSceneX();
        lastY = event.getSceneY();
        System.out.println("MoveController - startMove : "+lastX +", "+lastY);
    }

    public void moveShapes(MouseEvent event, List<Shape> selectedShapes, MultiSelectController multiSelectController) {
        double offsetX = event.getSceneX() - lastX;
        double offsetY = event.getSceneY() - lastY;


        if (selectedShapes.isEmpty()) {

            return;
        }


        for (Shape shape : selectedShapes) {

            shape.setTranslateX(0);
            shape.setTranslateY(0);

            if (shape instanceof Circle) {
                Circle circle = (Circle) shape;
                circle.setCenterX(circle.getCenterX() + offsetX);
                circle.setCenterY(circle.getCenterY() + offsetY);
            }
            else if (shape instanceof Ellipse) {
                Ellipse ellipse = (Ellipse) shape;
                ellipse.setCenterX(ellipse.getCenterX() + offsetX);
                ellipse.setCenterY(ellipse.getCenterY() + offsetY);
            } else if (shape instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) shape;
                rectangle.setX(rectangle.getX() + offsetX);
                rectangle.setY(rectangle.getY() + offsetY);
                double centerX = rectangle.getX() + rectangle.getWidth() / 2;
                double centerY = rectangle.getY() + rectangle.getHeight() / 2;


  
            }else if (shape instanceof Polygon) {
                Polygon triangle = (Polygon) shape;
                ObservableList<Double> points = triangle.getPoints();
                for (int i = 0; i < points.size(); i+=2) {
                    points.set(i, points.get(i) + offsetX);
                    points.set(i+1, points.get(i+1) + offsetY);
                }
            }
            else if (shape instanceof Line) {
                Line straight = (Line) shape;
                straight.setStartX(straight.getStartX() + offsetX);
                straight.setStartY(straight.getStartY() + offsetY);
                straight.setEndX(straight.getEndX() + offsetX);
                straight.setEndY(straight.getEndY() + offsetY);
            }
            else if (shape instanceof QuadCurve) {
                QuadCurve curve = (QuadCurve) shape;
                curve.setStartX(curve.getStartX() + offsetX);
                curve.setStartY(curve.getStartY() + offsetY);
                curve.setControlX(Math.abs(curve.getEndX() - curve.getStartX()));
                curve.setControlY(Math.abs(curve.getEndY()-curve.getStartY()));
                curve.setEndX(curve.getEndX() + offsetX);
                curve.setEndY(curve.getEndY() + offsetY);
            }
        }

        Point2D oldCenter = boundingRectangleModel.getCenter();

        boundingRectangleModel.calculateBoundingRectangle(selectedShapes);
        boundingRectangleModel.move(offsetX, offsetY);
        Point2D newCenter = boundingRectangleModel.getCenter();


        lastX = event.getSceneX();
        lastY = event.getSceneY();


        updateView();


    }

    private void updateView() {
        boundingRectangleView.update(boundingRectangleModel);
    }
    public void deactivateMoveMode() {
        //System.out.println("deactivateMoveMode");
        multiSelectController.deselectShapes();
        multiSelectController.clearSelection();

        boundingRectangleModel.removeBoundingRectangle();
    }

    public void activateMoveMode() {
    }
}
