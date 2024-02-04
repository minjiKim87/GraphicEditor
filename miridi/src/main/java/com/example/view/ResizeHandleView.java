package com.example.view;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class ResizeHandleView {

    private Rectangle handle;

    public ResizeHandleView(AnchorPane centerAnchorPane) {
        handle = new Rectangle(5, 5);
        handle.setFill(javafx.scene.paint.Color.RED);
        centerAnchorPane.getChildren().add(handle);
    }

    public Rectangle getHandle() {
        return handle;
    }
}
