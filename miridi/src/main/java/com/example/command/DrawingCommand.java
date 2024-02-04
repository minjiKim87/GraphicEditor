package com.example.command;

import com.example.controller.ShapeController;
import javafx.scene.input.MouseEvent;

public abstract class DrawingCommand implements Command {
    protected ShapeController shapeController;
    public DrawingCommand(ShapeController shapeController) {
        this.shapeController = shapeController;
    }
    public abstract void startDrawing(MouseEvent event);
    public abstract void updateDrawing(MouseEvent event);
    public abstract void finishDrawing(MouseEvent event);

    @Override
    public abstract void execute();
}
