package app.database.migrations.partials;

import app.database.migrations.Migrator;
import org.bson.Document;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenerateTicketMigration extends Migrator {

    private final String collectionName = "Tickets";

    public GenerateTicketMigration(){
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());

        Document document1 = new Document("Reported", formatter.format(date))
                .append("incident", "incident1")
                .append("type", "hardware")
                .append("user_id", "obj(id:123123213)")
                .append("priority", "High")
                .append("deadline", formatter.format(date))
                .append("description", "this is a description");

        Document document2 = new Document("Reported", formatter.format(date))
                .append("incident", "incident2")
                .append("type", "software")
                .append("user_id", "obj(id:123123213)")
                .append("priority", "High")
                .append("deadline", formatter.format(date))
                .append("description", "this is a description");

        Document document3 = new Document("Reported", formatter.format(date))
                .append("incident", "incident3")
                .append("type", "wifi")
                .append("user_id", "obj(id:123123213)")
                .append("priority", "High")
                .append("deadline", formatter.format(date))
                .append("description", "this is a description");

        List<Document> tickets = new ArrayList<>();
        tickets.add(document1);
        tickets.add(document2);
        tickets.add(document3);

        this.database.insertMany(tickets, this.collectionName);
    }
}
