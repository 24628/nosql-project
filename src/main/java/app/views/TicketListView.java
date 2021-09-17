package app.views;

import app.database.Database;
import app.model.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TicketListView extends VBox {

    // This is just a mess that is there for filler. Has nothing to do with the assignments ;)

    private TableView<Ticket> table = new TableView<>();
    private Database db;

    public TicketListView() {

        db = new Database("noSql");

        ObservableList<Ticket> data = FXCollections.observableArrayList();

        this.setPadding(new Insets(20));

        Label heading = new Label();
        heading.setText("Tickets");
        heading.getStyleClass().add("header");


        table.setEditable(true);
        table.getSelectionModel().setCellSelectionEnabled(true);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(150);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Ticket, String>("firstName"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(220);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Ticket, String>("lastName"));

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(250);
        emailCol.setCellValueFactory(new PropertyValueFactory<Ticket, String>("email"));

        TableColumn groupCol = new TableColumn("Salary");
        groupCol.setMinWidth(250);
        groupCol.setCellValueFactory(new PropertyValueFactory<Ticket, String>("salary"));

        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol, groupCol);


        HBox studentMenu = new HBox();
        studentMenu.setPadding(new Insets(20,0,0,0));
        studentMenu.setSpacing(10);

        Button addTicketButton = new Button("Add Ticket");
        Button editTicketButton = new Button("Edit Ticket");
        Button deleteTicketButton = new Button("Delete Ticket");
        studentMenu.getChildren().addAll(addTicketButton, editTicketButton, deleteTicketButton);

        this.getChildren().addAll(heading, table, studentMenu);
    }
}
