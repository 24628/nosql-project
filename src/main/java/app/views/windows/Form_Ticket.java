package app.views.windows;

import app.ICallBack;
import app.database.Database;
import app.helpers.helperMethods;
import app.model.Ticket;
import app.views.BaseForm;
import app.views.partial.TicketListView;
import app.views.partial.UserListView;
import com.mongodb.client.model.Filters;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Form_Ticket extends BaseForm {

    // db, helpers and main window
    private Database db;
    private helperMethods helper;

    // all form items
    private DatePicker reported;
    private TextField incident;
    private ComboBox type;
    private ComboBox user;
    private ComboBox priority;
    private DatePicker deadline;
    private TextField description;

    // cmb values
    private String[] comboBoxTypes = {"Hardware", "Software", "Service"};
    private String[] comboBoxUserNames = {"Bram", "Koen", "Noor"};
    private String[] comboBoxPriorityNames = {"LOW", "MEDIUM", "HIGH"};



    // --Constructor
    public Form_Ticket(Ticket ticket) {
        // db conn
        //db = new Database("ProjectNoSQL");
        db = new Database("noSql");
        helper = new helperMethods();

        // --CRUD FORM-- //
        this.addUIControls(this.form, ticket);
        layout.getChildren().addAll(this.form);

        // Create the main scene.
        Scene form_Ticket = new Scene(layout);

        // Let's go!
        stage.setTitle("Form Ticket");
        stage.setScene(form_Ticket);

        // --BUTTON EVENTS-- //
        ticketButton.setOnAction(actionEvent -> openMainAndClose(actionEvent, "Ticket"));
        userButton.setOnAction(actionEvent -> openMainAndClose(actionEvent, "User"));
        dashboardButton.setOnAction(actionEvent -> openMainAndClose(actionEvent, "Dashboard"));
    }




    // --Controls of the form
    protected void addUIControls(GridPane gridPane, Ticket ticket) {
        // Add Header
        Label headerLabel = new Label("Create Ticket");
        headerLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        Control[] formItems;
        if (ticket == null)
            formItems = this.createFormItems();
        else
            formItems = this.createFormItems(ticket);


        Button cancelButton = this.generateFormBtn("CANCEL", 1);
        Button submitButton = this.generateFormBtn("SUBMIT TICKET", 0);

        submitButton.setOnAction(actionEvent -> this.handleSubmitBtnClick(formItems, ticket, new ICallBack() {
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
        cancelButton.setOnAction(actionEvent -> openMainAndClose(actionEvent,"Ticket"));
    }




    // --create empty form
    private Control[] createFormItems(){
        Control[] formItems = {
                reported = this.generateDatePicker("Date/time reported: ",1),
                incident = this.generateTextField("Subject of incident:: ", 2),
                type = this.generateComboBox("Type of incident:", comboBoxTypes, 3),
                user = this.generateComboBox("Reported by user:", comboBoxUserNames, 4),
                priority = this.generateComboBox("Priority", comboBoxPriorityNames,5),
                deadline = this.generateDatePicker("Deadline/follow up: ", 6),
                description = this.generateTextField("Description: ", 7),
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




    // --Submit button event handle
    protected void handleSubmitBtnClick(Control[] formItems, Ticket ticket, ICallBack callBack){
        List<String> data = new ArrayList<String>();

        // foreach item in control items, add value to data list
        for (Control item : formItems) {
            if(item instanceof TextField){
                final TextField parsedTextField = (TextField) item;
                data.add(parsedTextField.getText());
            }
            if(item instanceof ComboBox){
                final ComboBox parsedComboBox = (ComboBox) item;
                data.add(parsedComboBox.getValue().toString());
            }
            if(item instanceof DatePicker){
                final DatePicker parsedDatePicker = (DatePicker) item;
                data.add(parsedDatePicker.getValue().toString());
            }
        }

        // generate BSON document
        String[] columnNames = {"Reported", "incident", "type", "user_id", "priority", "deadline", "description"};
        Document document = helper.generateDocument(data, columnNames);

        // if ticket null, insert new one, otherwise update
        try {
            if (ticket == null)
                db.insertOne(document, "Tickets");
            else {
                Bson filter = Filters.eq("incident", ticket.getIncident());
                db.replaceOne(filter, document, "Tickets");
            }
            callBack.onSucces();
        }catch (Exception e){
            callBack.onError(e.toString());
        }
    }




    // --Open main window and close this one
    private void openMainAndClose(ActionEvent actionEvent, String option){
        MainWindow mainWindow = new MainWindow();
        mainWindow.setTableView(option);
        mainWindow.getStage().show();

        // close this window
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
    }
}
