package app.views;

import app.views.partial.TicketListView;
import app.views.partial.UserListView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainWindow {
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public MainWindow() {
        // create a new Stage (window)
        stage = new Stage();

        // set up the global layout, menu on the left, sub scene on the right
        VBox layout = new VBox();





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
        title.setFont(Font.font ("Verdana", 20));

        // buttons
        Button dashboardButton = new Button("Dashboard");
        Button userButton = new Button("User");
        Button ticketButton = new Button("Ticket");
        dashboardButton.setMinWidth(250);
        ticketButton.setMinWidth(250);
        userButton.setMinWidth(250);





        // --DASHBOARD-- //
        VBox dashboard = new VBox();
        dashboard.setPrefHeight(500);
        dashboard.setPrefWidth(750);
        dashboard.setPadding(new Insets(50, 0, 0, 0));

        // header of dashboard
        HBox dashboard_Header = new HBox();
        Button show_List = new Button("SHOW LIST");
        Label dashboard_Title = new Label("Current incidents");

        dashboard_Header.setSpacing(325);
        dashboard_Title.setFont(Font.font("Verdana", 30));
        show_List.setMinWidth(150);
        dashboard_Header.getChildren().addAll(dashboard_Title, show_List);

        // add to dashboard
        dashboard.getChildren().add(dashboard_Header);

        // add all children and set alignment to right
        nav_bar.getChildren().addAll(dashboardButton, ticketButton, userButton);
        container.setAlignment(Pos.BOTTOM_RIGHT);
        container.getChildren().addAll(title, description, nav_bar);




        // --BUTTON EVENTS-- //
        ticketButton.setOnAction(actionEvent -> layout.getChildren().set(1, new TicketListView()));
        userButton.setOnAction(actionEvent -> layout.getChildren().set(1, new UserListView()));
        dashboardButton.setOnAction(actionEvent -> layout.getChildren().set(1, dashboard));





        // Add the menu and the view. Default view will be the student list view
        layout.getChildren().addAll(container,dashboard);

        // Create the main scene.
        // Scene mainScene = new StyledScene(layout);
        Scene mainScene = new Scene(layout);

        // Let's go!
        stage.setTitle("NoDesk");
        stage.setScene(mainScene);
    }
}
