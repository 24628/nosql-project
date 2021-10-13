package app;

import app.views.windows.Form_Login;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // rendering the text more crisply
        System.setProperty("prism.lcdtext", "false");

//       new Migrator();
//       Migrator.migrationsToRun();

        Form_Login form = new Form_Login();
        form.getStage().show();
    }

    public static void main(String[] args) {
        launch();
    }
}