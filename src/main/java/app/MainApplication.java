package app;

import app.test.Connection;
import app.views.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Tab;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // rendering the text more crisply
        System.setProperty("prism.lcdtext", "false");

        // We should open a login window, but that is outside of scope for this lesson.
        // So we go straight to the main window of the application

        // open the main window
        MainWindow mw = new MainWindow();
        mw.getStage().show();
    }

    public static void main(String[] args) {
        //Connection connection = new Connection();
        launch();
    }
}