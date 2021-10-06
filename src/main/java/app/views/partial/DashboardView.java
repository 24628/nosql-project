package app.views.partial;

import app.views.BaseListView;
import app.views.windows.MainWindow;
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
        HBox dashboard_Header = new HBox();
        Label dashboard_Title = new Label("Current incidents");

        dashboard_Header.setSpacing(325);
        dashboard_Header.setAlignment(Pos.CENTER);
        dashboard_Title.setFont(Font.font("Verdana", 30));

        Arc ProgressBarProgress = new Arc();
        ProgressBarProgress.setRadiusX(150.0f);
        ProgressBarProgress.setRadiusY(150.0f);
        ProgressBarProgress.setStartAngle(0);
        ProgressBarProgress.setLength(10);
        ProgressBarProgress.fillProperty();
        ProgressBarProgress.setFill(Color.RED);
        ProgressBarProgress.setType(ArcType.ROUND);

        Arc ProgressBarHolder = new Arc();
        ProgressBarHolder.setRadiusX(150.0f);
        ProgressBarHolder.setRadiusY(150.0f);
        ProgressBarHolder.setStartAngle(0);
        ProgressBarHolder.setLength(360.0f);
        ProgressBarHolder.setFill(Color.GRAY);
        ProgressBarHolder.setType(ArcType.ROUND);

        Arc InnerCircle = new Arc();
        InnerCircle.setRadiusX(100.0f);
        InnerCircle.setRadiusY(100.0f);
        InnerCircle.setStartAngle(0);
        InnerCircle.setLength(360.0f);
        InnerCircle.setFill(Color.WHITE);
        InnerCircle.setType(ArcType.ROUND);

        AnchorPane pane = new AnchorPane();
        pane.getChildren().addAll(ProgressBarHolder, ProgressBarProgress, InnerCircle);

        dashboard_Header.getChildren().addAll(dashboard_Title,pane);


        // add to dashboard
        getChildren().add(dashboard_Header);
    }
}
