package com.example.controller;

import com.example.observer.PropertyObserver;
import com.example.observer.PropertyObserverImp;
import javafx.collections.ObservableList;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class PropertyController {

    private MultiSelectController multiSelectController;
    @FXML
    private AnchorPane centerAnchorPane;
    @FXML
    private Text xText;
    @FXML
    private Text yText;
    @FXML
    private Text wText;
    @FXML
    private Text hText;
    @FXML
    private Text rText;
    @FXML
    private Text gText;
    @FXML
    private Text bText;
    @FXML
    private final TextField xTextField;
    @FXML
    private final TextField yTextField;
    @FXML
    private final TextField wTextField;
    @FXML
    private final TextField hTextField;
    @FXML
    private final TextField rTextField;
    @FXML
    private final TextField gTextField;
    @FXML
    private final TextField bTextField;
    private List<PropertyObserver> observers = new ArrayList<>();

    public PropertyController(AnchorPane centerAnchorPane, Text xText, Text yText, Text wText, Text hText, Text rText, Text gText, Text bText, TextField xTextField, TextField yTextField, TextField wTextField, TextField hTextField, TextField rTextField, TextField gTextField, TextField bTextField) {
        this.centerAnchorPane = centerAnchorPane;
        this.xText = xText;
        this.yText = yText;
        this.wText = wText;
        this.hText = hText;
        this.rText = rText;
        this.gText = gText;
        this.bText = bText;
        this.xTextField = xTextField;
        this.yTextField = yTextField;
        this.wTextField = wTextField;
        this.hTextField = hTextField;
        this.rTextField = rTextField;
        this.gTextField = gTextField;
        this.bTextField = bTextField;
        this.multiSelectController = new MultiSelectController(centerAnchorPane);
    }


    public void addObserver(PropertyObserver observer) {
        observers.add(observer);

    }

    public void removeObserver(PropertyObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(double x, double y, double width, double height, int r, int g, int b) {
        for (PropertyObserver observer : observers) {

            observer.update(x, y, width, height, r, g, b);

        }
    }

    public void handleInfoAnchorPaneClick(EventTarget eventTarget, MouseEvent mouseEvent) {
        if (eventTarget!= centerAnchorPane) {
            double x = 0;
            double y = 0;
            double width = 0;
            double height = 0;
            double clickX = mouseEvent.getX();
            double clickY = mouseEvent.getY();

            Shape clickedShape = null;
            for (Node node : centerAnchorPane.getChildren()) {
                if (node instanceof Shape) {
                    Shape shape = (Shape) node;
                    if (shape.contains(clickX, clickY)) {
                        clickedShape = shape;
                        break;
                    }
                }
            }
            if (clickedShape != null) {
                if (clickedShape instanceof Ellipse) {
                    Ellipse ellipse = (Ellipse) clickedShape;
                    x = ellipse.getCenterX();
                    y = ellipse.getCenterY();
                    width = 2 * ellipse.getRadiusX();
                    height = 2 * ellipse.getRadiusY();
                } else if (clickedShape instanceof Circle) {
                    Circle circle = (Circle) clickedShape;
                    x = circle.getCenterX();
                    y = circle.getCenterY();
                    width = 2 * circle.getRadius();
                    height = 2 * circle.getRadius();
                } else if (clickedShape instanceof Rectangle) {
                    Rectangle rectangle = (Rectangle) clickedShape;
                    x = rectangle.getX();
                    y = rectangle.getY();
                    width = rectangle.getWidth();
                    height = rectangle.getHeight();
                } else if (clickedShape instanceof Polygon) {
                    Polygon triangle = (Polygon) clickedShape;
                    ObservableList<Double> points = triangle.getPoints();

                    double sumX = 0;
                    double sumY = 0;
                    for (int i = 0; i < points.size(); i += 2) {
                        double xPoint = points.get(i);
                        double yPoint = points.get(i + 1);
                        sumX += xPoint;
                        sumY += yPoint;
                    }
                    x = sumX / (points.size() / 2);
                    y = sumY / (points.size() / 2);


                    double minX = Double.MAX_VALUE;
                    double minY = Double.MAX_VALUE;
                    double maxX = Double.MIN_VALUE;
                    double maxY = Double.MIN_VALUE;

                    for (int i = 0; i < points.size(); i += 2) {
                        double xPoint = points.get(i);
                        double yPoint = points.get(i + 1);
                        minX = Math.min(minX, xPoint);
                        minY = Math.min(minY, yPoint);
                        maxX = Math.max(maxX, xPoint);
                        maxY = Math.max(maxY, yPoint);
                    }

                    width = maxX - minX;
                    height = maxY - minY;
                }
            } else if (clickedShape instanceof Line) {

                Line line = (Line) clickedShape;
                x = (line.getStartX() + line.getEndX()) / 2;
                y = (line.getStartY() + line.getEndY()) / 2;
                width = line.getStrokeWidth();
                height = 1;
            } else if (clickedShape instanceof QuadCurve) {
                QuadCurve quadCurve = (QuadCurve) clickedShape;
                x = (quadCurve.getStartX() + quadCurve.getEndX()) / 2;
                y = (quadCurve.getStartY() + quadCurve.getEndY()) / 2;
                width = quadCurve.getStrokeWidth();
                ;
                height = 1;
            }

            if (clickedShape != null && clickedShape.getFill() == null) {
                clickedShape.setFill(Color.TRANSPARENT);
            }
            Color fillColor = (Color) clickedShape.getFill();
            int r = (int) (fillColor.getRed() * 255);
            int g = (int) (fillColor.getGreen() * 255);
            int b = (int) (fillColor.getBlue() * 255);
            PropertyObserver observer = new PropertyObserverImp(xText, yText, wText, hText, rText, gText, bText);
            addObserver(observer);

            notifyObservers(x, y, width, height, r, g, b);

        }

        else{
            notifyObservers(0, 0, 0, 0, 0,0,0);
        }
    }

    public void ToFrontObject(List<Shape> selectedShapes){
        for (Shape shape : selectedShapes) {
            shape.toFront();
        }
    }

    public void ToBackObject(List<Shape> selectedShapes){
        for (Shape shape : selectedShapes) {
            shape.toBack();
        }
    }
    public void handleXYTextFields(MultiSelectController multiSelectController) {
        List<Shape> selectedShapes = multiSelectController.getSelectedShapes();

        if (!selectedShapes.isEmpty()) {
            double newX = Double.parseDouble(xTextField.getText());
            double newY = Double.parseDouble(yTextField.getText());

            for (Shape shape : selectedShapes) {
                if (shape instanceof Circle) {
                    Circle circle = (Circle) shape;
                    circle.setCenterX(newX);
                    circle.setCenterY(newY);

                } else if (shape instanceof Ellipse) {
                    Ellipse ellipse = (Ellipse) shape;
                    ellipse.setCenterX(newX);
                    ellipse.setCenterY(newY);
                } else if (shape instanceof Rectangle) {
                    Rectangle rectangle = (Rectangle) shape;
                    rectangle.setX(newX);
                    rectangle.setY(newY);
                }
                else if (shape instanceof Line) {
                    Line straight = (Line) shape;

                    double currentCenterX = (straight.getStartX() + straight.getEndX()) / 2;
                    double currentCenterY = (straight.getStartY() + straight.getEndY()) / 2;

                    double deltaX = newX - currentCenterX;
                    double deltaY = newY - currentCenterY;

                    straight.setStartX(straight.getStartX() + deltaX);
                    straight.setStartY(straight.getStartY() + deltaY);
                    straight.setEndX(straight.getEndX() + deltaX);
                    straight.setEndY(straight.getEndY() + deltaY);
                }
                else if (shape instanceof QuadCurve) {
                    QuadCurve curve = (QuadCurve) shape;

                    double currentCenterX = (curve.getStartX() + curve.getEndX() + curve.getControlX()) / 3;
                    double currentCenterY = (curve.getStartY() + curve.getEndY() + curve.getControlY()) / 3;

                    double deltaX = newX - currentCenterX;
                    double deltaY = newY - currentCenterY;

                    curve.setStartX(curve.getStartX() + deltaX);
                    curve.setStartY(curve.getStartY() + deltaY);
                    curve.setControlX(curve.getControlX() + deltaX);
                    curve.setControlY(curve.getControlY() + deltaY);
                    curve.setEndX(curve.getEndX() + deltaX);
                    curve.setEndY(curve.getEndY() + deltaY);
                }
                else if (shape instanceof Polygon){
                    Polygon triangle = (Polygon) shape;
                    ObservableList<Double> points = triangle.getPoints();

                    double centroidX = (points.get(0) + points.get(2) + points.get(4)) / 3.0;
                    double centroidY = (points.get(1) + points.get(3) + points.get(5)) / 3.0;
                    double moveX = newX - centroidX;
                    double moveY = newY - centroidY;
                  //  System.out.println("centroidY: "+centroidY+" moveY: "+moveY);
                    for (int i = 0; i < points.size(); i += 2) {
                        points.set(i, points.get(i) + moveX);
                        points.set(i + 1, points.get(i + 1) + moveY);
                       // System.out.println("centroidY: "+centroidY+" moveY: "+moveY+" i: "+i);
                    }
                }
            }
        }
        multiSelectController.showBoundingRectangle();
    }

    public void handleWHTextFields(MultiSelectController multiSelectController) {

        List<Shape> selectedShapes = multiSelectController.getSelectedShapes();

        if (!selectedShapes.isEmpty() && (wTextField.getText().equals(hTextField.getText()))) {
            double newW = Double.parseDouble(wTextField.getText());
            double newH = Double.parseDouble(hTextField.getText());
            for (Shape shape : selectedShapes) {
                if (shape instanceof Circle) {
                    Circle circle = (Circle) shape;
                    circle.setRadius(newW / 2.0);
                } else if (shape instanceof Ellipse) {
                    Ellipse ellipse = (Ellipse) shape;
                    ellipse.setRadiusX(newW / 2.0);
                    ellipse.setRadiusY(newH /2.0);
                } else if (shape instanceof Rectangle) {
                    Rectangle rectangle = (Rectangle) shape;
                    rectangle.setWidth(newW);
                    rectangle.setHeight(newH);
                } else if (shape instanceof Polygon) {
                    Polygon triangle = (Polygon) shape;

                    ObservableList<Double> points = triangle.getPoints();

                    double currentCenterX = (points.get(0) + points.get(2) + points.get(4)) / 3.0;
                    double currentCenterY = (points.get(1) + points.get(3) + points.get(5)) / 3.0;

                    double newWidth = newW;
                    double newHeight = newH;

                    points.set(0, currentCenterX - newWidth / 2.0);
                    points.set(1, currentCenterY + newHeight / 2.0);
                    points.set(2, currentCenterX + newWidth / 2.0);
                    points.set(3, currentCenterY + newHeight / 2.0);
                    points.set(4, currentCenterX);
                    points.set(5, currentCenterY - newHeight / 2.0);
                }

            }
        }
        multiSelectController.showBoundingRectangle();
    }



    public void handleRGBTextFields(MultiSelectController multiSelectController) {
        List<Shape> selectedShapes = multiSelectController.getSelectedShapes();

        if (!selectedShapes.isEmpty()) {
            int newR = Integer.parseInt(rTextField.getText());
            int newG = Integer.parseInt(gTextField.getText());
            int newB = Integer.parseInt(bTextField.getText());

            Color newColor = Color.rgb(newR, newG, newB);
            for (Shape shape : selectedShapes) {
                if (shape instanceof Circle) {
                    Circle circle = (Circle) shape;
                    circle.setFill(newColor);
                } else if (shape instanceof Ellipse) {
                    Ellipse ellipse = (Ellipse) shape;
                    ellipse.setFill(newColor);
                } else if (shape instanceof Rectangle) {
                    Rectangle rectangle = (Rectangle) shape;
                    rectangle.setFill(newColor);
                } else if (shape instanceof Line) {
                    Line line = (Line) shape;
                    line.setStroke(newColor);
                } else if (shape instanceof QuadCurve) {
                    QuadCurve curve = (QuadCurve) shape;
                    curve.setStroke(newColor);
                } else if (shape instanceof Polygon) {
                    Polygon triangle = (Polygon) shape;
                    triangle.setFill(newColor);
                }

            }
        }
    }
}
