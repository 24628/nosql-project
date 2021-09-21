package app.views;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CRUD_User {
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public CRUD_User() {
        // create a new Stage (window)
        stage = new Stage();
        stage.setMinWidth(500);
        stage.setMinHeight(300);

        // set up the global layout, menu on the left, sub scene on the right
        VBox layout = new VBox();

        Label test = new Label("Test User");

        // Add the menu and the view. Default view will be the student list view
        layout.getChildren().addAll(test);

        // Create the main scene.
        // Scene mainScene = new StyledScene(layout);
        Scene mainScene = new Scene(layout);

        // Let's go!
        stage.setTitle("CRUD user");
        stage.setScene(mainScene);
    }
}
