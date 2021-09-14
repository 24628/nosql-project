package com.company.database.migrations;

import com.company.database.Database;
import com.company.database.migrations.partials.GenerateLocationsMigration;
import com.company.database.migrations.partials.GenerateUserMigration;

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
