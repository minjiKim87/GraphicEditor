package com.example.state;

import com.example.command.Command;
import com.example.controller.*;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.util.List;

public class PropertyState extends EditorState {
    private PropertyController propertyController;
    private MultiSelectController multiSelectController;

    //command 패턴
    private Command PropertyCommand;

    public PropertyState(AnchorPane centerAnchorPane, List<Shape> selectedShapes, MoveController moveController, ShapeController shapeController, ShapeCreatorController shapeCreatorController, MultiSelectController multiSelectController, PropertyController propertyController, List<Button> buttons) {
        super(centerAnchorPane, selectedShapes, moveController, shapeController, shapeCreatorController, multiSelectController, propertyController, buttons);
        this.propertyController = propertyController;
        this.multiSelectController = multiSelectController;
    }

    @Override
    public void handleMouseClick(MouseEvent event) {
       //System.out.println("PropertyState");
    } //안쓸듯 속성값 조회는 기존 유지(centerAnchorPane이 propertyController에 접근하는것도 분리해야하나 고민)

    @Override
    public void handleKeyPress(KeyEvent event) {
        // enter 시
    }

    @Override
    public void handleMouseDrag(MouseEvent event) {} //미사용

    @Override
    public void handleMouseRelease(MouseEvent event) {} //미사용
    @Override
    public void activate(){}// 미사용
    @Override
    public void deactivate(){};// 미사용
}