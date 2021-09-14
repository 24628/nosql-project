package com.company;

import com.company.database.migrations.Migrator;
import com.company.test.*;

public class Main {

    public static void main(String[] args) {
         System.out.println("Running program!");

         //Connection test = new Connection();

        Migrator.migrationsToRun();
    }
}
