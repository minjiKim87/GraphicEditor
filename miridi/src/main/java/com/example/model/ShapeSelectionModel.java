package com.example.model;

import javafx.scene.shape.Shape;
import java.util.ArrayList;
import java.util.List;

public class ShapeSelectionModel {
    private List<Shape> selectedShapes = new ArrayList<>();
    public List<Shape> getSelectedShapes() {
        return selectedShapes;
    }

    public void selectShape(Shape shape) {
        if (!selectedShapes.contains(shape)) {
            selectedShapes.add(shape);
        }
    }

    public void deselectShape(Shape shape) {
        if (selectedShapes.contains(shape)) {
            selectedShapes.remove(shape);
        }
    }
    public void clearSelection() {
        selectedShapes.clear();
    }

    public void deleteSelectedShapes() {
        selectedShapes.clear();

    }
}
