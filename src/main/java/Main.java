
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {

//        JsonArray jsonArray = ParseJSON.parseJSON();
//
//        InsertData.insertJSONtoDB(jsonArray);

        double maxValue = ExecuteQuery.getMaxPriceBetweenRangeforStock("AMZN", "2018-06-22", "2018-06-25");
        System.out.println("Max value :" + maxValue);

//
//        try {
//            double minValue = getMinPriceBetweenRangeforStock("AMZN", "2018-06-22", "2018-06-25");
//
//            System.out.println("Min price " +minValue);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        long totalVolume = totalValueOfStockInRange("2018-06-22","2018-06-25");
//        System.out.println("Total Volume trade :"+ totalVolume);



    }
    

}




