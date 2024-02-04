package com.example.state;

import com.example.command.XYCommand;
import com.example.controller.*;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.util.List;

public class XYState extends PropertyState{
    private XYCommand xyCommand;
    private PropertyController propertyController;
    private MultiSelectController multiSelectController;

    public XYState(AnchorPane centerAnchorPane, List<Shape> selectedShapes, MoveController moveController, ShapeController shapeController, ShapeCreatorController shapeCreatorController, MultiSelectController multiSelectController, PropertyController propertyController, List<Button> buttons) {
        super(centerAnchorPane, selectedShapes, moveController, shapeController, shapeCreatorController, multiSelectController, propertyController, buttons);
        this.xyCommand = new XYCommand(propertyController, multiSelectController);
        this.propertyController = propertyController;
    }

    //@Override
    public void activate(MultiSelectController multiSelectController) {
        xyCommand.execute(multiSelectController);

    }

    @Override
    public void deactivate() {

    }
}
