package com.example.state;

import com.example.command.ToBackCommand;
import com.example.controller.*;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.util.List;

public class ToBackState extends EditorState{
    private ToBackCommand toBackCommand;
    private HelloController hellocontroller;
    private MultiSelectController multiSelectController;

    public void handleMouseClick(MouseEvent event){};
    public void handleKeyPress(KeyEvent event){};
    public void handleMouseDrag(MouseEvent event){};
    public void handleMouseRelease(MouseEvent event){};

    public ToBackState(AnchorPane centerAnchorPane, List<Shape> selectedShapes, MoveController moveController, ShapeController shapeController, ShapeCreatorController shapeCreatorController, MultiSelectController multiSelectController, PropertyController propertyController, List<Button> buttons) {
        super(centerAnchorPane, selectedShapes, moveController, shapeController, shapeCreatorController, multiSelectController, propertyController, buttons);
        this.toBackCommand = new ToBackCommand(propertyController, multiSelectController);
    }

    @Override
    public void activate(){};

    public void activate(List<Shape> selectedShapes) {
       // System.out.println("ToBackState IN");
        toBackCommand.execute(selectedShapes);
       // System.out.println("ToBackState excute");
    }

    @Override
    public void deactivate() {
    }
}
