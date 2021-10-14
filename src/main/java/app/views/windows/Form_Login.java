package app.views.windows;

import app.ICallBack;
import app.database.Database;
import app.helpers.SHA512;
import app.helpers.Session;
import app.helpers.documentHandling;
import app.model.Employee;
import app.model.ServiceDeskEmployee;
import app.model.User;
import app.views.BaseForm;
import com.mongodb.client.model.Filters;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.text.ParseException;
import java.util.Objects;

public class Form_Login extends BaseForm {

    private Database db;
    private documentHandling helper;

    // all form items
    private TextField emailField;
    private PasswordField passwordField;


    public Form_Login() {
        // db conn
        db = new Database("noSql");
        helper = new documentHandling();

        // --CRUD FORM-- //
        this.addUIControls(this.form);
        layout.getChildren().addAll(this.form);

        // Create the main scene.
        Scene form_Login = new Scene(layout);
        form_Login.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/style.css")).toString());

        // Let's go!
        stage.setTitle("Form Ticket");
        stage.setScene(form_Login);

    }

    @Override
    protected VBox createNavBar(){
        return new VBox();
    }

    protected void addUIControls(GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("Login");
        headerLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        emailField = this.generateTextField("email: ", 1);
        passwordField = this.generatePasswordField("password: ", 2);

        Button submitButton = this.generateFormBtn("LOGIN", 0);

        submitButton.setOnAction(actionEvent -> {
            try {
                this.handleSubmitBtnClick(new ICallBack() {
                    public void onSucces() {}
                    public void onError(String err) {}
                });
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    protected void handleSubmitBtnClick(ICallBack callBack) throws ParseException {
        String email = emailField.getText();
        String password = passwordField.getText();

        Bson filter = Filters.and(
                Filters.eq("email", email),
                Filters.eq("password", SHA512.encryptThisString(password))
        );

        Document result = db.findOne(filter, "users");
        System.out.println(result);

        if(result != null){
            if(Objects.equals(result.getString("type"), "Service_desk")){

                System.out.println("SD employee");
                ServiceDeskEmployee user = new ServiceDeskEmployee(
                    result.get("firstName").toString(),
                    result.get("lastName").toString(),
                    result.get("type").toString(),
                    result.get("email").toString(),
                    result.get("phonenumber").toString(),
                    result.get("location").toString()
                );

                loadNewWindow(user);
            } else {
                System.out.println("Employee");
                Employee user = new Employee(
                        result.get("firstName").toString(),
                        result.get("lastName").toString(),
                        result.get ("type").toString(),
                        result.get("email").toString(),
                        result.get("phonenumber").toString(),
                        result.get("location").toString()
                );

                loadNewWindow(user);
            }
        }
    }

    private void loadNewWindow(User user){
        this.getStage().close();

        Session.getInstance(user);
        MainWindow mw = new MainWindow();
        mw.getStage().show();
    }
}
