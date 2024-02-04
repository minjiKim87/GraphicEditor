module com.example.miridi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.miridi to javafx.fxml;
    exports com.example.miridi;
    exports com.example.controller;
    opens com.example.controller to javafx.fxml;
}