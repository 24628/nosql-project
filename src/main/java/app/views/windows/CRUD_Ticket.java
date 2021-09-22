package app.views.windows;

import app.views.BaseView;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CRUD_Ticket extends BaseView {

    public CRUD_Ticket() {

        // --CRUD FORM-- //
        GridPane form = createGrid();
        addUIControls(form);

        // Add the menu and the view. Default view will be the student list view
        layout.getChildren().addAll(form);

        // Create the main scene.
        // Scene mainScene = new StyledScene(layout);
        Scene crud_Ticket = new Scene(layout);

        // Let's go!
        stage.setTitle("CRUD Ticket");
        stage.setScene(crud_Ticket);
    }

    private GridPane createGrid(){
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 80, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(150, 150, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(150,150, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("Create Ticket");
        headerLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add reported Label
        Label reportedLabel = new Label("Date/time reported: ");
        gridPane.add(reportedLabel, 0,1);

        // Add reported date picker
        DatePicker reportedDate = new DatePicker();
        reportedDate.setPrefHeight(20);
        gridPane.add(reportedDate, 1, 1);

        // Add subjectIncident Label
        Label subjectIncidentLabel = new Label("Subject of incident: ");
        gridPane.add(subjectIncidentLabel, 0, 2);

        // Add subjectIncident Text Field
        TextField subjectIncidentField = new TextField();
        subjectIncidentField.setPrefHeight(20);
        gridPane.add(subjectIncidentField, 1, 2);

        // Add type Label
        Label typeLabel = new Label("Type of incident: ");
        gridPane.add(typeLabel, 0, 3);

        // add type combobox
        ComboBox typeBox = new ComboBox();
        typeBox.getItems().addAll("Choice1", "choice2", "choice3");
        gridPane.add(typeBox, 1, 3);

        // Add user Label
        Label userLabel = new Label("Reported by user: ");
        gridPane.add(userLabel, 0, 4);

        // Add user combobox
        ComboBox userBox = new ComboBox();
        userBox.getItems().addAll("Bram", "Koen", "Noor");
        gridPane.add(userBox, 1, 4);

        // Add priority Label
        Label priorityLabel = new Label("Priority: ");
        gridPane.add(priorityLabel, 0, 5);

        // Add priority combobox
        ComboBox priorityBox = new ComboBox();
        priorityBox.getItems().addAll("LOW", "MEDIUM", "HIGH");
        gridPane.add(priorityBox, 1, 5);

        // Add deadline Label
        Label deadlineLabel = new Label("Deadline/follow up: ");
        gridPane.add(deadlineLabel, 0, 6);

        // Add deadline Field
        PasswordField deadlineField = new PasswordField();
        deadlineField.setPrefHeight(20);
        gridPane.add(deadlineField, 1, 6);

        // Add description Label
        Label descriptionLabel = new Label("Description: ");
        gridPane.add(descriptionLabel, 0, 7);

        // Add description Field
        PasswordField descriptionField = new PasswordField();
        descriptionField.setPrefHeight(20);
        gridPane.add(descriptionField, 1, 7);

        // add Cancel button
        Button cancelButton = new Button("CANCEL");
        cancelButton.setPrefHeight(40);
        cancelButton.setDefaultButton(false);
        cancelButton.setPrefWidth(100);
        gridPane.add(cancelButton, 0, 8, 2, 1);
        GridPane.setHalignment(cancelButton, HPos.LEFT);
        GridPane.setMargin(cancelButton, new Insets(20, 0,20,0));

        // Add Submit Button
        Button submitButton = new Button("SUBMIT TICKET");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 1, 8, 2, 1);
        GridPane.setHalignment(submitButton, HPos.RIGHT);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));
    }


}
