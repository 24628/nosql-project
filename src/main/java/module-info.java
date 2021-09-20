module com.example.projectnosqllol {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires mongo.java.driver;

    opens app to javafx.fxml;
    opens app.model;
    exports app;
    exports app.controller;
    opens app.controller to javafx.fxml;
}