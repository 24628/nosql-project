package app.views;

import app.views.partial.TicketListView;
import app.views.partial.UserListView;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class MainWindow extends BaseView{

    public MainWindow() {

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

        // --BUTTON EVENTS-- //
        ticketButton.setOnAction(actionEvent -> layout.getChildren().set(1, new TicketListView()));
        userButton.setOnAction(actionEvent -> layout.getChildren().set(1, new UserListView()));
        dashboardButton.setOnAction(actionEvent -> layout.getChildren().set(1, dashboard));

        // Add the menu and the view. Default view will be the student list view
        layout.getChildren().addAll(dashboard);

        // Create the main scene.
        // Scene mainScene = new StyledScene(layout);
        Scene mainScene = new Scene(layout);

        // Let's go!
        stage.setTitle("NoDesk");
        stage.setScene(mainScene);
    }
}
