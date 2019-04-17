package mongo;

import mongo.data.DataOperations;
import mongo.entity.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class DataOperationsTest {

    @Test
    public void insertTest(){
        DataOperations operations = new DataOperations("test","test");
        Data insert = new Data();
        insert.setText(UUID.randomUUID().toString());
        insert.setDate(new Date());
        insert.setNumber(new Random().nextInt());

        operations.insertData(insert);
        Assertions.assertNotNull(insert.getId());
        Data received = operations.getData(insert.getId());

        Assertions.assertNotNull(received);
        Assertions.assertEquals(insert.getText(),received.getText());
        Assertions.assertEquals(insert.getDate(),received.getDate());
        Assertions.assertEquals(insert.getId(),received.getId());
        Assertions.assertEquals(insert.getNumber(),received.getNumber());
    }

    @Test
    public void getByTextTest(){
        DataOperations operations = new DataOperations("test","test");

        String text1 = UUID.randomUUID().toString();
        List<Data> insertList = new ArrayList<>();
        int insertCount = 10;
        for (int i = 0; i <insertCount ; i++) {
            Data insert = new Data();
            insert.setNumber(i);
            insert.setDate(new Date());
            insert.setText(text1);
            insertList.add(insert);
            //operations.insertData(insert);
        }
        operations.insertMany(insertList);
        List<Data> receiveList = operations.getDataByText(text1);

        Assertions.assertNotNull(receiveList);
        Assertions.assertEquals(insertCount,receiveList.size());
    }
    @Test
    public void findInRangeTest(){
        DataOperations operations = new DataOperations("test","test");
        Data item1 = new Data();
        item1.setNumber(1000);
        operations.insertData(item1);

        Data item2 = new Data();
        item2.setNumber(5000);
        operations.insertData(item2);

        Data item3 = new Data();
        item3.setNumber(10000);
        operations.insertData(item3);

        Data item4 = new Data();
        item4.setNumber(15000);
        operations.insertData(item4);

        List<Data> resultList = operations.findInRange(5000,10000);

        Assertions.assertEquals(2,resultList.size());
    }
}