package app.views.windows;

import app.views.BaseView;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CRUD_User extends BaseView {

    public CRUD_User() {
        // Add the menu and the view. Default view will be the student list view
        layout.getChildren().addAll();

        // Create the main scene.
        // Scene mainScene = new StyledScene(layout);
        Scene crud_User = new Scene(layout);

        // Let's go!
        stage.setTitle("CRUD Users");
        stage.setScene(crud_User);
    }
}
