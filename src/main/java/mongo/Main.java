package mongo;

import mongo.data.DataOperations;
import mongo.entity.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        DataOperations dataOperations = new DataOperations("test", "kolekcja");


        long startTime = System.currentTimeMillis();
        List<Data> insertData = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Data data = new Data();
            data.setDate(new Date());
            data.setNumber(1);
            data.setText("bartek");
            insertData.add(data);

        }
        dataOperations.insertMany(insertData);
        System.out.println(System.currentTimeMillis() - startTime);

    }
}
