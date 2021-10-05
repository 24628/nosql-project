package app.database.migrations;

import app.database.Database;
import app.database.migrations.partials.GenerateLocationsMigration;
import app.database.migrations.partials.GenerateTicketMigration;
import app.database.migrations.partials.GenerateUserMigration;

import java.text.ParseException;
import java.util.Random;

public class Migrator {

    protected final Database database;

    public Migrator(){
        this.database = new Database("noSql");

        System.out.println("Starting migrations!");
    }

    public static void migrationsToRun() throws ParseException {
        new GenerateLocationsMigration();
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

    protected String selectRandomFromArray(String[] array){
        return array[new Random().nextInt(array.length)];
    }
}
