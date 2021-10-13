package app.database.migrations.partials;

import app.helpers.SHA512;
import app.database.migrations.Migrator;
import org.bson.Document;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateUserMigration extends Migrator {

    private final String collectionName = "users";

    public GenerateUserMigration() throws ParseException {

        List<Document> users = new ArrayList<>();
        for(int i = 1; i < 100; i++){
            users.add(generateDocument());
        }

        this.database.insertMany(users, this.collectionName);
    }

    private Document generateDocument(){
        String[] type = {"Employee", "Service_desk"};
        String[] location = {"Amsterdam", "Haarlem", "Knuppeldam", "Headquarters (HQ)"};

        return new Document("firstName", generateRandomString(ThreadLocalRandom.current().nextInt(5, 14)))
                .append("lastName", generateRandomString(ThreadLocalRandom.current().nextInt(5, 14)))
                .append("type", selectRandomFromArray(type))
                .append("email", generateRandomString(ThreadLocalRandom.current().nextInt(5, 14))+"@example.com")
                .append("phonenumber", "0687264563")
                .append("location", selectRandomFromArray(location))
                .append("password", SHA512.encryptThisString("password"));
    }
}
