package app.helpers;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;

public class generateProgressiveCircle {

    private final AnchorPane pane = new AnchorPane();

    public generateProgressiveCircle(double radius, double percentage, Color barColor, int totalTickets, int openTickets){
        Arc ProgressBarProgress = generateArc(radius, radius, 0 , percentage, barColor);
        Arc ProgressBarHolder = generateArc(radius, radius, 0 , 360.0f, Color.GRAY);
        Arc InnerCircle = generateArc(radius*0.66, radius*0.66, 0 , 360.0f, Color.WHITE);

        Label label1 = new Label(String.valueOf(openTickets) + " / " + String.valueOf(totalTickets));
        label1.setFont(Font.font("Verdana", 30));
        label1.setTranslateX(-150);
        label1.setTranslateY(-20);

        pane.getChildren().addAll(ProgressBarHolder, ProgressBarProgress, InnerCircle, label1);
    }

    public generateProgressiveCircle(double radius, double percentage, Color barColor, int pastDeadline){
        Arc ProgressBarProgress = generateArc(radius, radius, 0 , percentage, barColor);
        Arc ProgressBarHolder = generateArc(radius, radius, 0 , 360.0f, Color.GRAY);
        Arc InnerCircle = generateArc(radius*0.66, radius*0.66, 0 , 360.0f, Color.WHITE);

        Label label1 = new Label(String.valueOf(pastDeadline));
        label1.setFont(Font.font("Verdana", 30));
        label1.setTranslateX(-120);
        label1.setTranslateY(-20);

        pane.getChildren().addAll(ProgressBarHolder, ProgressBarProgress, InnerCircle, label1);
    }

    public AnchorPane getProgressiveBar(){
        return pane;
    }

    private Arc generateArc(double radiusX, double radiusY, double startAngle, double lenght,  Color color){
        Arc arc = new Arc();
        arc.setRadiusX(radiusX);
        arc.setRadiusY(radiusY);
        arc.setStartAngle(startAngle);
        arc.setLength(lenght);
        arc.setFill(color);
        arc.setType(ArcType.ROUND);
        arc.setTranslateX(-100);
        return arc;
    }

}

