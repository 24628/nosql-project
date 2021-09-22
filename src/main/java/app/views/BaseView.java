package app.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BaseView {

    protected Stage stage;
    protected VBox layout;
    protected Button dashboardButton;
    protected Button userButton;
    protected Button ticketButton;
    public Stage getStage() {
        return stage;
    }

    public BaseView() {

        // create a new Stage (window)
        stage = new Stage();

        // set up the global layout, menu on the left, sub scene on the right
         layout = new VBox();


        // --MENU-- //
        VBox container = new VBox();
        HBox nav_bar = new HBox();

        container.setStyle("-fx-padding: 0;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 1;" +
                "-fx-border-radius: 1;" +
                "-fx-border-color: black;");

        // labels for title and description
        Label title = new Label("NoDesk");
        Label description = new Label("Licensed to: The Garden Group");
        title.setFont(Font.font("Verdana", 20));

        // buttons
        dashboardButton = new Button("Dashboard");
        userButton = new Button("User");
        ticketButton = new Button("Ticket");
        dashboardButton.setMinWidth(250);
        ticketButton.setMinWidth(250);
        userButton.setMinWidth(250);

        // add all children and set alignment to right
        nav_bar.getChildren().addAll(dashboardButton, ticketButton, userButton);
        container.setAlignment(Pos.BOTTOM_RIGHT);
        container.getChildren().addAll(title, description, nav_bar);

        layout.getChildren().addAll(container);
    }
}
