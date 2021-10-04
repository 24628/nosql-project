package app;

import app.database.migrations.Migrator;
import app.views.windows.Form_Login;
import app.views.windows.Form_Ticket;
import app.views.windows.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // rendering the text more crisply
        System.setProperty("prism.lcdtext", "false");

        // We should open a login window, but that is outside of scope for this lesson.
        // So we go straight to the main window of the application

//        new Migrator();
//        Migrator.migrationsToRun();

        Form_Login form = new Form_Login();
        form.getStage().show();

    }

    public static void main(String[] args) {
        //Connection = new Connection();
        launch();
    }
}