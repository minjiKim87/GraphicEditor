package com.example.controller;

import com.example.observer.PropertyObserver;
import com.example.command.Command;
import com.example.command.CreateCircleCommand;
import com.example.command.MoveCommand;
import com.example.observer.PropertyObserverImp;
import com.example.state.*;
import javafx.scene.shape.*;
import com.example.creator.ShapeFactory;
import com.example.model.BoundingRectangleModel;
import com.example.view.ShapeView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class HelloController {
    @FXML
    private AnchorPane centerAnchorPane;

    @FXML
    private ShapeFactory shapeFactory = new ShapeFactory();
    private List<Shape> selectedShapes = new ArrayList<>();
    private MoveController moveController;
    private ShapeController shapeController;
    private ShapeCreatorController shapeCreatorController = new ShapeCreatorController();
    private MultiSelectController multiSelectController;
    private PropertyController PropertyController;
    private SizeController sizeController;


    List<Button> buttons;
    @FXML
    private Button cursorButton;
    @FXML
    private Button circleButton;
    @FXML
    private Button squareButton;
    @FXML
    private Button triangleButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button moveButton;
    @FXML
    private Button toBackButton;
    @FXML
    private Button toFrontButton;
    @FXML
    private Button straightButton;
    @FXML
    private Button curveButton;

    @FXML
    private Button sizeButton;

    @FXML
    private TextField xTextField = new TextField();
    @FXML
    private TextField yTextField = new TextField();
    @FXML
    private TextField wTextField = new TextField();
    @FXML
    private TextField hTextField = new TextField();
    @FXML
    private TextField rTextField = new TextField();
    @FXML
    private TextField gTextField = new TextField();
    @FXML
    private TextField bTextField = new TextField();
    @FXML
    private Text xText = new Text();
    @FXML
    private Text yText = new Text();
    @FXML
    private Text wText = new Text();
    @FXML
    private Text hText = new Text();
    @FXML
    private Text rText = new Text();
    @FXML
    private Text gText = new Text();
    @FXML
    private Text bText = new Text();

    @FXML
    private ColorPicker colorPicker = new ColorPicker();
    private boolean cursorModeActivated = false;
    private boolean deleteModeActivated = false;
    private boolean isControlPressed = false;
    private boolean moveModeActivated = false;

    private BoundingRectangleModel boundingRectangleModel;
    private Rectangle boundingRectangle;


    private ShapeView shapeView;
    private EditorState currentState;
    private MoveCommand moveCommand;
    private MoveState moveState;
    ///

    @FXML
    private void initialize() {

        colorPicker.setOnAction(event -> {
            List<Shape> selectedShapes = multiSelectController.getSelectedShapes();
            if (!selectedShapes.isEmpty()) {
                Color selectedColor = colorPicker.getValue();
                for (Shape shape : selectedShapes) {
                    shape.setFill(selectedColor);
                }
            }
        });
        shapeView = new ShapeView(centerAnchorPane);

        multiSelectController = new MultiSelectController(centerAnchorPane);
        PropertyController = new PropertyController(centerAnchorPane, xText, yText, wText, hText, rText, gText, bText, xTextField, yTextField, wTextField, hTextField, rTextField, gTextField, bTextField);
        buttons = Arrays.asList(cursorButton, circleButton, squareButton, triangleButton, deleteButton, moveButton, toBackButton, toFrontButton, straightButton, curveButton, sizeButton);
        boundingRectangleModel = new BoundingRectangleModel(centerAnchorPane);
        boundingRectangle = boundingRectangleModel.getBoundingRectangle();

        shapeController = new ShapeController(shapeView, shapeFactory, shapeCreatorController);




        centerAnchorPane.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.CONTROL) {
                ControlState.getInstance().setControlPressed(true);
            }
        });

        centerAnchorPane.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.CONTROL) {
                ControlState.getInstance().setControlPressed(false);
            }
        });


        centerAnchorPane.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            //System.out.println("Key pressed: " + event.getCode());

        });

        centerAnchorPane.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
           // System.out.println("Key released: " + event.getCode());

        });


        circleButton.setOnMouseClicked(event -> {
            CircleDrawingState circleState = new CircleDrawingState(centerAnchorPane, selectedShapes, moveController,
                    shapeController, shapeCreatorController, multiSelectController, PropertyController, buttons);
            setCurrentState(circleState);
            circleState.activate();
            resetButtonStyles();
            circleButton.setStyle("-fx-background-color: green;");
            deleteModeActivated = false;
            cursorModeActivated = false;
            multiSelectController.deselectShapes();
            multiSelectController.clearSelection();
            centerAnchorPane.requestFocus();
        });

        squareButton.setOnMouseClicked(event -> {
            System.out.println("HelloController : squareButton.setOnMouseClicked");

            SquareDrawingState squareState = new SquareDrawingState(centerAnchorPane, selectedShapes, moveController, shapeController, shapeCreatorController, multiSelectController, PropertyController, buttons);
            setCurrentState(squareState);
            squareState.activate();

            centerAnchorPane.requestFocus();

            resetButtonStyles();
            squareButton.setStyle("-fx-background-color: green;");

            multiSelectController.deselectShapes();
            multiSelectController.clearSelection();
        });

        triangleButton.setOnMouseClicked(event -> {

            TriangleDrawingState triangleState = new TriangleDrawingState(centerAnchorPane, selectedShapes, moveController, shapeController, shapeCreatorController, multiSelectController, PropertyController, buttons);
            setCurrentState(triangleState);
            triangleState.activate();


            resetButtonStyles();
            triangleButton.setStyle("-fx-background-color: green;");

            multiSelectController.deselectShapes();
            multiSelectController.clearSelection();
            centerAnchorPane.requestFocus();
        });

        straightButton.setOnMouseClicked(event -> {

            StraightDrawingState straightState = new StraightDrawingState(centerAnchorPane, selectedShapes, moveController, shapeController, shapeCreatorController, multiSelectController, PropertyController, buttons);
            setCurrentState(straightState);
            straightState.activate();

            resetButtonStyles();
            straightButton.setStyle("-fx-background-color: green;");

            multiSelectController.deselectShapes();
            multiSelectController.clearSelection();
            centerAnchorPane.requestFocus();
        });

        curveButton.setOnMouseClicked(event -> {

            CurveDrawingState curveState = new CurveDrawingState(centerAnchorPane, selectedShapes, moveController, shapeController, shapeCreatorController, multiSelectController, PropertyController, buttons);
            setCurrentState(curveState);
            curveState.activate();


            resetButtonStyles();
            curveButton.setStyle("-fx-background-color: green;");

            multiSelectController.deselectShapes();
            multiSelectController.clearSelection();
            centerAnchorPane.requestFocus();
        });


        moveButton.setOnAction(event -> {

            shapeCreatorController.setDrawingMode(false);
            resetButtonStyles();
            moveButton.setStyle("-fx-background-color: green;");

            moveController = new MoveController(boundingRectangleModel, multiSelectController, this, centerAnchorPane);


            MoveState moveState = new MoveState(centerAnchorPane, selectedShapes,  moveController, shapeController, shapeCreatorController, multiSelectController, PropertyController, buttons);
           setCurrentState(moveState);

           moveState.activate();
        });

        sizeButton.setOnAction(event ->{
          //  System.out.println("Resize Mode");

            if (currentState != null) {
                currentState.deactivate();
            }

            resetButtonStyles();
            sizeButton.setStyle("-fx-background-color: green;");


            sizeController = new SizeController(multiSelectController);
            ResizeState resizeState = new ResizeState(centerAnchorPane, selectedShapes,
                    moveController, shapeController,
                    shapeCreatorController, multiSelectController,
                    PropertyController, buttons,
                    sizeController);
            setCurrentState(resizeState);
            resizeState.activate();

        });



        centerAnchorPane.setOnMousePressed(event -> {
            if (currentState != null) {
                currentState.handleMouseClick(event);
            }

            updateSelectedShapes();

        });
        centerAnchorPane.setOnMouseDragged(event -> {
            if (currentState != null) {
                currentState.handleMouseDrag(event);
            }

        });
        centerAnchorPane.setOnMouseReleased(event -> {
            //shapeController.handleCanvasRelease(event);
            if (currentState != null) {
                currentState.handleMouseRelease(event);
            }


        });


        centerAnchorPane.setFocusTraversable(true);

        deleteButton.setOnAction(event -> {
            if (currentState != null) {
                currentState.deactivate();
            }

            DeleteState deleteState = new DeleteState(centerAnchorPane, selectedShapes,
                    moveController, shapeController,
                    shapeCreatorController,
                    multiSelectController,
                    PropertyController, buttons);
            setCurrentState(deleteState);
            deleteState.activate();

            resetButtonStyles();
            deleteButton.setStyle("-fx-background-color: green;");
            System.out.println("Delete mode activated");


        });

        cursorButton.setOnAction(event -> {
            if (currentState != null) {
                currentState.deactivate();
            }

            SelectState selectState = new SelectState(centerAnchorPane, selectedShapes, moveController, shapeController,
                    shapeCreatorController, multiSelectController, PropertyController, buttons);
            setCurrentState(selectState);
            selectState.activate();


            resetButtonStyles();
            cursorButton.setStyle("-fx-background-color: green;");


        });

        toBackButton.setOnMouseClicked(event -> {

            if (currentState != null) {
                List<Shape> selectedShapes = multiSelectController.getSelectedShapes();
                if (selectedShapes !=null) {
                    ToBackState toBackState = new ToBackState(centerAnchorPane, selectedShapes,
                            moveController, shapeController,
                            shapeCreatorController,
                            multiSelectController,
                            PropertyController, buttons);
                    toBackState.activate(selectedShapes);
                }
            }
        });

        toFrontButton.setOnMouseClicked(event -> {

            if (currentState != null) {
                List<Shape> selectedShapes = multiSelectController.getSelectedShapes();
                if (selectedShapes !=null) {
                    ToFrontState toFrontState = new ToFrontState(centerAnchorPane, selectedShapes,
                            moveController, shapeController,
                            shapeCreatorController,
                            multiSelectController,
                            PropertyController, buttons);
                    toFrontState.activate(selectedShapes);
                }
            }

        });

        xTextField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                XYState xyState = new XYState(centerAnchorPane, selectedShapes,
                        moveController, shapeController,
                        shapeCreatorController,
                        multiSelectController,
                        PropertyController, buttons);
                xyState.activate(multiSelectController);

            }
        });
        yTextField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                XYState xyState = new XYState(centerAnchorPane, selectedShapes,
                        moveController, shapeController,
                        shapeCreatorController,
                        multiSelectController,
                        PropertyController, buttons);
                xyState.activate(multiSelectController);

            }
        });
        wTextField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                WHState whState = new WHState(centerAnchorPane, selectedShapes,
                        moveController, shapeController,
                        shapeCreatorController,
                        multiSelectController,
                        PropertyController, buttons);
                whState.activate(multiSelectController);
            }
        });
        hTextField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                WHState whState = new WHState(centerAnchorPane, selectedShapes,
                        moveController, shapeController,
                        shapeCreatorController,
                        multiSelectController,
                        PropertyController, buttons);
                whState.activate(multiSelectController);
            }
        });
        rTextField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                RGBState rgbState = new RGBState(centerAnchorPane, selectedShapes,
                        moveController, shapeController,
                        shapeCreatorController,
                        multiSelectController,
                        PropertyController, buttons);
                rgbState.activate(multiSelectController);
            }
        });
        gTextField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                RGBState rgbState = new RGBState(centerAnchorPane, selectedShapes,
                        moveController, shapeController,
                        shapeCreatorController,
                        multiSelectController,
                        PropertyController, buttons);
                rgbState.activate(multiSelectController);
            }
        });
        bTextField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                RGBState rgbState = new RGBState(centerAnchorPane, selectedShapes,
                        moveController, shapeController,
                        shapeCreatorController,
                        multiSelectController,
                        PropertyController, buttons);
                rgbState.activate(multiSelectController);
            }
        });




        centerAnchorPane.setOnKeyPressed(event -> {
           if (event.getCode() == KeyCode.DELETE) {

                if (currentState != null) {
                    currentState.deactivate(); // 현재 상태 비활성화
                }

                DeleteState deleteState = new DeleteState(centerAnchorPane, selectedShapes,
                        moveController, shapeController,
                        shapeCreatorController,
                        multiSelectController,
                        PropertyController, buttons);
                setCurrentState(deleteState); // 새 상태 설정
                deleteState.activate(); // 삭제 상태 활성화
            }
        });

        centerAnchorPane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {

            Shape clickedShape = null;
            for (Node node : centerAnchorPane.getChildren()) {
                if (node.contains(event.getX(), event.getY()) && node instanceof Shape && !(node == multiSelectController.selectionRectangle || node == boundingRectangle)) {
                    clickedShape = (Shape) node;
                    break;
                }
            }

            if (!moveModeActivated && clickedShape == null && !isControlPressed) {
                multiSelectController.deselectShapes();
                multiSelectController.clearSelection();
            }


            if (currentState != null) {
                currentState.handleMouseClick(event);
            }
            if (currentState instanceof DeleteState && clickedShape != null) {
                multiSelectController.selectShape(clickedShape);
                currentState.handleMouseClick(event);

            } else if (!moveModeActivated && clickedShape == null && !isControlPressed) {
                multiSelectController.deselectShapes();
                multiSelectController.clearSelection();
            }

        });

    }

    public void resetButtonStyles() {
        for (Button btn : buttons) {
            btn.setStyle("-fx-background-color: #000000;");
        }
    }


    private void setCurrentState(EditorState newState) {

        if (currentState != null) {
            currentState.deactivate(); // 현재 상태 비활성화
        }
        currentState = newState;

        if (currentState != null) {
            currentState.activate(); // 새 상태 활성화
        }

        System.out.println("Current State Set: " + newState);


    }

    public void updateSelectedShapes() {
        selectedShapes.clear();
        selectedShapes.addAll(multiSelectController.getSelectedShapes());
    }


}