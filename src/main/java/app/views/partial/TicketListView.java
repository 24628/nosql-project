package app.views.partial;

import app.database.Database;
import app.helpers.Session;
import app.helpers.dateParser;
import app.helpers.ticketFilter;
import app.model.BaseModel;
import app.model.Ticket;
import app.views.BaseListView;
import app.views.windows.Form_Ticket;
import app.views.windows.MainWindow;
import com.mongodb.client.model.Filters;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.text.ParseException;
import java.time.LocalDateTime;

import static com.mongodb.client.model.Filters.eq;

public class TicketListView extends BaseListView {

    // main window
    private MainWindow mainWindow;



    // --Constructor
    public TicketListView(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.generateTable();
        this.fillTableWithData(Filters.not(Filters.eq("status", "Closed")));

        // header title and search box
        Label heading = this.addHeaders("Tickets");
        TextField filterTable = new TextField();
        filterTable.setMaxWidth(200);
        filterTable.setPromptText("Enter something...");
        filterTable.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    table.getItems().clear();
                    ticketFilter filter = new ticketFilter();
                    fillTableWithData(filter.filterTickets(filterTable.getText()));
                }
            }
        });

        //setCellValueFactory in BaseListView (tableview fills table with property's of ticket)
        String[] columnNames = {"reported", "incident", "type", "user_id", "employee_id", "priority", "deadline", "description", "status"};
        this.generateData(columnNames);

        HBox menu = new HBox();
        if (Session.isServiceDeskEmployee())
            menu = this.createCrudButtons("add Ticket", "edit Ticket", "Delete Ticket");

        getChildren().addAll(heading, filterTable, table, menu); // add all
    }




    // --fill table with bson filter
    protected void fillTableWithData(Bson filter) {
        ObservableList<Ticket> tableList = FXCollections.observableArrayList();
        Database db = new Database("noSql");

        dateParser parser = new dateParser();
        for (Document doc : db.findMany(filter, "Tickets")) {
            try {
                tableList.add(new Ticket(
                        parser.toDate(doc.get("Reported").toString()),
                        doc.get("incident").toString(),
                        doc.get("type").toString(),
                        db.findOne(Filters.eq("_id", new ObjectId((String) doc.get("user_id"))), "users").getString("firstName"),
                        db.findOne(Filters.eq("_id", new ObjectId((String) doc.get("employee_id"))), "users").getString("firstName"),
                        doc.get("priority").toString(),
                        parser.toDate(doc.get("deadline").toString()),
                        doc.get("description").toString(),
                        doc.get("status").toString()
                ));
            } catch (ParseException e) {
                System.out.println(e.toString());
            }
        }
        for (BaseModel item : tableList) {
            table.getItems().add(item);
        }
    }





    // open empty form to add a ticket
    protected void handleCreateBtnClick() {
        new Form_Ticket(null).getStage().show();
        this.mainWindow.getStage().close();
    }




    // when there is an item selected, create form with all ticket properties filled
    protected void handleEditBtnClick() {
        if (table.getSelectionModel().getSelectedItem() != null) {
            new Form_Ticket((Ticket) table.getSelectionModel().getSelectedItem()).getStage().show();
            this.mainWindow.getStage().close();
        }
    }




    // --button event deleting a ticket
    protected void handleDeleteBtnClick() {
        if (table.getSelectionModel().getSelectedItem() != null) {
            // alert user about his action
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Delete item");
            alert.setHeaderText("Item is about to be deleted");
            alert.setContentText("Are you sure you want to delete this item?");

            // delete when button ok is pressed
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    Ticket t = (Ticket) table.getSelectionModel().getSelectedItem();
                    Bson filter = eq("incident", t.getIncident());
                    db.deleteOne(filter, "Tickets");
                }
            });
        }
    }
}
