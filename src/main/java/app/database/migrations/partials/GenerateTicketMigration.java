package app.database.migrations.partials;

import app.database.migrations.Migrator;
import app.helpers.dateParser;
import org.bson.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenerateTicketMigration extends Migrator {

    private final String collectionName = "Tickets";

    public GenerateTicketMigration() throws ParseException {
        Date date = new Date(System.currentTimeMillis());
        dateParser parser = new dateParser();

        new dateParser();
        Document document1 = new Document("Reported", parser.toString(date))
                .append("incident", "Flammable laptop.")
                .append("type", "Hardware")
                .append("user_id", "obj(id:123123213)")
                .append("priority", "High")
                .append("deadline", parser.toString(date))
                .append("description", "Laptop went up in flames.")
                .append("status", "escalate");

        Document document2 = new Document("Reported", parser.toString(date))
                .append("incident", "Software problem.")
                .append("type", "Software")
                .append("user_id", "obj(id:123123213)")
                .append("priority", "High")
                .append("deadline", parser.toString(date))
                .append("description", "System 32 was erased.")
                .append("status", "closed");;

        Document document3 = new Document("Reported", parser.toString(date))
                .append("incident", "Sandwich stolen.")
                .append("type", "Service")
                .append("user_id", "obj(id:123123213)")
                .append("priority", "High")
                .append("deadline", parser.toString(date))
                .append("description", "Ross's turkey sandwich was stolen")
                .append("status", "normal");;

        List<Document> tickets = new ArrayList<>();
        tickets.add(document1);
        tickets.add(document2);
        tickets.add(document3);

        this.database.insertMany(tickets, this.collectionName);
    }
}
