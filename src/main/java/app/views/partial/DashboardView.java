package app.views.partial;

import app.helpers.dateParser;
import app.helpers.generateProgressiveCircle;
import app.views.BaseListView;
import app.views.windows.MainWindow;
import com.mongodb.client.model.Filters;
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
import org.controlsfx.control.spreadsheet.Grid;

import java.time.LocalDateTime;

public class DashboardView extends BaseListView {

    private MainWindow main;

    public int pastDeadline;
    public int totalTickets;
    public int openTickets;

    public DashboardView(MainWindow main){
        this.main = main;

        pastDeadline = this.db.findMany(Filters.and(
                    Filters.lt("deadline", dateParser.toString(LocalDateTime.now())),
                    Filters.not(Filters.eq("status" , "Closed"))
                ),
        "Tickets").size();
        //totalTickets = this.db.findAll("Tickets").size();
        totalTickets = this.db.findMany(Filters.not(Filters.eq("status" , "Closed")), "Tickets").size();
        openTickets = this.db.findMany(Filters.eq("status", "Normal"), "Tickets").size();

        // --DASHBOARD-- //
        this.setPrefHeight(500);
        this.setPrefWidth(750);
        this.setPadding(new Insets(50, 0, 0, 0));

        // header of dashboard
        VBox hBoxContainer = new VBox();
        Label dashboard_Title = new Label("Current incidents");

        hBoxContainer.setSpacing(150);
        hBoxContainer.setAlignment(Pos.CENTER);
        dashboard_Title.setFont(Font.font("Verdana", 30));

        generateProgressiveCircle first = new generateProgressiveCircle(150.0f, Color.LIGHTSKYBLUE, totalTickets, openTickets);
        generateProgressiveCircle second = new generateProgressiveCircle(150.0f, Color.RED, pastDeadline);
        AnchorPane firstPane = first.getProgressiveBar();
        AnchorPane secondPane = second.getProgressiveBar();

        GridPane gridPane = new GridPane();
        //gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setMinWidth(500);
        gridPane.setPrefWidth(500);
        gridPane.setHgap(350);
        gridPane.add(firstPane, 1, 0);
        gridPane.add(secondPane, 2, 0);

        Label lblOpenIncidents = new Label("Open incidents");
        Label lblPastDeadline = new Label("Past deadline");
        lblOpenIncidents.setTranslateX(-200);
        lblPastDeadline.setTranslateX(-190);
        lblOpenIncidents.setFont(Font.font(30));
        lblPastDeadline.setFont(Font.font(30));
        gridPane.add(lblOpenIncidents, 1, 1);
        gridPane.add(lblPastDeadline, 2, 1);

        // add to dashboard
        hBoxContainer.getChildren().addAll(dashboard_Title, gridPane);
        getChildren().addAll(hBoxContainer);
    }
}