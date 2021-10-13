package app.database.migrations;

import app.database.Database;
import app.database.migrations.partials.GenerateTicketMigration;
import app.database.migrations.partials.GenerateUserMigration;

import java.text.ParseException;
import java.util.Random;

public class Migrator {

    protected final Database database;
    private final String loremIpsum = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

    public Migrator(){
        this.database = new Database("noSql");

        System.out.println("Starting migrations!");
    }

    public static void migrationsToRun() throws ParseException {
//        new GenerateLocationsMigration();
        new GenerateUserMigration();
        new GenerateTicketMigration();
    }

    protected String generateRandomString(int length){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    protected String generateActualText(int length){
        if(loremIpsum.length() > length)
            return loremIpsum.substring(0, length);
        return loremIpsum.substring(0, 10);
    }

    protected String selectRandomFromArray(String[] array){
        return array[new Random().nextInt(array.length)];
    }
}
