package app.views;

import app.database.Database;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BaseListView extends VBox {

    protected Database db;

    public BaseListView(){
        db = new Database("noSql");
    }

    protected Label addHeaders(String headerName){
        this.setPadding(new Insets(20));

        Label heading = new Label();
        heading.setText(headerName);
        heading.getStyleClass().add("header");

        return heading;
    }

    protected HBox createCrudButtons(String addText, String editText, String deleteText){
        HBox btnMenu = new HBox();
        btnMenu.setPadding(new Insets(20,0,0,0));
        btnMenu.setSpacing(10);

        Button addButton = new Button(addText);
        Button editButton = new Button(editText);
        Button deleteButton = new Button(deleteText);
        btnMenu.getChildren().addAll(addButton, editButton, deleteButton);

        return btnMenu;
    }

    protected void generateData(String[] columnNames, String Model){
        for(String name: columnNames){
            TableColumn<Model, String> colType = new TableColumn<>(name);
            colType.setCellValueFactory(new PropertyValueFactory<>(name));
            table.getColumns().add(colType);
        }
    }
}
