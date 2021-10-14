package app.views.windows;

import app.ICallBack;
import app.database.Database;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Form_User extends BaseForm {

    // database and helpers
    private Database db;
    private documentHandling helper;

    private TextField firstName;
    private TextField lastName;
    private ComboBox userType;
    private TextField email;
    private TextField phoneNumber;
    private ComboBox location;


    // comboBox values
    private String[] comboBoxUserTypes = {"Employee", "Service_desk"};
    private String[] comboBoxLocations = {"Amsterdam", "Haarlem", "Knuppeldam", "Headquarters (HQ)"};

    public Form_User(User user) {
        // set database and helper
        db = new Database("noSql");
        helper = new documentHandling();

        // --CRUD FORM-- //
        this.addUIControls(this.form, user);
        layout.getChildren().addAll(this.form);

        // Create the main scene.
        Scene form_User = new Scene(layout);
        form_User.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/style.css")).toString());

        // Let's go!
        stage.setTitle("Users Form");
        stage.setScene(form_User);

        // --NAV BUTTON EVENTS-- //
        ticketButton.setOnAction(actionEvent -> openMainAndClose(actionEvent, "Ticket"));
        userButton.setOnAction(actionEvent -> openMainAndClose(actionEvent, "User"));
        dashboardButton.setOnAction(actionEvent -> openMainAndClose(actionEvent, "Dashboard"));
        logoutButton.setOnAction(actionEvent -> logoutFromSession());

    }

    protected void addUIControls(GridPane gridPane, User user) {
        // Add Header
        Label headerLabel = new Label("Create User");
        headerLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        Control[] formItems;
        headerLabel.setText("Edit User");
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
                firstName = this.generateTextField("First Name: ",1),
                lastName = this.generateTextField("Last Name: ", 2),
                userType = this.generateComboBox("User Type: ", comboBoxUserTypes, 3 ),
                email = this.generateTextField("E-mail: ", 4),
                phoneNumber = this.generateTextField("Phone Number: ", 5),
                location = this.generateComboBox("Location: ", comboBoxLocations, 6 )
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

        location = this.generateComboBox("Location: ", comboBoxLocations, 6 );
        location.getSelectionModel().select(helper.getCMBIndex((ComboBox<String>) location, user.getLocation()));


        Control[] formItems = { firstName, lastName, userType, email, phoneNumber, location};
        return formItems;
    }

    // Submit button event handle
    private void handleSubmitBtnClick(Control[] formItems, User user, ICallBack callBack) {
        List<String> data = new ArrayList<String>();

        // foreach item in control items, add value to data list
        for (Control item : formItems) {
            if(item instanceof TextField){
                final TextField parsedTextField = (TextField) item;
                if(Objects.equals(parsedTextField.getText(), "")) return;
                data.add(parsedTextField.getText());
            }
            if(item instanceof ComboBox) {
                final ComboBox parsedComboBox = (ComboBox) item;
                data.add(parsedComboBox.getValue().toString());
            }
        }

        // generate BSON document
        String[] columnNames = {"firstName" , "lastName" , "type", "email" , "phonenumber", "location"};
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
