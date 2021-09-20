package app.views.partial;

import app.model.Ticket;
import app.views.BaseListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketListView extends BaseListView {

    // This is just a mess that is there for filler. Has nothing to do with the assignments ;)

    public TicketListView() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());

        ObservableList<Ticket> data2 = FXCollections.observableArrayList(
                new Ticket(date, "incident 1", "Kassa", "obj(id:123123213)", "High", date, "this is the description"),
                new Ticket(date, "incident 2", "Computer", "obj(id:123123213)", "High", date, "this is the description"),
                new Ticket(date, "incident 3", "Jaap", "obj(id:123123213)", "High", date, "this is the description"),
                new Ticket(date, "incident 4", "Kassa", "obj(id:123123213)", "High", date, "this is the description"));


        Label heading = this.addHeaders("Tickets");

        TableView<Ticket> table = new TableView<>();
        table.setEditable(true);
        table.getSelectionModel().setCellSelectionEnabled(true);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Make columns and add to table

        String[] columnNames = {"reported", "incident", "type", "user_id", "priority", "deadline", "description"};

        this.generateData(columnNames,"Ticket");

        for (Ticket item : data2) {
            table.getItems().add(item);
        }

        HBox menu = this.createCrudButtons("add Ticket", "edit Ticket", "Delete Ticket");

        getChildren().addAll(heading, table, menu);
    }


}
