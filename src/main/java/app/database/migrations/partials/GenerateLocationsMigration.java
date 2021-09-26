package app.database.migrations.partials;

import app.database.migrations.Migrator;
import org.bson.Document;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenerateLocationsMigration extends Migrator {

    private final String collectionName = "locations";

    public GenerateLocationsMigration(){

//        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());

        Document document1 = new Document("location", "Amsterdam")
                .append("created_at", formatter.format(date))
                .append("updated_at", null);

        Document document2 = new Document("location", "Haarlam")
                .append("created_at", formatter.format(date))
                .append("updated_at", null);

        Document document3 = new Document("location", "Knuppeldam")
                .append("created_at", formatter.format(date))
                .append("updated_at", null);

        Document document4 = new Document("location", "Headquarters")
                .append("created_at", formatter.format(date))
                .append("updated_at", null);

        List<Document> list = new ArrayList<>();
        list.add(document1);
        list.add(document2);
        list.add(document3);
        list.add(document4);

        this.database.insertMany(list, this.collectionName);
    }
}
