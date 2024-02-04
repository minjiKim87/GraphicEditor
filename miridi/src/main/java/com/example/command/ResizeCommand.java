package com.example.command;

import com.example.controller.SizeController;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;

public class ResizeCommand implements Command {
    private SizeController sizeController;
    private Shape shape;
    private double width;
    private double height;

    public ResizeCommand(SizeController sizeController) {
        this.sizeController = sizeController;


    }

    @Override
    public void execute(MouseEvent event) {

    }

    @Override
    public void execute() {

    }

    public void handleDrag(MouseEvent event){
        sizeController.resizeShapes(event);
    }
}
