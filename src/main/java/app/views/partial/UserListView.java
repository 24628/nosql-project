package app.views.partial;

import app.model.BaseModel;
import app.model.User;
import app.views.BaseListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import org.bson.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserListView extends BaseListView {

    public UserListView() {
        this.generateTable();

        this.fillTableWithData();

        Label heading = this.addHeaders("Users");

        String[] columnNames = {"firstName", "lastName", "email", "phoneNumber", "Created_at", "Updated_at"};
        this.generateData(columnNames);

        HBox menu = this.createCrudButtons("add User", "edit User", "Delete User");

        getChildren().addAll(heading, table, menu);
    }

    protected void fillTableWithData() {
        ObservableList<User> tableList = FXCollections.observableArrayList();
        SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        for (Document doc : db.findAll("users")) {
            try {
                tableList.add(new User(
                        doc.get("firstName").toString(),
                        doc.get("lastName").toString(),
                        doc.get("email").toString(),
                        Float.parseFloat(doc.get("phonenumber").toString()),
                        dateFormat.parse(doc.get("created_at").toString()),
                        dateFormat.parse(doc.get("updated_at").toString())
                ));
            } catch (ParseException e){System.out.println(e.toString());}
        }

        for (BaseModel item : tableList) {
            table.getItems().add(item);
        }
    }

    protected void handleCreateBtnClick() {}
    protected void handleEditBtnClick() {}
    protected void handleDeleteBtnClick() {}
}

