package app.views.partial;

import app.views.BaseListView;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class DashboardView extends BaseListView {

    public DashboardView(){

        // --DASHBOARD-- //
        this.setPrefHeight(500);
        this.setPrefWidth(750);
        this.setPadding(new Insets(50, 0, 0, 0));

        // header of dashboard
        HBox dashboard_Header = new HBox();
        Button show_List = new Button("SHOW LIST");
        Label dashboard_Title = new Label("Current incidents");

        dashboard_Header.setSpacing(325);
        dashboard_Title.setFont(Font.font("Verdana", 30));
        show_List.setMinWidth(150);
        dashboard_Header.getChildren().addAll(dashboard_Title, show_List);

        // add to dashboard
        getChildren().add(dashboard_Header);
    }
}
