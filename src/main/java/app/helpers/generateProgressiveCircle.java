package app.helpers;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;

public class generateProgressiveCircle {

    private final AnchorPane pane = new AnchorPane();

    public generateProgressiveCircle(double radius, Color barColor, int totalTickets, int openTickets){
        Arc ProgressBarProgress = generateArc(radius, radius, (((double) openTickets / (double)totalTickets) * 100 * 3.60), barColor);
        Arc ProgressBarHolder = generateArc(radius, radius, 360.0f, Color.GRAY);
        Arc InnerCircle = generateArc(radius*0.66, radius*0.66, 360.0f, Color.WHITE);

        Label label = new Label(String.valueOf(openTickets) + " / " + String.valueOf(totalTickets));
        label.setFont(Font.font("Verdana", 30));
        label.setTranslateX(-150);
        label.setTranslateY(-20);

        pane.getChildren().addAll(ProgressBarHolder, ProgressBarProgress, InnerCircle, label);
    }

    public generateProgressiveCircle(double radius, Color barColor, int pastDeadline){
        Arc ProgressBarProgress = generateArc(radius, radius, (double) pastDeadline * 45, barColor);
        Arc ProgressBarHolder = generateArc(radius, radius, 360.0f, Color.GRAY);
        Arc InnerCircle = generateArc(radius*0.66, radius*0.66, 360.0f, Color.WHITE);

        Label label = new Label(String.valueOf(pastDeadline));
        label.setFont(Font.font("Verdana", 30));
        label.setTranslateX(-120);
        label.setTranslateY(-20);

        pane.getChildren().addAll(ProgressBarHolder, ProgressBarProgress, InnerCircle, label);
    }

    public AnchorPane getProgressiveBar(){
        return pane;
    }

    private Arc generateArc(double radiusX, double radiusY, double length, Color color){
        Arc arc = new Arc();
        arc.setRadiusX(radiusX);
        arc.setRadiusY(radiusY);
        arc.setStartAngle(90);
        arc.setLength(length * -1);
        arc.setFill(color);
        arc.setType(ArcType.ROUND);
        arc.setTranslateX(-100);
        return arc;
    }

}

