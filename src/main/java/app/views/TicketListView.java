package app.views;

import app.database.Database;
import app.model.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketListView extends VBox {

    // This is just a mess that is there for filler. Has nothing to do with the assignments ;)

    private TableView<Ticket> table = new TableView<>();
    private Database db;

    public TicketListView() {

        //db = new Database("noSql");

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());

        ObservableList<Ticket> data2 = FXCollections.observableArrayList(
                new Ticket(date, "incident 1", "Kassa", "obj(id:123123213)", "High", date, "this is the description"),
                new Ticket(date,"incident 2", "Computer", "obj(id:123123213)", "High", date, "this is the description"),
                new Ticket(date, "incident 3", "Jaap", "obj(id:123123213)", "High", date, "this is the description"),
                new Ticket(date, "incident 4", "Kassa", "obj(id:123123213)", "High", date, "this is the description"));



//        this.setPadding(new Insets(20));
//
//        Label heading = new Label();
//        heading.setText("Tickets");
//        heading.getStyleClass().add("header");


        table.setEditable(true);
        table.getSelectionModel().setCellSelectionEnabled(true);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Make columns and add to table
        TableColumn<Ticket, String> reportedCol = new TableColumn<>("reported");
        reportedCol.setCellValueFactory(new PropertyValueFactory<>("reported"));
        TableColumn<Ticket, String> incidentCol = new TableColumn<>("incident");
        incidentCol.setCellValueFactory(new PropertyValueFactory<>("incident"));
        TableColumn<Ticket, String> typeCol = new TableColumn<>("type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn<Ticket, String> userCol = new TableColumn<>("user_id");
        userCol.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        TableColumn<Ticket, String> priorityCol = new TableColumn<>("priority");
        priorityCol.setCellValueFactory(new PropertyValueFactory<>("priority"));
        TableColumn<Ticket, String> deadlineCol = new TableColumn<>("deadline");
        deadlineCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        TableColumn<Ticket, String> descriptionCol = new TableColumn<>("description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));


        table.getColumns().add(reportedCol);
        table.getColumns().add(incidentCol);
        table.getColumns().add(typeCol);
        table.getColumns().add(userCol);
        table.getColumns().add(priorityCol);
        table.getColumns().add(deadlineCol);
        table.getColumns().add(descriptionCol);

        for (Ticket item: data2) {
            table.getItems().add(item);
        }




        HBox studentMenu = new HBox();
        studentMenu.setPadding(new Insets(20,0,0,0));
        studentMenu.setSpacing(10);

        Button addTicketButton = new Button("Add Ticket");
        Button editTicketButton = new Button("Edit Ticket");
        Button deleteTicketButton = new Button("Delete Ticket");
        studentMenu.getChildren().addAll(addTicketButton, editTicketButton, deleteTicketButton);

        this.getChildren().addAll(table, studentMenu);
    }
}
