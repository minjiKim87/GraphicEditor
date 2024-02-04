package com.example.state;

public class ControlState {
    private static ControlState instance;
    private boolean isControlPressed = false;

    private ControlState() {}

    public static ControlState getInstance() {
        if (instance == null) {
            instance = new ControlState();
        }
        return instance;
    }

    public boolean isControlPressed() {
        return isControlPressed;
    }

    public void setControlPressed(boolean newControlPressed) {
        isControlPressed = newControlPressed;
    }
}
