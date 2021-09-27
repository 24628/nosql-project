package app.database.migrations.partials;

import app.helpers.SHA512;
import app.database.migrations.Migrator;
import org.bson.Document;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.mongodb.client.model.Filters.eq;

public class GenerateUserMigration extends Migrator {

    private final String collectionName = "users";

    public GenerateUserMigration(){

//        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());

        Document serviceDeskEmployee = new Document("firstName", "Jan")
                .append("lastName", "van dijk")
                .append("type", "Service_desk")
                .append("email", "janvandijk@example.com")
                .append("phonenumber", "0687264563")
                .append("location_id", this.database.findOne(eq("location", "Amsterdam"),"locations").get("_id"))
                .append("password", SHA512.encryptThisString("password"))
                .append("created_at", formatter.format(date))
                .append("updated_at", null);

        this.database.insertOne(serviceDeskEmployee, this.collectionName);
    }
}
