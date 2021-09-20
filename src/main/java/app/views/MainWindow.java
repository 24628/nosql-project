package app.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainWindow {
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public MainWindow() {
        // create a new Stage (window)
        stage = new Stage();
        // set the window to max size
        stage.setMaximized(true);

        // setup the global layout, menu on the left, subscene on the right
        HBox layout = new HBox();

        // the menu
        VBox menu = new VBox();
        menu.setPadding(new Insets(80,20,20,20));
        menu.setSpacing(10);
        menu.getStyleClass().add("menu");

        Button ticketButton = new Button("Ticket");
        ticketButton.setMinWidth(150);

        Button userButton = new Button("User");
        userButton.setMinWidth(150);

        menu.getChildren().addAll(ticketButton, userButton);

        // container for dashboard and listviews
        VBox container = new VBox();

        ticketButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                layout.getChildren().set(1, new TicketListView());
            }
        });

        userButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                layout.getChildren().set(1, new UserListView());
            }
        });


        // Add the menu and the view. Default view will be the student list view
        layout.getChildren().addAll(menu, new TicketListView());

        // Create the main scene
        Scene mainScene = new StyledScene(layout);

        // Let's go!
        stage.setTitle("University Management");
        stage.setScene(mainScene);
    }
}
