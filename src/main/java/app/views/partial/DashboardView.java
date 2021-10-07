package app.views.partial;

import app.helpers.generateProgressiveCircle;
import app.views.BaseListView;
import app.views.windows.MainWindow;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;

public class DashboardView extends BaseListView {

    private MainWindow main;

    public int pastDeadline = 5;
    public int totalTickets = 15;
    public int openTickets = 7;

    public DashboardView(MainWindow main){
        this.main = main;

        // --DASHBOARD-- //
        this.setPrefHeight(500);
        this.setPrefWidth(750);
        this.setPadding(new Insets(50, 0, 0, 0));

        // header of dashboard
        VBox hBoxContainer = new VBox();
        Label dashboard_Title = new Label("Current incidents");

        HBox secondContainer = new HBox();
        hBoxContainer.setSpacing(150);
        hBoxContainer.setAlignment(Pos.CENTER);
        dashboard_Title.setFont(Font.font("Verdana", 30));

        generateProgressiveCircle first = new generateProgressiveCircle(150.0f, 20.0f, Color.RED);
        generateProgressiveCircle second = new generateProgressiveCircle(150.0f, 20.0f, Color.BLUE);
        secondContainer.getChildren().addAll(first.getProgressiveBar(), second.getProgressiveBar());
        secondContainer.setMinWidth(750);

        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setMinWidth(500);
        gridPane.setHgap(350);
        gridPane.add(first.getProgressiveBar(), 1, 0, 1, 1);
        GridPane.setHalignment(first.getProgressiveBar(), HPos.CENTER);
        gridPane.add(second.getProgressiveBar(), 2, 0, 1, 1);
        GridPane.setHalignment(second.getProgressiveBar(), HPos.CENTER);

        // add to dashboard
        hBoxContainer.getChildren().addAll(dashboard_Title, gridPane);
        getChildren().addAll(hBoxContainer);
    }
}
