package app.database.migrations;

import app.database.Database;
import app.database.migrations.partials.GenerateLocationsMigration;
import app.database.migrations.partials.GenerateTicketMigration;
import app.database.migrations.partials.GenerateUserMigration;

public class Migrator {

    protected final Database database;

    public Migrator(){
        this.database = new Database("noSql");

        System.out.println("Starting migrations!");
    }

    public static void migrationsToRun() {
        new GenerateLocationsMigration();
        new GenerateUserMigration();
        new GenerateTicketMigration();
    }
}
