package com.example.state;

import com.example.command.ToFrontCommand;
import com.example.controller.*;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.util.List;

public class ToFrontState extends EditorState{
    private ToFrontCommand toFrontCommand;
    private HelloController hellocontroller;
    private MultiSelectController multiSelectController;

    public void handleMouseClick(MouseEvent event){};
    public void handleKeyPress(KeyEvent event){};
    public void handleMouseDrag(MouseEvent event){};
    public void handleMouseRelease(MouseEvent event){};

    public ToFrontState(AnchorPane centerAnchorPane, List<Shape> selectedShapes, MoveController moveController, ShapeController shapeController, ShapeCreatorController shapeCreatorController, MultiSelectController multiSelectController, PropertyController propertyController, List<Button> buttons) {
        super(centerAnchorPane, selectedShapes, moveController, shapeController, shapeCreatorController, multiSelectController, propertyController, buttons);
        this.toFrontCommand = new ToFrontCommand(propertyController, multiSelectController); //기본 state구조 자체가 hellocontroller는 받고있지 않음
    }

    @Override
    public void activate(){};

    public void activate(List<Shape> selectedShapes) {
       // System.out.println("TofrontState IN");
        toFrontCommand.execute(selectedShapes);
        //System.out.println("TofrontState excute");
    }

    @Override
    public void deactivate() {
    }
}
