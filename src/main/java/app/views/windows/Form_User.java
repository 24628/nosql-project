package app.views.windows;

import app.ICallBack;
import app.database.Database;
import app.helpers.dateParser;
import app.helpers.documentHandling;
import app.model.User;
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
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Form_User extends BaseForm {

    // database and helpers
    private Database db;
    private documentHandling helper;
    private dateParser dateHelper;

    private TextField firstName;
    private TextField lastName;
    private ComboBox userType;
    private TextField email;
    private TextField phoneNumber;
    private DatePicker created_at;
    private DatePicker updated_at;

    // comboBox values
    private String[] comboBoxUserTypes = {"Employee", "Service_desk"};

    public Form_User(User user) {
        // set database and helper
        db = new Database("noSql");
        helper = new documentHandling();
        dateHelper = new dateParser();

        // --CRUD FORM-- //
        this.addUIControls(this.form, user);
        layout.getChildren().addAll(this.form);

        // Create the main scene.
        // Scene mainScene = new StyledScene(layout);
        Scene form_User = new Scene(layout);

        // Let's go!
        stage.setTitle("Users Form");
        stage.setScene(form_User);

        // --NAV BUTTON EVENTS-- //
        ticketButton.setOnAction(actionEvent -> openMainAndClose(actionEvent, "Ticket"));
        userButton.setOnAction(actionEvent -> openMainAndClose(actionEvent, "User"));
        dashboardButton.setOnAction(actionEvent -> openMainAndClose(actionEvent, "Dashboard"));
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

        // generate form buttons
        Button cancelButton = this.generateFormBtn("CANCEL", 1);
        Button submitButton = this.generateFormBtn("SUBMIT USER", 0);

        // set action to submit button
        submitButton.setOnAction(actionEvent -> this.handleSubmitBtnClick(formItems, user, new ICallBack() {
            @Override
            public void onSucces() {
                System.out.println("data submit succesfull!");
                openMainAndClose(actionEvent, "User"); // open / refresh tableview
            }

            @Override
            public void onError(String err) {
                System.out.println("data submit not succesfull: " + err);
            }
        }));
        // set action to cancel button
        cancelButton.setOnAction(actionEvent -> openMainAndClose(actionEvent,"User"));
    }

    // create empty form
    private Control[] createFormItems(){
        Control[] formItems = {
                firstName = this.generateTextField("First Name",1),
                lastName = this.generateTextField("Last Name", 2),
                userType = this.generateComboBox("User Type", comboBoxUserTypes, 3 ),
                email = this.generateTextField("E-mail", 4),
                phoneNumber = this.generateTextField("Phone Number", 5),
                created_at = this.generateDatePicker("Created At", 6),
                updated_at = this.generateDatePicker("Updated At", 7)
        };
        return formItems;
    }

    // create form with user items filled in
    private Control[] createFormItems(User user){
        firstName = this.generateTextField("First Name: ", 1);
        firstName.setText(user.getFirstName());

        lastName = this.generateTextField("Last Name: ", 2);
        lastName.setText(user.getLastName());

        userType = this.generateComboBox("User Type", comboBoxUserTypes, 3 );
        userType.getSelectionModel().select(helper.getCMBIndex((ComboBox<String>) userType, user.getUserType()));

        email = this.generateTextField("Email: ", 4);
        email.setText(user.getEmail());

        phoneNumber = this.generateTextField("Phone Number: ", 5);
        phoneNumber.setText(user.getPhoneNumber());

        created_at = this.generateDatePicker("Created at: ", 6);
        created_at.setValue(user.getCreated_at().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        updated_at = this.generateDatePicker("Updated At: ", 7);
        updated_at.setValue(user.getUpdated_at().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());



        Control[] formItems = { firstName, lastName, userType, email, phoneNumber, created_at, updated_at};
        return formItems;
    }

    // Submit button event handle
    private void handleSubmitBtnClick(Control[] formItems, User user, ICallBack callBack) {
        List<String> data = new ArrayList<String>();

        // foreach item in control items, add value to data list
        for (Control item : formItems) {
            if(item instanceof TextField){
                final TextField parsedTextField = (TextField) item;
                data.add(parsedTextField.getText());
            }
            if(item instanceof ComboBox) {
                final ComboBox parsedComboBox = (ComboBox) item;
                data.add(parsedComboBox.getValue().toString());
            }
            if(item instanceof DatePicker){
                final DatePicker parsedDatePicker = (DatePicker) item;
                data.add(parsedDatePicker.getValue().toString());
            }
        }

        // generate BSON document
        String[] columnNames = {"firstName" , "lastName" , "type", "email" , "phonenumber" , "created_at" , "updated_at"};
        Document document = helper.generateDocument(data, columnNames);

        // if user null, insert new one, otherwise update
        try {
            if (user == null)
                db.insertOne(document, "users");
            else {
                Bson filter = Filters.eq("email", user.getEmail());
                db.replaceOne(filter, document, "users");
            }
            callBack.onSucces();
        }catch (Exception e){
            callBack.onError(e.toString());
        }
    }
}
