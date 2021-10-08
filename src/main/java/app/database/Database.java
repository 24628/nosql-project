package app.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private MongoDatabase database;
//    private final String conS = "mongodb+srv://new-user31:qx7AA7LwaCD5WHj@cluster0.twb3h.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";
    //private final String conS = "mongodb+srv://Bram:Mdt58w8d1!@clusternosql.q16av.mongodb.net/ProjectNoSQL?retryWrites=true&w=majority";
    private final String conS = "mongodb+srv://dbUser:Welkom1234!@cluster0.fpuzw.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";

    private List<Document> tickets;
    private List<Document> users;

    public Database(String DatabaseName) {
        MongoClient mongoClient = MongoClients.create(this.conS);
        this.database = mongoClient.getDatabase(DatabaseName);

        tickets = this.findMany(Filters.not(Filters.eq("status", "Closed")), "tickets");
        users = this.findAll("users");
    }

    //@todo Make sure item doesn't already exist in the database!
    /** insert one document into the collection
     * @param data Document data
     * @param CollectionName collection you want to insert to
     */
    public void insertOne(Document data, String CollectionName) {
        this.database.getCollection(CollectionName).insertOne(data);
    }

    //todo Make sure items don't already exist in the database!
    /** Insert many documents into the collection
     * @param data Document data in an array list
     * @param CollectionName collection you want to insert to
     */
    public void insertMany(List<Document> data, String CollectionName){
        MongoCollection<Document> collection = this.database.getCollection(CollectionName);
        collection.insertMany(data);
    }

    /**
     * @param filterString string where you want to filter on
     * @param CollectionName collection you want to find one from
     * @return return given document
     */
    public Document findOne(Bson filterString, String CollectionName) {
        MongoCollection<Document> collection = this.database.getCollection(CollectionName);
        return collection.find(filterString).first();
    }

    /**
     * @param filterString string where you want to filter on
     * @param CollectionName collection you want to find one from
     * @return return given documents in an array list!
     */
    public ArrayList<Document> findMany(Bson filterString, String CollectionName){
        MongoCollection<Document> collection = this.database.getCollection(CollectionName);
        return collection.find(filterString).into(new ArrayList<>());
    }

    /**
     * @param CollectionName collection you want to find one from
     * @return return all document from the collection!
     */
    public ArrayList<Document> findAll(String CollectionName){
        MongoCollection<Document> collection = this.database.getCollection(CollectionName);
        return collection.find().into(new ArrayList<>());
    }

    /** update one document from the collection
     * @param filterString to filter the document
     * @param data Document data you want to update
     * @param CollectionName collection you want to update to
     */
    public void updateOne(Bson filterString, Document data, String CollectionName) {
        if(this.findOne(filterString, CollectionName) != null) {
            MongoCollection<Document> collection = this.database.getCollection(CollectionName);
            collection.updateOne(filterString, data);
        }
    }
    /** delete one document into the collection
     * @param filterString to filter the document
     * @param CollectionName collection you want to delete from
     */
    public void deleteOne(Bson filterString, String CollectionName) {
        if(this.findOne(filterString, CollectionName) != null) {
            MongoCollection<Document> collection = this.database.getCollection(CollectionName);
            collection.deleteOne(filterString);
        }
    }
    /** update one document from the collection
     * @param filterString to filter the document
     * @param data Document data you want to replace
     * @param CollectionName collection you want to update to
     */
    public void replaceOne(Bson filterString, Bson data, String CollectionName){
        if(this.findOne(filterString, CollectionName) != null) {
            MongoCollection<Document> collection = this.database.getCollection(CollectionName);
            collection.replaceOne(filterString, (Document) data);
        }
    }

    public List<Document> getTickets() {
        return tickets;
    }

    public void setTickets(List<Document> tickets) {
        this.tickets = tickets;
    }

    public List<Document> getUsers() {
        return users;
    }

    public void setUsers(List<Document> users) {
        this.users = users;
    }
}
