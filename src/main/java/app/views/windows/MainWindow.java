package app.views.windows;

import app.views.BaseForm;
import app.views.partial.TicketListView;
import app.views.partial.UserListView;
import app.views.partial.DashboardView;
import javafx.scene.Scene;

public class MainWindow extends BaseForm {

    public MainWindow() {
        // --BUTTON EVENTS-- //
        ticketButton.setOnAction(actionEvent -> layout.getChildren().set(1, new TicketListView()));
        userButton.setOnAction(actionEvent -> layout.getChildren().set(1, new UserListView()));
        dashboardButton.setOnAction(actionEvent -> layout.getChildren().set(1, new DashboardView()));

        // Add the menu and the view. Default view will be the student list view
        layout.getChildren().addAll(new DashboardView());

        // Create the main scene.
        // Scene mainScene = new StyledScene(layout);
        Scene mainScene = new Scene(layout);

        // Let's go!
        stage.setTitle("NoDesk");
        stage.setScene(mainScene);
    }
}
