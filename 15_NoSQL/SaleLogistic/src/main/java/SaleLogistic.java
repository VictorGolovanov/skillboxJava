import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import org.bson.Document;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;

import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

public class SaleLogistic {
    private static volatile SaleLogistic instance;
    private final String HOST;
    private final int PORT;

    private static final String collectionStores = "stores";
    private static final String collectionProducts = "products";

    private static final String commandAddStore = "add_store";
    private static final String commandAddProduct = "add_product";
    private static final String commandExhibitProduct = "exhibit";
    private static final String commandList = "list";
    private static final String commandExit = "exit";
    private static final int COMPARISON_PRICE = 100;
    private static final String StoreFieldStore = "Store";
    private static final String StoreFieldProducts = "Products";
    private static final String ProductFieldProduct = "Product";
    private static final String ProductFieldPrice = "Price";

    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> stores;
    private MongoCollection<Document> products;

    // Singleton? Why not?
    private SaleLogistic(String HOST, int PORT, String DB) {
        this.HOST = HOST;
        this.PORT = PORT;
        client = new MongoClient(HOST, PORT);
        database = client.getDatabase(DB);
        stores = database.getCollection(collectionStores);
        products = database.getCollection(collectionProducts);
    }

    public static SaleLogistic getInstance(String HOST, int PORT, String DB){
        SaleLogistic result = instance;
        if(result != null){
            return result;
        }
        synchronized (SaleLogistic.class){
            if(instance == null){
                instance = new SaleLogistic(HOST, PORT, DB);
            }
            return instance;
        }
    }

    public boolean isCommandExit(String command) {
        return (command.contains(commandExit) && (command.trim().split(" ").length == 1));
    }

    public boolean isCommandAddStore(String command) {
        return (command.contains(commandAddStore) && (command.trim().split(" ").length == 2));
    }

    public boolean isCommandAddProduct(String command) {
        return (command.contains(commandAddProduct) && (command.trim().split(" ").length == 3));
    }

    public boolean isCommandExhibit(String command) {
        return (command.contains(commandExhibitProduct) && (command.trim().split(" ").length == 3));
    }

    public boolean isCommandList(String command) {
        return (command.contains(commandList) && (command.trim().split(" ").length == 1));
    }

    public void addStore(String command) {
        command = command.replaceAll(commandAddStore, "").trim();
        if(!checkExistenceStore(command)){
            Document store = new Document().append(StoreFieldStore, command)
                    .append(StoreFieldProducts, new ArrayList<String>());
            stores.insertOne(store);
            System.out.println("Store " + command + " added!");
        }
        else{
            System.err.println("This store has already added!");
        }
    }

    public void addProduct(String command) {
        if(isCommandAddProduct(command)){
            int productName = 0;
            int productPrice = 1;
            String[] commandArray = command.replaceAll(commandAddProduct, "").trim().split(" ");
            if(!checkExistenceProduct(commandArray[productName])){
                Document product = new Document().append(ProductFieldProduct, commandArray[productName])
                        .append(ProductFieldPrice, Integer.valueOf(commandArray[productPrice]));
                products.insertOne(product);
                System.out.println("Product " + commandArray[productName] + " by price " + commandArray[productPrice] + " added");
            }
            else{
                System.err.println("This product has already added!");
            }
        }
    }

    public void exhibitProductToStore(String command) {
        int productName = 0;
        int storeName = 1;
        String[] commandArray = command.replaceAll(commandExhibitProduct, "").trim().split(" ");
        if (checkExistenceProduct(commandArray[productName]) && checkExistenceStore(commandArray[storeName])) {
            Document store = stores.find(eq("Store", commandArray[storeName])).first();
            // get product from database
            ArrayList<String> productsList = (ArrayList<String>) store.get("Products");

            // add only unique product
            if (!productsList.contains(commandArray[productName])) {
                productsList.add(commandArray[productName]);
            }
            stores.findOneAndUpdate(eq("Store", commandArray[storeName]), new Document("$set", new Document("Products", productsList)));
            System.out.println("Product " + commandArray[productName] + " added to store " + commandArray[storeName]);
        }
        else{
            System.err.println("No such product or store!");
        }
    }

    private boolean checkExistenceProduct(String productName) {
        if (products.find(eq(ProductFieldProduct, productName)).first() != null) {
            return true;
        }
        return false;
    }

    private boolean checkExistenceStore (String storeName) {
        if (stores.find(eq(StoreFieldStore, storeName)).first() != null) {
            return true;
        }
        return false;
    }

    public void listStatistics() {
        AggregateIterable<Document> statistics = stores
                .aggregate(Arrays.asList(
                Aggregates.lookup(products.getNamespace().getCollectionName(), "Products", "Product", "Products"),
                Aggregates.unwind("$Products"),
                Aggregates.group("$Store", Accumulators.sum("Total products amount", 1),
                        Accumulators.avg("AvgPrice", "$Products.Price"),
                        Accumulators.max("MaxPrice", "$Products.Price"),
                        Accumulators.min("MinPrice", "$Products.Price"))
        ));
        AggregateIterable<Document> numberProductsCheaperComparisonPrice = stores
                .aggregate(Arrays.asList(
                Aggregates.lookup(products.getNamespace().getCollectionName(), "Products", "Product", "Products"),
                Aggregates.unwind("$Products"),
                Aggregates.match(lt("Products.Price", COMPARISON_PRICE)),
                Aggregates.group("$Store", Accumulators.sum("ProductLowPriceSum", 1))
        ));
        System.out.println("Statistics for each store: ");

        statistics.forEach((Consumer<Document>) doc -> {
            System.out.println(doc.toJson(JsonWriterSettings.builder().outputMode(JsonMode.SHELL).build()));
        });
        numberProductsCheaperComparisonPrice.forEach((Consumer<Document>) doc -> {
            System.out.println(doc.toJson(JsonWriterSettings.builder().outputMode(JsonMode.SHELL).build()));
        });
    }



}