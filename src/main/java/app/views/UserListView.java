package app.views;

import app.database.Database;
import app.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserListView extends VBox {

    // This is just a mess that is there for filler. Has nothing to do with the assignments ;)

    private TableView<User> table = new TableView<>();
    private Database db;

    public UserListView() {

        db = new Database("noSql");

        ObservableList<User> data = FXCollections.observableArrayList();

        this.setPadding(new Insets(20));

        Label heading = new Label();
        heading.setText("Users");
        heading.getStyleClass().add("header");


        table.setEditable(true);
        table.getSelectionModel().setCellSelectionEnabled(true);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(150);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(220);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(250);
        emailCol.setCellValueFactory(new PropertyValueFactory<User, String>("email"));

        TableColumn groupCol = new TableColumn("Salary");
        groupCol.setMinWidth(250);
        groupCol.setCellValueFactory(new PropertyValueFactory<User, String>("salary"));

        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol, groupCol);


        HBox studentMenu = new HBox();
        studentMenu.setPadding(new Insets(20,0,0,0));
        studentMenu.setSpacing(10);

        Button addUserButton = new Button("Add User");
        Button editUserButton = new Button("Edit User");
        Button deleteUserButton = new Button("Delete User");
        studentMenu.getChildren().addAll(addUserButton, editUserButton, deleteUserButton);

        this.getChildren().addAll(heading, table, studentMenu);
    }
}

