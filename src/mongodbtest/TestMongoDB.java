/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbtest;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.Arrays;
import java.util.regex.Pattern;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author George
 */
public class TestMongoDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
       
        MongoClient mongo = new MongoClient("localhost");
        MongoDatabase database = mongo.getDatabase("test");

        MongoCollection<Document> collection = database.getCollection("restaurantes");

     
        Document document1 = new Document()
                .append("nombre", "pizza planeta")
                .append("categoria", Arrays.asList("pizza", "hamburguesa", "sopa"))
                .append("rating", 4);

        collection.insertOne(document1);

        Document document2 = new Document()
                .append("nombre", "tacomex")
                .append("categoria", Arrays.asList("taco", "sopa"))
                .append("rating", 3);

        collection.insertOne(document2);

        Document document3 = new Document()
                .append("nombre", "sushilito")
                .append("categoria", Arrays.asList("sushi", "hot wings", "papas fritas"))
                .append("rating", 5);

        collection.insertOne(document3);

        FindIterable<Document> documents = collection.find(Filters.gt("rating", 4));

        FindIterable<Document> documents2 = collection.find(Filters.eq("categoria", "pizza"));

        FindIterable<Document> documents3 = collection.find(Filters.in("nombre", "sushi"));

        UpdateResult updateResult = collection.updateOne(
                Filters.eq("categoria", Arrays.asList("sushi", "hot wings", "papas fritas")),
                Updates.set("categoria", Arrays.asList("sushi", "hot wings", "papas fritas", "categoria extra")));

        DeleteResult deleteResult = collection.deleteOne(Filters.eq("_id", new ObjectId("5ebb20f6860e162a3847f418")));

        DeleteResult deleteResult2 = collection.deleteOne(Filters.lte("rating", 3));




         
        
        
        
        
        
    }
    
}
