package com.example.controller;

import com.example.model.ResizeHandleModel;
import com.example.view.ResizeHandleView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ResizeHandleController {

    private ResizeHandleModel model;
    private ResizeHandleView view;

    public ResizeHandleController(ResizeHandleModel model, ResizeHandleView view) {
        this.model = model;
        this.view = view;

        view.getHandle().addEventHandler(MouseEvent.MOUSE_DRAGGED, this::handleDrag);
    }

    private void handleDrag(MouseEvent event) {
        model.setX(event.getSceneX());
        model.setY(event.getSceneY());
    }
}
