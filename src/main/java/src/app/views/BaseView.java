package src.app.views;

import javafx.application.Application;
import javafx.stage.Stage;

public class BaseView extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage window) throws Exception { // IMO window is a better name than stage
        window.show();
    }
}
