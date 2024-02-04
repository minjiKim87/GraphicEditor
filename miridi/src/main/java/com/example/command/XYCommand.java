package com.example.command;

import com.example.controller.MultiSelectController;
import com.example.controller.PropertyController;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class XYCommand extends PropertyCommand {
    private final PropertyController propertyController;
    private MultiSelectController multiSelectController;
    @FXML
    private AnchorPane centerAnchorPane;

    public XYCommand(PropertyController propertyController, MultiSelectController multiSelectController) {
        super(propertyController, multiSelectController);
        this.propertyController = propertyController;

    }
    @Override
    public void handleInfoAnchorPaneClick(EventTarget eventTarget, MouseEvent mouseEvent){
            super.propertyController.handleInfoAnchorPaneClick(eventTarget, mouseEvent);
    }

    @Override
    public void handleXYTextFields(MultiSelectController multiSelectController){
        super.propertyController.handleXYTextFields(multiSelectController);
    }


    @Override
    public void execute(MouseEvent event) {

    }

    @Override
    public void execute(){};
    public void execute(MultiSelectController multiSelectController) {

        propertyController.handleXYTextFields(multiSelectController);
    }

}

