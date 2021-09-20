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
                new Ticket(/*date,*/ "incident 1", "Kassa", "obj(id:123123213)", "High", /*date,*/ "this is the description"),
                new Ticket(/*date,*/ "incident 2", "Computer", "obj(id:123123213)", "High", /*date,*/ "this is the description"),
                new Ticket(/*date,*/ "incident 3", "Jaap", "obj(id:123123213)", "High", /*date,*/ "this is the description"),
                new Ticket(/*date,*/ "incident 4", "Kassa", "obj(id:123123213)", "High", /*date,*/ "this is the description"));



//        this.setPadding(new Insets(20));
//
//        Label heading = new Label();
//        heading.setText("Tickets");
//        heading.getStyleClass().add("header");


        table.setEditable(true);
        table.getSelectionModel().setCellSelectionEnabled(true);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Make columns and add to table
        TableColumn<Ticket, String> column1 = new TableColumn<>("incident");
        column1.setCellValueFactory(new PropertyValueFactory<>("incident"));
        TableColumn<Ticket, String> column2 = new TableColumn<>("type");
        column2.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn<Ticket, String> column3 = new TableColumn<>("user_id");
        column3.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        TableColumn<Ticket, String> column4 = new TableColumn<>("priority");
        column4.setCellValueFactory(new PropertyValueFactory<>("priority"));
        TableColumn<Ticket, String> column5 = new TableColumn<>("description");
        column5.setCellValueFactory(new PropertyValueFactory<>("description"));

        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.getColumns().add(column3);
        table.getColumns().add(column4);
        table.getColumns().add(column5);

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
