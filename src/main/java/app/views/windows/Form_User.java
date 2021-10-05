package app.views.windows;

import app.ICallBack;
import app.model.Ticket;
import app.model.User;
import app.views.BaseForm;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.ZoneId;
import java.util.Date;

public class Form_User extends BaseForm {

    private TextField firstName;
    private TextField lastName;
    private TextField email;
    private TextField phoneNumber;
    private DatePicker created_at;
    private DatePicker updated_at;

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

    protected void addUIControls(GridPane gridPane, User user) {
        // Add Header
        Label headerLabel = new Label("Create User");
        headerLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        Control[] formItems;
        if (user == null)
            formItems = this.createFormItems();
        else
            formItems = this.createFormItems(user);


        Button cancelButton = this.generateFormBtn("CANCEL", 1);
        Button submitButton = this.generateFormBtn("SUBMIT TICKET", 0);

        submitButton.setOnAction(actionEvent -> this.handleSubmitBtnClick(formItems, user, new ICallBack() {
            @Override
            public void onSucces() {
                System.out.println("data submit succesfull!");
                openMainAndClose(actionEvent, "Ticket"); // open / refresh tableview
            }

            @Override
            public void onError(String err) {
                System.out.println("data submit not succesfull: " + err);
            }
        }));
        cancelButton.setOnAction(actionEvent -> openMainAndClose(actionEvent,"User"));
    }

    // --create empty form
    private Control[] createFormItems(){
        Control[] formItems = {
                firstName = this.generateTextField("First Name",1),
                lastName = this.generateTextField("Last Name", 2),
                email = this.generateTextField("E-mail", 3),
                phoneNumber = this.generateTextField("Phone Number", 4),
                created_at = this.generateDatePicker("Created At", 5),
                updated_at = this.generateDatePicker("Updated At", 6)
        };
        return formItems;
    }




    // --create form with ticket items filled in
    private Control[] createFormItems(Ticket ticket){
        reported = this.generateDatePicker("Date/time reported: ", 1);
        reported.setValue(ticket.getReported().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        incident = this.generateTextField("Subject of incident: ", 2);
        incident.setText(ticket.getIncident());

        type = this.generateComboBox("Type of incident:", comboBoxTypes, 3);
        type.getSelectionModel().select(helper.getCMBIndex((ComboBox<String>) type, ticket.getType()));

        user = this.generateComboBox("Reported by user:", comboBoxUserNames, 4);
        user.getSelectionModel().select(helper.getCMBIndex((ComboBox<String>) user, ticket.getUser_id()));

        priority = this.generateComboBox("Priority", comboBoxPriorityNames, 5);
        priority.getSelectionModel().select(helper.getCMBIndex((ComboBox<String>) priority, ticket.getPriority()));

        deadline = this.generateDatePicker("Deadline/follow up: ", 6);
        deadline.setValue(ticket.getDeadline().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        description = this.generateTextField("Description: ", 7);
        description.setText(ticket.getDescription());

        Control[] formItems = { reported, incident, type, user, priority, deadline, description};
        return formItems;
    }
}
