import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Main
{
    private static final String path = "src/main/resources/mongo.csv";
    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(path));
        List<String> students = new ArrayList<>();
        for (String s : lines) {
            StringBuilder builder = new StringBuilder(s);
            boolean inQuotes = false;

            for (int currentIndex = 0; currentIndex < builder.length(); currentIndex++) {
                char currentChar = builder.charAt(currentIndex);
                if (currentChar == '\"') {
                    inQuotes = !inQuotes;
                }
                if (currentChar == ',' && inQuotes) {
                    builder.setCharAt(currentIndex, '/');
                }
            }
            String line = builder.toString();
            students.add(line);
        }

        // подключение к Mongo
        MongoClient mongoClient = new MongoClient("127.0.0.1" , 27017);

        // create DataBase
        MongoDatabase database = mongoClient.getDatabase("students");

        // create collection of documents
        MongoCollection<Document> collection = database.getCollection("students");
        collection.drop();

        students.forEach(s -> {
            String[] data = s.split(",");
            // nested object
            Document coursesObject = new Document();
            String[] courses = data[2].replace("\"", "").replace("/", ", ").split(",");
            List<String> coursesList = new ArrayList<>();
            coursesList.addAll(Arrays.asList(courses));
            // works with List, not Array
            coursesObject.put("courses", coursesList);
            Document document = new Document()
                    .append("name", data[0])
                    .append("age", Integer.parseInt(data[1]))
                    .append("education", coursesObject);
            collection.insertOne(document);
        });

        // total count of students in database
        System.out.println("Total students count in database " + collection.countDocuments());

        System.out.println("**************************");

        // students older 40 years
        BsonDocument query = BsonDocument.parse("{age: {$gt: 40}}");
        System.out.println("Number of students older than 40 years are " + collection.countDocuments(query));

        query = BsonDocument.parse("{age: 1}");
        collection.find().sort(query).limit(1)
            .forEach((Consumer<Document>) document -> System.out.println("Name the youngest student is " + document.get("name")));

        //list of courses of the oldest student
        query = BsonDocument.parse("{age: -1}");
        collection.find().sort(query).limit(1)
                .forEach((Consumer<Document>) d -> {
                    Document document = (Document) d.get("education");
                    System.out.println("The oldest student : " + d.get("name") + " courses: " + document.get("courses"));
                });
    }
}
