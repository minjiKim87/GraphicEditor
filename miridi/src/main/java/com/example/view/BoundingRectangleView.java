package com.example.view;

import com.example.model.BoundingRectangleModel;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoundingRectangleView {

    private static final String BOUNDING_BOX_ID = "boundingBox";
    private AnchorPane centerAnchorPane;
    private BoundingRectangleModel boundingRectangleModel;



    public BoundingRectangleView(AnchorPane centerAnchorPane, BoundingRectangleModel boundingRectangleModel) {
        this.centerAnchorPane = centerAnchorPane;
        this.boundingRectangleModel = boundingRectangleModel;
    }

    public void update(BoundingRectangleModel model) {
        Rectangle newBoundingRectangle = model.getBoundingRectangle();

        newBoundingRectangle.setId(BOUNDING_BOX_ID);


        centerAnchorPane.getChildren().removeIf(node ->
                node instanceof Rectangle && !"shapeRectangle".equals(node.getId()));

        if (!centerAnchorPane.getChildren().contains(newBoundingRectangle)) {
            centerAnchorPane.getChildren().add(newBoundingRectangle);
        }

        model.setBoundingRectangle(newBoundingRectangle);
    }



}
