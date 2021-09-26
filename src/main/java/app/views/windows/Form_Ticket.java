package app.views.windows;

import app.database.Database;
import app.model.Ticket;
import app.views.BaseForm;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.bson.Document;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Form_Ticket extends BaseForm {

    private Database db;

    public Form_Ticket() {
        // db conn
        //db = new Database("ProjectNoSQL");
        db = new Database("noSql");

        // --CRUD FORM-- //
        this.addUIControls(this.form);

        // Add the menu and the view. Default view will be the student list view
        layout.getChildren().addAll(this.form);

        // Create the main scene.
        Scene form_Ticket = new Scene(layout);

        // Let's go!
        stage.setTitle("Form Ticket");
        stage.setScene(form_Ticket);
    }

    protected void addUIControls(GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("Create Ticket");
        headerLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        String[] comboBoxTypes = {"Choice1", "choice2", "choice3"};
        String[] comboBoxUserNames = {"Bram", "Koen", "Noor"};
        String[] comboBoxPriorityNames = {"LOW", "MEDIUM", "HIGH"};

        Control[] formItems = {
                this.generateDatePicker("Date/time reported: ",1),
                this.generateTextField("Subject of incident:: ", 2),
                this.generateComboBox("Type of incident:", comboBoxTypes, 3),
                this.generateComboBox("Reported by user:", comboBoxUserNames, 4),
                this.generateComboBox("Priority", comboBoxPriorityNames,5),
                this.generateDatePicker("Deadline/follow up: ", 6),
                this.generateTextField("Description: ", 7),
        };

        Button cancelButton = this.generateFormBtn("CANCEL", 1);
        Button submitButton = this.generateFormBtn("SUBMIT TICKET", 0);

        submitButton.setOnAction(actionEvent -> this.handleSubmitBtnClick(formItems));
    }

    protected void handleSubmitBtnClick(Control[] formItems){
        System.out.println("Handle Submit!");
        List<String> data = new ArrayList<String>();

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
        db.insertOne(generateDocument(data), "Tickets");
    }

    private Document generateDocument(List<String> data){
        // new document and all column names
        Document document = new Document();
        String[] columnNames = {"Reported", "incident", "type", "user_id", "priority", "deadline", "description"};

        // create document
        for (int i = 0; i < data.size(); i++) {
            document.append(columnNames[i], data.get(i));
        }

        // return document
        return document;
    }













    // work in progress, when a ticket is selected and the button "Edit" is pressed,
    // the form_ticket should open with all the fields already filled in....
    protected void fillTicketData(Control[] formItems, Ticket t) throws ParseException {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] ticketData = t.getTicketArray();
        int index = 0;

        for (Control item : formItems) {
            if(item instanceof TextField){
                ((TextField) item).setText(ticketData[index]);
            }

            if(item instanceof ComboBox){
                ((ComboBox<?>) item).getSelectionModel().select(getCMBIndex((ComboBox<String>) item, ticketData[index]));
            }

            if(item instanceof DatePicker){
                ((DatePicker) item).setValue(LocalDate.parse(ticketData[index]));
            }
            index++;
        }
    }

    private int getCMBIndex(ComboBox<String> box, String value){
        int index = 0;
        for (String s:box.getItems()) {
            if (s == value)
                break;
            index++;
        }
        return index;
    }
}
