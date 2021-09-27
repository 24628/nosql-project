package app.controller;

import app.database.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {

    private Database db;

    public MainController(){
        // db conn
        db = new Database("ProjectNoSQL");
    }

//        @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
}