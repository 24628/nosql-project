package app.views.partial;

import app.model.BaseModel;
import app.model.Ticket;
import app.views.BaseListView;
import app.views.CRUD_Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.bson.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TicketListView extends BaseListView {

    public TicketListView() {
        this.generateTable();

        this.fillTableWithData();

        Label heading = this.addHeaders("Tickets");

        String[] columnNames = {"reported", "incident", "type", "user_id", "priority", "deadline", "description"};
        this.generateData(columnNames);

        HBox menu = this.createCrudButtons("add Ticket", "edit Ticket", "Delete Ticket");

        getChildren().addAll(heading, table, menu);
    }

    protected void fillTableWithData() {
        ObservableList<Ticket> tableList = FXCollections.observableArrayList();
        SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        for (Document doc : db.findAll("Tickets")) {
            try {
                tableList.add(new Ticket(
                        dateFormat.parse(doc.get("Reported").toString()),
                        doc.get("incident").toString(),
                        doc.get("type").toString(),
                        doc.get("user_id").toString(),
                        doc.get("priority").toString(),
                        dateFormat.parse(doc.get("deadline").toString()),
                        doc.get("description").toString()
                ));
            } catch (ParseException e){System.out.println(e.toString());}
        }

        for (BaseModel item : tableList) {
            table.getItems().add(item);
        }
    }

    protected void handleCreateBtnClick() {  }
    protected void handleEditBtnClick() {

    }
    protected void handleDeleteBtnClick() {

    }

}
