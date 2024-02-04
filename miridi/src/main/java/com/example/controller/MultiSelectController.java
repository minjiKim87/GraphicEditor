package com.example.controller;

import com.example.model.BoundingRectangleModel;
import com.example.model.ShapeSelectionModel;
import com.example.view.SelectionShapeView;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.List;

public class MultiSelectController{
    @FXML
    private AnchorPane centerAnchorPane;
    public Rectangle selectionRectangle = new Rectangle();
    private BoundingRectangleModel boundingRectangleModel;
    private ShapeSelectionModel shapeSelectionModel;
    //private SizeController sizeController;
    private SelectionShapeView selectionShapeView;
    private double startX;
    private double startY;


    public MultiSelectController(AnchorPane centerAnchorPane) {
        this.centerAnchorPane = centerAnchorPane;
        this.shapeSelectionModel = new ShapeSelectionModel();
        this.selectionShapeView = new SelectionShapeView(shapeSelectionModel, centerAnchorPane,this);
        this.boundingRectangleModel = new BoundingRectangleModel(centerAnchorPane);
        initializeRectangle();

    }

    public MultiSelectController() {
        initializeRectangle();
    }


    private void initializeRectangle() {
        selectionRectangle.setFill(null);
        selectionRectangle.setStroke(Color.BLUE);
        selectionRectangle.getStrokeDashArray().addAll(5.0, 5.0);
    }

    public void showBoundingRectangle() {
        List<Shape> selectedShapes = shapeSelectionModel.getSelectedShapes();

        boundingRectangleModel.showBoundingRectangle(selectedShapes);

    }

    public Rectangle getSelectionRectangle() {
        return selectionRectangle;
    }


    public void startSelection(double x, double y) {
        startX = x;
        startY = y;
        selectionRectangle.setX(startX);
        selectionRectangle.setY(startY);
        selectionRectangle.setWidth(0);
        selectionRectangle.setHeight(0);
        centerAnchorPane.getChildren().add(selectionRectangle);

    }


    public void updateSelection(double x, double y) {
        selectionRectangle.setWidth(x - startX);
        selectionRectangle.setHeight(y - startY);
    }


    public void finishSelection(MouseEvent event, boolean isControlPressed) {

        List<Shape> currentSelectedShapes = shapeSelectionModel.getSelectedShapes();
        Rectangle currentBoundingRectangle = boundingRectangleModel.getBoundingRectangle();

        showCurrentSelectedShapes();

        if (Math.abs(selectionRectangle.getWidth()) < 5 && Math.abs(selectionRectangle.getHeight()) < 5) {
            Shape topShape = null;
            for (Node node : centerAnchorPane.getChildren()) {
                if (node.contains(event.getX(), event.getY()) &&  node != selectionRectangle && node != currentBoundingRectangle) {
                    topShape = (Shape) node;
                }
            }
            if (topShape == null) {
                selectionShapeView.updateView();
                shapeSelectionModel.clearSelection();


            } else {
                if (isControlPressed) {
                    if (currentSelectedShapes.contains(topShape)) {
                       // System.out.println("Ctrl Pressed /REMOVE: " + topShape);
                        shapeSelectionModel.deselectShape(topShape);
                    } else {
                        //System.out.println("Ctrl Pressed /ADD: " + topShape);
                        shapeSelectionModel.selectShape(topShape);
                    }
                } else {
                    if (!currentSelectedShapes.contains(topShape)) {
                        //System.out.println("ADD");
                        shapeSelectionModel.selectShape(topShape);
                    }
                }
            }

            selectionShapeView.updateView();
        } else {
            for (Node node : centerAnchorPane.getChildren()) {
                if ( node != selectionRectangle && node != currentBoundingRectangle) {
                    Shape shape = (Shape) node;
                    Bounds shapeBounds = shape.getBoundsInParent();
                    Bounds selectionBounds = selectionRectangle.getBoundsInParent();

                    if (shapeBounds.intersects(selectionBounds)) {
                        if (isControlPressed && currentSelectedShapes.contains(shape)) {
                            shapeSelectionModel.deselectShape(shape);
                        } else if (!currentSelectedShapes.contains(shape)) {
                            shapeSelectionModel.selectShape(shape);
                        }
                    } else if (!isControlPressed && currentSelectedShapes.contains(shape)) {
                        shapeSelectionModel.deselectShape(shape);
                    }
                }
            }
            selectionShapeView.updateView();
        }


        centerAnchorPane.getChildren().remove(selectionRectangle);
        showBoundingRectangle();
    }


    public void clearSelection() {
        boundingRectangleModel.removeBoundingRectangle();
        shapeSelectionModel.clearSelection();

    }

    public List<Shape> getSelectedShapes() {
        return shapeSelectionModel.getSelectedShapes();
    }

    public void selectShape(Shape shape) {

        shapeSelectionModel.selectShape(shape);
        selectionShapeView.updateView();
        centerAnchorPane.requestFocus();
        showBoundingRectangle();

    }


    public void deselectShapes() {
        selectionShapeView.deselectedShapes();
        shapeSelectionModel.clearSelection();

    }

    public void deleteSelectedShapes() {

        selectionShapeView. deleteSelectedShapes();
        shapeSelectionModel.deleteSelectedShapes();
        selectionShapeView.updateView();
    }


    public void showCurrentSelectedShapes() {
        List<Shape> selectedShapes = shapeSelectionModel.getSelectedShapes();

    }

    public void updateBoundingBox() {
        List<Shape> selectedShapes = getSelectedShapes();
        boundingRectangleModel.calculateBoundingRectangle(selectedShapes);
    }

}