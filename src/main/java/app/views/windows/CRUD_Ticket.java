package app.views.windows;

import app.model.BaseModel;
import app.views.BaseView;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CRUD_Ticket extends BaseView {

    public CRUD_Ticket() {

        // --CRUD FORM-- //
        this.addUIControls(this.form);

        // Add the menu and the view. Default view will be the student list view
        layout.getChildren().addAll(this.form);

        // Create the main scene.
        // Scene mainScene = new StyledScene(layout);
        Scene crud_Ticket = new Scene(layout);

        // Let's go!
        stage.setTitle("CRUD Ticket");
        stage.setScene(crud_Ticket);
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

        for (Control item : formItems) {
            if(item instanceof TextField){
                final TextField parsedTextField = (TextField) item;
                System.out.println(parsedTextField.getText());
            }

            if(item instanceof ComboBox){
                final ComboBox parsedComboBox = (ComboBox) item;
                System.out.println(parsedComboBox.getValue());
            }

            if(item instanceof DatePicker){
                final DatePicker parsedComboBox = (DatePicker) item;
                System.out.println(parsedComboBox.getValue());
            }
        }
    }
}
