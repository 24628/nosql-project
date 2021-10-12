package app.views.windows;

import app.ICallBack;
import app.database.Database;
import app.helpers.controls.DateTimePicker;
import app.helpers.dateParser;
import app.helpers.documentHandling;
import app.model.Ticket;
import app.views.BaseForm;
import com.mongodb.client.model.Filters;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class Form_Ticket extends BaseForm {

    // db, helpers and main window
    private final Database db;
    private final documentHandling helper;

    // all form items
    private DateTimePicker reported;
    private TextField incident;
    private ComboBox type;
    private ComboBox user_id;
    private ComboBox employee_id;
    private ComboBox priority;
    private DateTimePicker deadline;
    private TextField description;
    private ComboBox status;

    // cmb values
    private final String[] comboBoxTypes;
    private String[] comboBoxUserNames;
    private final String[] comboBoxPriorityNames;
    private final String[] comboBoxStatusValues;
    // --Constructor
    public Form_Ticket(Ticket ticket) {
        db = new Database("noSql");
        helper = new documentHandling();

        // cmb values
        comboBoxTypes = new String[]{"Hardware", "Software", "Service"};
        comboBoxUserNames = getAllUserNames();
        comboBoxPriorityNames = new String[]{"High",  "Normal", "Low"};
        comboBoxStatusValues = new String[]{"Closed", "Normal", "Escalated"};

        // add controls
        this.addUIControls(this.form, ticket);
        layout.getChildren().addAll(this.form);

        // make scene and add to stage
        Scene form_Ticket = new Scene(layout);
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
        else {
            headerLabel.setText("Edit Ticket");
            formItems = this.createFormItems(ticket);
        }


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
                reported = this.generateDateTimePicker("Date/time reported: ",1),
                incident = this.generateTextField("Subject of incident:: ", 2),
                type = this.generateComboBox("Type of incident:", comboBoxTypes, 3),
                user_id = this.generateComboBox("Reported by user:", comboBoxUserNames, 4),
                employee_id = this.generateComboBox("Handled by employee: ", comboBoxUserNames, 5), // todo maybe default user = current user
                priority = this.generateComboBox("Priority", comboBoxPriorityNames,6),
                deadline = this.generateDateTimePicker("Deadline/follow up: ", 7),
                description = this.generateTextField("Description: ", 8),
                status = this.generateComboBox("Status: ", comboBoxStatusValues, 9)
        };
        return formItems;
    }



    // --create form with ticket items filled in
    private Control[] createFormItems(Ticket ticket){
        reported = this.generateDateTimePicker("Date/time reported: ", 1);
        reported.setDateTimeValue(ticket.getReported());

        incident = this.generateTextField("Subject of incident: ", 2);
        incident.setText(ticket.getIncident());

        type = this.generateComboBox("Type of incident:", comboBoxTypes, 3);
        type.getSelectionModel().select(helper.getCMBIndex((ComboBox<String>) type, ticket.getType()));

        user_id = this.generateComboBox("Reported by user:", comboBoxUserNames, 4);
        user_id.getSelectionModel().select(helper.getCMBIndex((ComboBox<String>) user_id, ticket.getUser_id()));

        employee_id = this.generateComboBox("handled by employee: ", comboBoxUserNames, 5);
        employee_id.getSelectionModel().select(helper.getCMBIndex((ComboBox<String>) employee_id, ticket.getEmployee_id()));

        priority = this.generateComboBox("Priority", comboBoxPriorityNames, 6);
        priority.getSelectionModel().select(helper.getCMBIndex((ComboBox<String>) priority, ticket.getPriority()));

        deadline = this.generateDateTimePicker("Deadline/follow up: ", 7);
        deadline.setDateTimeValue(ticket.getDeadline());

        description = this.generateTextField("Description: ", 8);
        description.setText(ticket.getDescription());

        status = this.generateComboBox("Status: ", comboBoxStatusValues, 9);
        status.getSelectionModel().select(helper.getCMBIndex((ComboBox<String>) status, ticket.getStatus()));

        Control[] formItems = { reported, incident, type, user_id, employee_id, priority, deadline, description, status};
        return formItems;
    }



    // --Submit button event handle
    protected void handleSubmitBtnClick(Control[] formItems, Ticket ticket, ICallBack callBack){
        List<String> data = new ArrayList<String>();
        dateParser parser = new dateParser();

        // foreach item in control items, add value to data list
        int index = 0;
        for (Control item : formItems) {
            if(item instanceof TextField){
                final TextField parsedTextField = (TextField) item;
                data.add(parsedTextField.getText());
            }
            if(item instanceof ComboBox){
                final ComboBox parsedComboBox = (ComboBox) item;
                if (index == 3 || index == 4){
                    Document user = db.findOne(Filters.eq("firstName", parsedComboBox.getValue()), "users");
                    String userID = user.getObjectId("_id").toString();
                    data.add(userID);
                } else {
                    data.add(parsedComboBox.getValue().toString());
                }
            }
//            if(item instanceof DateTimePicker){
//                final DateTimePicker parsedDateTimePicker = (DateTimePicker) item;
//                data.add(parser.toString(parsedDateTimePicker.getDateTimeValue()));
//            }
            if(item instanceof DateTimePicker){
                final DateTimePicker parsedDateTimePicker = (DateTimePicker) item;
                if (parsedDateTimePicker.getDateTimeValue().isBefore(LocalDateTime.now()) &&
                        parsedDateTimePicker.getDateTimeValue().isBefore(ticket.getDeadline())){
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("Date time Error");
                    errorAlert.setContentText("Date time value not valid!");
                    errorAlert.showAndWait();
                    return;
                }
                else
                    data.add(parser.toString(parsedDateTimePicker.getDateTimeValue()));
            }
            index++;
        }

        String[] columnNames = {"Reported", "incident", "type", "user_id", "employee_id", "priority", "deadline", "description", "status"};
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




    private String[] getAllUserNames(){
        List<String> users = new ArrayList<>();
        for (Document doc : db.findAll("users")) {
            users.add(doc.getString("firstName"));
        }
        return users.toArray(String[]::new);
    }
}
