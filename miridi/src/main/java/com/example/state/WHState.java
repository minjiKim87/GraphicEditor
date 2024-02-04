package com.example.state;

import com.example.command.WHCommand;
import com.example.controller.*;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.util.List;

public class WHState extends PropertyState{
    private WHCommand whCommand;
    private PropertyController propertyController;
    private MultiSelectController multiSelectController;

    public WHState(AnchorPane centerAnchorPane, List<Shape> selectedShapes, MoveController moveController, ShapeController shapeController, ShapeCreatorController shapeCreatorController, MultiSelectController multiSelectController, PropertyController propertyController, List<Button> buttons) {
        super(centerAnchorPane, selectedShapes, moveController, shapeController, shapeCreatorController, multiSelectController, propertyController, buttons);
        this.whCommand = new WHCommand(propertyController, multiSelectController);
        this.propertyController = propertyController;
    }

    //@Override
    public void activate(MultiSelectController multiSelectController) {
        whCommand.execute(multiSelectController);
    }

    @Override
    public void deactivate() {
    }
}
