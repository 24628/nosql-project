package com.company.test;

import com.company.database.Database;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class Connection {

    private final Database database;
    private final String collectionName = "sample_soccer_players";

    public Connection(){
        this.database = new Database("noSqlTest");

        System.out.println("Running test cases");

        this.insertOne();
        this.findOne();
        this.insertMany();
        this.findMany();
        this.findAll();
        this.updateOne();
        this.deleteOne();
    }

    private void insertOne(){
        Document doc = new Document("playerName", "Ronaldo")
                .append("age", 25)
                .append("nationality", "Filipino")
                .append("JerseyNumber", 23)
                .append("position", "Guard");

        this.database.insertOne(doc, this.collectionName);
        System.out.println("Document Inserted into user collection");
    }

    private void findOne(){
        Document user = this.database.findOne(
                eq("playerName", "Ronaldo"),
                this.collectionName
        );
        System.out.println("Found one User: " + user.toJson());
    }

    private void insertMany(){
        Document document1 = new Document("playerName", "Jan")
                .append("age", 21)
                .append("nationality", "Nederlands")
                .append("JerseyNumber", 13)
                .append("position", "Spits");

        Document document2 = new Document("playerName", "Joris")
                .append("age", 24)
                .append("nationality", "Nederlands")
                .append("JerseyNumber", 19)
                .append("position", "Middenvelder");

        Document document3 = new Document("playerName", "Piet")
                .append("age", 22)
                .append("nationality", "Nederlands")
                .append("JerseyNumber", 19)
                .append("position", "Keeper");

        //Inserting the created documents
        List<Document> list = new ArrayList<>();
        list.add(document1);
        list.add(document2);
        list.add(document3);

        this.database.insertMany(list, this.collectionName);
        System.out.println("Documents Inserted into user collection");
    }

    private void findMany(){
        List<Document> listUsers = this.database.findMany(
                eq("nationality", "Nederlands"),
                this.collectionName
        );
        System.out.println("User list with filter");
        for (Document printUser : listUsers) {
            System.out.println(printUser.toJson());
        }
    }

    private void findAll(){
        List<Document> allListUsers = this.database.findAll(this.collectionName);
        System.out.println("User list without Filter");
        for (Document printUser : allListUsers) {
            System.out.println(printUser.toJson());
        }
    }

    private void updateOne(){
        this.database.updateOne(
                eq("nationality", "Filipino"),
                new Document("$set", new Document("nationality", "Nederlands")),
                this.collectionName
        );
    }

    private void deleteOne(){
        this.database.deleteOne(
                eq("playerName", "Piet"),
                this.collectionName
        );
    }
}
