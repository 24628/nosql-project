package app.views.windows;

import app.helpers.Session;
import app.views.BaseForm;
import app.views.partial.TicketListView;
import app.views.partial.UserListView;
import app.views.partial.DashboardView;
import javafx.scene.Scene;

import java.util.Objects;

public class MainWindow extends BaseForm {

    public MainWindow() {
        // --BUTTON EVENTS-- //
        ticketButton.setOnAction(actionEvent -> layout.getChildren().set(1, new TicketListView(this)));
        userButton.setOnAction(actionEvent -> layout.getChildren().set(1, new UserListView(this)));
        dashboardButton.setOnAction(actionEvent -> layout.getChildren().set(1, new DashboardView(this)));
        logoutButton.setOnAction(actionEvent -> logoutFromSession());

        // Add the menu and the view. Default view will be the student list view
        layout.getChildren().addAll(new DashboardView(this));

        // Create the main scene.
        // Scene mainScene = new StyledScene(layout);
        Scene mainScene = new Scene(layout);
        mainScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/style.css")).toString());

        // Let's go!
        stage.setTitle("NoDesk");
        stage.setWidth(1200);
        stage.setHeight(600);
        stage.setScene(mainScene);

        System.out.println("is SD emp? " + Session.isServiceDeskEmployee());
        System.out.println(Session.getUser());
    }

    public void setTableView(String option){
        if (option.equalsIgnoreCase("Ticket"))
            layout.getChildren().set(1, new TicketListView(this));
        if (option.equalsIgnoreCase("User"))
            layout.getChildren().set(1, new UserListView(this));
    }
}
