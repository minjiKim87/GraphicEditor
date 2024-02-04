package com.example.state;

import com.example.command.RGBCommand;
import com.example.controller.*;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.util.List;

public class RGBState extends PropertyState{
    private RGBCommand rgbCommand;
    private PropertyController propertyController;
    private MultiSelectController multiSelectController;

    public RGBState(AnchorPane centerAnchorPane, List<Shape> selectedShapes, MoveController moveController, ShapeController shapeController, ShapeCreatorController shapeCreatorController, MultiSelectController multiSelectController, PropertyController propertyController, List<Button> buttons) {
        super(centerAnchorPane, selectedShapes, moveController, shapeController, shapeCreatorController, multiSelectController, propertyController, buttons);
        this.rgbCommand = new RGBCommand(propertyController, multiSelectController);
        this.propertyController = propertyController;
    }

    //@Override
    public void activate(MultiSelectController multiSelectController) {
        rgbCommand.execute(multiSelectController);
    }

    @Override
    public void deactivate() {
    }
}
