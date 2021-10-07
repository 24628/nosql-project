package app.helpers;

import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class generateProgressiveCircle {

    private final AnchorPane pane = new AnchorPane();

    public generateProgressiveCircle(double radius, double percentage, Color barColor){
        Arc ProgressBarProgress = generateArc(radius, radius, 0 , percentage, barColor);
        Arc ProgressBarHolder = generateArc(radius, radius, 0 , 360.0f, Color.GRAY);
        Arc InnerCircle = generateArc(radius*0.66, radius*0.66, 0 , 360.0f, Color.WHITE);
        pane.getChildren().addAll(ProgressBarHolder, ProgressBarProgress, InnerCircle);
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

        return arc;
    }

}

