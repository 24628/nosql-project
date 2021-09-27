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
                .append("incident", "Flammable laptop.")
                .append("type", "Hardware")
                .append("user_id", "obj(id:123123213)")
                .append("priority", "High")
                .append("deadline", formatter.format(date))
                .append("description", "Laptop went up in flames.");

        Document document2 = new Document("Reported", formatter.format(date))
                .append("incident", "Software problem.")
                .append("type", "Software")
                .append("user_id", "obj(id:123123213)")
                .append("priority", "High")
                .append("deadline", formatter.format(date))
                .append("description", "System 32 was erased.");

        Document document3 = new Document("Reported", formatter.format(date))
                .append("incident", "Sandwich stolen.")
                .append("type", "Service")
                .append("user_id", "obj(id:123123213)")
                .append("priority", "High")
                .append("deadline", formatter.format(date))
                .append("description", "Ross's turkey sandwich was stolen");

        List<Document> tickets = new ArrayList<>();
        tickets.add(document1);
        tickets.add(document2);
        tickets.add(document3);

        this.database.insertMany(tickets, this.collectionName);
    }
}
