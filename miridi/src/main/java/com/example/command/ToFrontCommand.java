package com.example.command;

import com.example.controller.MultiSelectController;
import com.example.controller.PropertyController;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.util.List;

public class ToFrontCommand extends PropertyCommand {
    private PropertyController propertyController;
    private MultiSelectController multiSelectController;
    @FXML
    private AnchorPane centerAnchorPane;

    public ToFrontCommand(PropertyController propertyController, MultiSelectController multiSelectController) {
        super(propertyController, multiSelectController);
        this.propertyController = propertyController;
        this.multiSelectController = multiSelectController;
    }



    @Override
    public void execute(MouseEvent event) {

    }

    @Override
    public void execute(){};
    public void execute(List<Shape> selectedShapes) {
        System.out.println("toFrontCommand IN");
        propertyController.ToFrontObject(selectedShapes);
        System.out.println("ToFrontObject execute");
    }

}

