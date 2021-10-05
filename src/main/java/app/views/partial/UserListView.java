package app.views.partial;

import app.helpers.dateParser;
import app.model.BaseModel;
import app.model.Ticket;
import app.model.User;
import app.views.BaseListView;
import app.views.windows.Form_Ticket;
import app.views.windows.Form_User;
import app.views.windows.MainWindow;
import com.mongodb.client.model.Filters;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserListView extends BaseListView {

    private MainWindow mainWindow;

    public UserListView(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        this.generateTable();

        this.fillTableWithData();

        Label heading = this.addHeaders("Users");

        TextField filterTable = new TextField();
        filterTable.setMaxWidth(200);
        filterTable.setPromptText("Enter something...");

        String[] columnNames = {"firstName", "lastName", "email", "phoneNumber", "Created_at", "Updated_at"};
        this.generateData(columnNames);

        HBox menu = this.createCrudButtons("add User", "edit User", "Delete User");

        getChildren().addAll(heading, filterTable, table, menu);
    }

    protected void fillTableWithData() {
        ObservableList<User> tableList = FXCollections.observableArrayList();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        for (Document doc : db.findAll("users")) {
            try {
                tableList.add(new User(
                        doc.get("firstName").toString(),
                        doc.get("lastName").toString(),
                        doc.get("email").toString(),
                        doc.get("phonenumber").toString(),
                        dateParser.toDate(doc.get("created_at").toString()),
                        dateParser.toDate(doc.get("updated_at").toString())
                ));
            } catch (ParseException e) {
                System.out.println(e.toString());
            }
        }

        for (BaseModel item : tableList) {
            table.getItems().add(item);
        }
    }

    protected void handleCreateBtnClick() {
        new Form_User(null).getStage().show();
        this.mainWindow.getStage().close();
    }

    protected void handleEditBtnClick() {
        if (table.getSelectionModel().getSelectedItem() != null) {
            new Form_User(
                    (User) table.getSelectionModel().getSelectedItem()
            ).getStage().show();
            this.mainWindow.getStage().close();
        }
    }

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
                    User u = (User) table.getSelectionModel().getSelectedItem();
                    Bson filter = Filters.eq("email", u.getEmail());
                    db.deleteOne(filter, "user");
                }
            });
        }
    }
}

