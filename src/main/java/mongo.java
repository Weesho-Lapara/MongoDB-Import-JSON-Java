import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.apache.commons.io.IOUtils;
import org.bson.Document;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class mongo {
    public static void main(String[] args) throws IOException {

        com.mongodb.client.MongoClient client = MongoClients.create( "mongodb+srv://bipin:mynameis@sandbox.jadwj.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");

        MongoDatabase database = client.getDatabase("testJava");
        MongoCollection<Document> coll = database.getCollection("jsonImport");

        try {

            InputStream inStream = new FileInputStream("C:\\Users\\Bipin\\Downloads\\Data.json");
            String body = IOUtils.toString(inStream, StandardCharsets.UTF_8.name());
            Document myDoc = Document.parse(body);

            InsertOneResult insertResult = coll.insertOne(myDoc);
            System.out.println("Inserted" + insertResult);

        } catch (MongoWriteException e) {
            System.out.println("Error");
        }

    }
}

