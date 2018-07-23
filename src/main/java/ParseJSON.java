
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseJSON {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException, ParseException {

//        JsonArray jsonArray = parseJSON();
//        try {
//            insertJSONtoDB(jsonArray);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


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

    public static JsonArray parseJSON() throws IOException {

        InputStream stream = null;
        JsonReader jsonReader = null;

        URL url = new URL("https://bootcamp-training-files.cfapps.io/week1/week1-stocks.json");
        stream = url.openStream();

        jsonReader = Json.createReader(stream);

        JsonArray jsonArray = jsonReader.readArray();

//
//            for (Object o : jsonArray) {
//                System.out.println(o);
//            }

        return jsonArray;

    }

    public static void insertJSONtoDB(JsonArray jsonArray) throws ClassNotFoundException, SQLException, ParseException {



        // JDBC connection

        // load class
        Class.forName("com.mysql.jdbc.Driver");
        // create connection
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "tesla");
        // create statement

        Statement statement = con.createStatement();
        int count = 1;
//
//        for (int i = 0; i < jsonArray.size(); i++) {
//
//            value = statement.executeUpdate("insert into stock_data values (" + i + ",'" + jsonArray.get(i) + "')");
//
//        }


        for (int i = 0; i < jsonArray.size(); i++) {

            JsonObject object = jsonArray.getJsonObject(i);

//            String sql = "insert into new_table values (" + i + "," + object.get("symbol") + "," + object.get("price") + "," + object.get("volume") + "," + object.get("date")+";";
//            statement.executeUpdate(sql);

            String query = " insert into stock (id, symbol, price, volume, date)"
                    + " values (?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, i);
            preparedStmt.setString(2, object.getString("symbol"));
            preparedStmt.setDouble(3, object.getJsonNumber("price").doubleValue());
            preparedStmt.setInt(4, object.getInt("volume"));

            String dateString = object.getString("date");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = simpleDateFormat.parse(dateString.substring(0,19));
            //Date date = format.parse(object.getString("date").substring(0,19));
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            preparedStmt.setDate (5, sqlDate);



            // execute the preparedstatement
            preparedStmt.execute();
            count++;
        }


        //System.out.println(count + " numbers of row/s inserted.");

        // close the connection
        statement.close();
        con.close();

    }


    public static double getMaxPriceBetweenRangeforStock(String stockName, String sdate, String edate) throws ClassNotFoundException, SQLException, ParseException {
        double maxValue = 0;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date sDateParsed = simpleDateFormat.parse(sdate);
        Date eDateParsed = simpleDateFormat.parse(edate);

        java.sql.Date startSqlDate = new java.sql.Date(sDateParsed.getTime());
        java.sql.Date endSqlDate = new java.sql.Date(eDateParsed.getTime());
        // load class
        Class.forName("com.mysql.jdbc.Driver");
        // create connection
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","tesla");
        // create statement
        Statement st = con.createStatement();
        // execute the query and store into resultset
        ResultSet rs = st.executeQuery("select max(price) from stock where symbol=\'"+stockName+"\' and date between \'"+startSqlDate+"\' and \'"+endSqlDate+"\'");
        // move the pointer
        rs.next();

        // store the column value into string name
        maxValue = rs.getDouble("max(price)");
        // print the value
        // close the connection
        st.close();
        con.close();


        return maxValue;
    }


    public static double getMinPriceBetweenRangeforStock(String stockName, String sdate, String edate) throws ClassNotFoundException, SQLException, ParseException {
        double minValue = 0;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date sDateParsed = simpleDateFormat.parse(sdate);
        Date eDateParsed = simpleDateFormat.parse(edate);

        java.sql.Date startSqlDate = new java.sql.Date(sDateParsed.getTime());
        java.sql.Date endSqlDate = new java.sql.Date(eDateParsed.getTime());
        // load class
        Class.forName("com.mysql.jdbc.Driver");
        // create connection
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","tesla");
        // create statement
        Statement st = con.createStatement();
        // execute the query and store into resultset
        ResultSet rs = st.executeQuery("select min(price) from stock where symbol=\'"+stockName+"\' and date between \'"+startSqlDate+"\' and \'"+endSqlDate+"\'");
        // move the pointer
        rs.next();

        // store the column value into string name
        minValue = rs.getDouble("min(price)");
        // print the value
        // close the connection
        st.close();
        con.close();


        return minValue;
    }

    public static long totalValueOfStockInRange(String sdate, String edate) throws ClassNotFoundException, SQLException, ParseException {

        long totalVolume = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date sDateParsed = simpleDateFormat.parse(sdate);
        Date eDateParsed = simpleDateFormat.parse(edate);

        java.sql.Date startSqlDate = new java.sql.Date(sDateParsed.getTime());
        java.sql.Date endSqlDate = new java.sql.Date(eDateParsed.getTime());
        // load class
        Class.forName("com.mysql.jdbc.Driver");
        // create connection
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","tesla");
        // create statement
        Statement st = con.createStatement();
        // execute the query and store into resultset
        ResultSet rs = st.executeQuery("select sum(volume) from stock where date between \'"+startSqlDate+"\' and \'"+endSqlDate+"\'");
        // move the pointer
        rs.next();

        // store the column value into string name
        totalVolume = rs.getLong("sum(volume)");
        // print the value
        // close the connection
        st.close();
        con.close();

        return totalVolume;
    }
}




