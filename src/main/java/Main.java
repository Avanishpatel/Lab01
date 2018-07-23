
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {

        JsonArray jsonArray = ParseJSON.parseJSON();
        
        
        try {
            insertJSONtoDB(jsonArray);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        try {
            double maxValue = getMaxPriceBetweenRangeforStock("AMZN", "2018-06-22", "2018-06-25");

            System.out.println("Max price " +maxValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        try {
            double minValue = getMinPriceBetweenRangeforStock("AMZN", "2018-06-22", "2018-06-25");

            System.out.println("Min price " +minValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long totalVolume = totalValueOfStockInRange("2018-06-22","2018-06-25");
        System.out.println("Total Volume trade :"+ totalVolume);



    }
    


    public static double getMaxPriceBetweenRangeforStock(String stockName, String sdate, String edate) throws ClassNotFoundException, SQLException, ParseException {
        double maxValue = 0;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date sDateParsed = simpleDateFormat.parse(sdate);
        Date eDateParsed = simpleDateFormat.parse(edate);

        java.sql.Date startSqlDate = new java.sql.Date(sDateParsed.getTime());
        java.sql.Date endSqlDate = new java.sql.Date(eDateParsed.getTime());

        Connection connection = ConnectionManager.getConnection();

        Statement st = connection.createStatement();
        // execute the query and store into resultset
        ResultSet rs = st.executeQuery("select max(price) from stock where symbol=\'"+stockName+"\' and date between \'"+startSqlDate+"\' and \'"+endSqlDate+"\'");
        // move the pointer
        rs.next();

        // store the column value into string name
        maxValue = rs.getDouble("max(price)");
        // print the value
        // close the connection
        st.close();
        connection.close();


        return maxValue;
    }


    public static double getMinPriceBetweenRangeforStock(String stockName, String sdate, String edate) throws ClassNotFoundException, SQLException, ParseException {
        double minValue = 0;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date sDateParsed = simpleDateFormat.parse(sdate);
        Date eDateParsed = simpleDateFormat.parse(edate);

        java.sql.Date startSqlDate = new java.sql.Date(sDateParsed.getTime());
        java.sql.Date endSqlDate = new java.sql.Date(eDateParsed.getTime());

        Connection connection = ConnectionManager.getConnection();
        Statement st = connection.createStatement();
        // execute the query and store into resultset
        ResultSet rs = st.executeQuery("select min(price) from stock where symbol=\'"+stockName+"\' and date between \'"+startSqlDate+"\' and \'"+endSqlDate+"\'");
        // move the pointer
        rs.next();

        // store the column value into string name
        minValue = rs.getDouble("min(price)");
        // print the value
        // close the connection
        st.close();
        connection.close();


        return minValue;
    }

    public static long totalValueOfStockInRange(String sdate, String edate) throws ClassNotFoundException, SQLException, ParseException {

        long totalVolume = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date sDateParsed = simpleDateFormat.parse(sdate);
        Date eDateParsed = simpleDateFormat.parse(edate);

        java.sql.Date startSqlDate = new java.sql.Date(sDateParsed.getTime());
        java.sql.Date endSqlDate = new java.sql.Date(eDateParsed.getTime());
        
        Connection connection = ConnectionManager.getConnection();
        Statement st = connection.createStatement();
        // execute the query and store into resultset
        ResultSet rs = st.executeQuery("select sum(volume) from stock where date between \'"+startSqlDate+"\' and \'"+endSqlDate+"\'");
        // move the pointer
        rs.next();

        // store the column value into string name
        totalVolume = rs.getLong("sum(volume)");
        // print the value
        // close the connection
        st.close();
        connection.close();

        return totalVolume;
    }
}




