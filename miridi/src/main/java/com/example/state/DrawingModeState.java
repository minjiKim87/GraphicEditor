package com.example.state;

import com.example.command.Command;
import com.example.command.CreateCircleCommand;
import com.example.controller.*;
import com.example.creator.ShapeFactory;
import com.example.state.EditorState;
import com.example.view.ShapeView;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.util.List;

public class DrawingModeState extends EditorState {
    private ShapeFactory shapeFactory;
    private ShapeController shapeController;
    private ShapeCreatorController shapeCreatorController;
    private ShapeView shapeView;


    public DrawingModeState(AnchorPane centerAnchorPane, List<Shape> selectedShapes, MoveController moveController, ShapeController shapeController, ShapeCreatorController shapeCreatorController, MultiSelectController multiSelectController, PropertyController propertyController, List<Button> buttons) {
        super(centerAnchorPane, selectedShapes, moveController, shapeController, shapeCreatorController, multiSelectController, propertyController, buttons);
        this.shapeFactory = new ShapeFactory();
        this.shapeController = shapeController;
        this.shapeCreatorController = shapeCreatorController;
        this.shapeView = new ShapeView(centerAnchorPane);

    }

    @Override
    public void handleMouseClick(MouseEvent event) {
      //  System.out.println("DrawingModeState Click");
    }

    @Override
    public void handleKeyPress(KeyEvent event) {
        // 키 입력 처리 로직
    }

    @Override
    public void handleMouseDrag(MouseEvent event) {
       // System.out.println("DrawingModeState Drag");
    }

    @Override
    public void handleMouseRelease(MouseEvent event) {
        //System.out.println("DrawingModeState Release");
    }
    @Override
    public void activate(){
        shapeCreatorController.setDrawingMode(true);
    };
    @Override
    public void deactivate(){ //어디서 쓰일지를 모르겠네 다른 버튼 클릭시인데..
        shapeCreatorController.setDrawingMode(false);
    };
}