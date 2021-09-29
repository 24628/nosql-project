package app.views.windows;

import app.model.User;
import app.views.BaseForm;
import javafx.scene.Scene;

public class Form_User extends BaseForm {

    public Form_User(User user) {
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
