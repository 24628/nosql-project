package app.views;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CRUD_Ticket {
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public CRUD_Ticket() {
        // create a new Stage (window)
        stage = new Stage();
        stage.setMinWidth(500);
        stage.setMinHeight(300);

        // set up the global layout, menu on the left, sub scene on the right
        VBox layout = new VBox();

        Label test = new Label("Test ticket");

        // Add the menu and the view. Default view will be the student list view
        layout.getChildren().addAll(test);

        // Create the main scene.
        // Scene mainScene = new StyledScene(layout);
        Scene mainScene = new Scene(layout);

        // Let's go!
        stage.setTitle("CRUD Ticket");
        stage.setScene(mainScene);
    }
}
