package app.views;

import app.views.partial.TicketListView;
import app.views.partial.UserListView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

        // setup the global layout, menu on the left, subscene on the right
        VBox layout = new VBox();

        // the menu
        VBox container = new VBox();
        HBox nav_bar = new HBox();

        // labels for title and description
        Label title = new Label("NoDesk");
        title.setFont(Font.font ("Verdana", 20));
        Label description = new Label("Licensed to: The Garden Group");

        // buttons
        Button dashboardButton = new Button("Dashboard");
        dashboardButton.setMinWidth(250);
        Button ticketButton = new Button("Ticket");
        ticketButton.setMinWidth(250);
        Button userButton = new Button("User");
        userButton.setMinWidth(250);

        // container for dashboard and listviews
        VBox dashboard = new VBox();
        dashboard.setPrefHeight(500);
        dashboard.setPrefWidth(750);
        dashboard.setPadding(new Insets(50, 0, 0, 0));

        // header of dashboard
        HBox dashboard_Header = new HBox();
        dashboard_Header.setSpacing(325);
        Label dashboard_Title = new Label("Current incidents");
        dashboard_Title.setFont(Font.font("Verdana", 30));
        Button show_List = new Button("SHOW LIST");
        show_List.setMinWidth(150);
        dashboard_Header.getChildren().addAll(dashboard_Title, show_List);

        // add to dashboard
        dashboard.getChildren().add(dashboard_Header);

        // add all children and set alignment to right
        nav_bar.getChildren().addAll(dashboardButton, ticketButton, userButton);
        container.setAlignment(Pos.BOTTOM_RIGHT);
        container.getChildren().addAll(title, description, nav_bar);

        // events for buttons, display dashboard or table view
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
        dashboardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                layout.getChildren().set(1, dashboard);
            }
        });

        // Add the menu and the view. Default view will be the student list view
        layout.getChildren().addAll(container,dashboard);

        // Create the main scene
        Scene mainScene = new StyledScene(layout);

        // Let's go!
        stage.setTitle("University Management");
        stage.setScene(mainScene);
    }
}
