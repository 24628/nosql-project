package src.database.migrations;

import src.database.Database;
import src.database.migrations.partials.GenerateLocationsMigration;
import src.database.migrations.partials.GenerateUserMigration;

public class Migrator {

    protected final Database database;

    public Migrator(){
        this.database = new Database("noSql");

        System.out.println("Starting migrations!");
    }

    public static void migrationsToRun() {
        new GenerateLocationsMigration();
        new GenerateUserMigration();
    }
}
