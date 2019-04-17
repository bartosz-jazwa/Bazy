package mongo.data;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import mongo.entity.Data;
import mongo.utils.MongoUtils;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DataOperations {

    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public DataOperations(String databaseName, String collectionName) {
        database = MongoUtils.getInstance().getClient().getDatabase(databaseName);

        collection = database.getCollection(collectionName);
    }

    public void insertData(Data data){
        Document doc = new Document("text",data.getText())
                .append("number",data.getNumber())
                .append("date", data.getDate());

        collection.insertOne(doc);
        data.setId(doc.getObjectId("_id").toString());
    }
    public void insertMany(List<Data> list){
        List<Document> insertList =list.stream()
                .map(l -> new Document("text",l.getText())
                        .append("number",l.getNumber())
                        .append("date",l.getDate()))
                .collect(Collectors.toList());

        collection.insertMany(insertList);
    }

    public Data getData(String id){
        FindIterable<Document> result = collection.find(Filters.eq("_id",new ObjectId(id)));
        if (result.iterator().hasNext()){
            Document doc = result.iterator().next();
            Data data = new Data(doc.getObjectId("_id").toString(),doc.getString("text"),doc.getInteger("number"),doc.getDate("date"));

            return data;
        }
        return null;
    }

    public List<Data> getDataByText(String text){
        FindIterable<Document> result = collection.find(Filters.eq("text",text));
        List<Data> resultList= new ArrayList<>();
        MongoCursor<Document> iterator = result.iterator();

        while (iterator.hasNext()){
            Document doc = iterator.next();
            resultList.add(new Data(doc.getObjectId("_id").toString(),doc.getString("text"),doc.getInteger("number"),doc.getDate("date")));
        }
        return resultList;
    }
    public List<Data> findInRange(Integer lower, Integer upper){
        FindIterable<Document> result = collection.find(Filters.and(Filters.gte("number",lower),Filters.lte("number",upper)));
        List<Data> resultList= new ArrayList<>();
        MongoCursor<Document> iterator = result.iterator();

        resultList = StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator,Spliterator.ORDERED),false)
                .map(doc -> new Data(doc.getObjectId("_id").toString(),doc.getString("text"),doc.getInteger("number"),doc.getDate("date")))
                .collect(Collectors.toList());

        /*while (iterator.hasNext()){
            Document doc = iterator.next();
            resultList.add(new Data(doc.getObjectId("_id").toString(),doc.getString("text"),doc.getInteger("number"),doc.getDate("date")));
        }*/
        return resultList;
    }

}
