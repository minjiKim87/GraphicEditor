package com.example.view;

import com.example.controller.MultiSelectController;
import com.example.model.ShapeSelectionModel;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;

public class SelectionShapeView {
    private ShapeSelectionModel model;
    private AnchorPane centerAnchorPane;
    private MultiSelectController multiSelectController;

    public Rectangle boundingRectangle = new Rectangle();

    public SelectionShapeView(ShapeSelectionModel model, AnchorPane centerAnchorPane, MultiSelectController multiSelectController) {
        this.model = model;
        this.centerAnchorPane = centerAnchorPane;
        this.multiSelectController = multiSelectController;
        boundingRectangle.setFill(null);
        boundingRectangle.setStroke(Color.BLUE);
        boundingRectangle.getStrokeDashArray().addAll(5.0, 5.0);
    }

    public void updateView() {

        for (Node node : centerAnchorPane.getChildren()) {
            if (node instanceof Shape) {
                ((Shape) node).setStroke(Color.BLACK);
            }
        }

        for (Shape shape : model.getSelectedShapes()) {
            shape.setStroke(Color.GREEN);
        }
    }

    public void deleteSelectedShapes() {
        for (Shape shape : model.getSelectedShapes()) {
            centerAnchorPane.getChildren().remove(shape);
        }
    }

    public void deselectedShapes() {
        for (Shape shape : model.getSelectedShapes()) {
            shape.setStroke(Color.BLACK);
        }
    }
}
