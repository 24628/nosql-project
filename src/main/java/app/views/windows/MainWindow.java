package app.views.windows;

import app.model.ServiceDeskEmployee;
import app.views.BaseForm;
import app.views.partial.TicketListView;
import app.views.partial.UserListView;
import app.views.partial.DashboardView;
import javafx.scene.Scene;

public class MainWindow extends BaseForm {

    public MainWindow() {
        // --BUTTON EVENTS-- //
        ticketButton.setOnAction(actionEvent -> layout.getChildren().set(1, new TicketListView(this)));
        userButton.setOnAction(actionEvent -> layout.getChildren().set(1, new UserListView(this)));
        dashboardButton.setOnAction(actionEvent -> layout.getChildren().set(1, new DashboardView()));

        // Add the menu and the view. Default view will be the student list view
        layout.getChildren().addAll(new DashboardView());

        // Create the main scene.
        // Scene mainScene = new StyledScene(layout);
        Scene mainScene = new Scene(layout);

        // Let's go!
        stage.setTitle("NoDesk");
        stage.setWidth(1200);
        stage.setHeight(600);
        stage.setScene(mainScene);
    }

    public void setTableView(String option){
        if (option.equalsIgnoreCase("Ticket"))
            layout.getChildren().set(1, new TicketListView(this));
        if (option.equalsIgnoreCase("User"))
            layout.getChildren().set(1, new UserListView(this));
    }

    public boolean getServicedeskAccess(){
        if (this.getLoggedInUser() instanceof ServiceDeskEmployee)
            return true;
        else
            return false;
    }
}
