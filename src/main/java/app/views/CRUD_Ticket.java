package app.views;

import app.views.partial.TicketListView;
import app.views.partial.UserListView;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CRUD_Ticket extends BaseView{

    public CRUD_Ticket() {

        // Add the menu and the view. Default view will be the student list view
        layout.getChildren().addAll();

        // Create the main scene.
        // Scene mainScene = new StyledScene(layout);
        Scene crud_Ticket = new Scene(layout);

        // Let's go!
        stage.setTitle("CRUD Ticket");
        stage.setScene(crud_Ticket);
    }
}
