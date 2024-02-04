package com.example.command;

import javafx.scene.input.MouseEvent;

public interface Command {
    void execute(MouseEvent event);

    void execute();

}
