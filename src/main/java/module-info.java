module com.example.projectnosqllol {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires mongo.java.driver;

    opens src to javafx.fxml;
    exports src;
    exports src.controller;
    opens src.controller to javafx.fxml;
}