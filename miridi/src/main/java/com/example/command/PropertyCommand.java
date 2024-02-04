package com.example.command;

import com.example.controller.MultiSelectController;
import com.example.controller.PropertyController;
import javafx.event.EventTarget;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public abstract class PropertyCommand implements Command {
    protected PropertyController propertyController;
    protected MultiSelectController multiSelectController;
    protected AnchorPane centerAnchorPane;

    public PropertyCommand(PropertyController propertyController, MultiSelectController multiSelectController) {
        this.propertyController = propertyController;
        this.multiSelectController = multiSelectController;
    }

    public void handleInfoAnchorPaneClick(EventTarget eventTarget, MouseEvent mouseEvent){

    }



    public void handleXYTextFields(MultiSelectController multiSelectController){}

    public void handleWHTextFields(MultiSelectController multiSelectController){}

    public void handleRGBTextFields(MultiSelectController multiSelectController){}

    public void execute(MultiSelectController multiSelectController){}

}