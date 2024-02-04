package com.example.controller;

public class ShapeCreatorController {

    private boolean isDrawing = false;
    private String currentShapeType = "";

    public void handleShapeCreation(String shapeType) {
        this.currentShapeType = shapeType;
        //System.out.println("ShapeCreatorController");
    }

    public void setDrawingMode(boolean mode){
        this.isDrawing = mode;
    }

    public boolean isDrawingShape() {return isDrawing;}

    public String getCurrentShapeType() {return currentShapeType;}

    public void setCurrentShapeType(String shapeType) {
        this.currentShapeType = shapeType;
    }
}
