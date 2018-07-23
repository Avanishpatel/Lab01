import javax.json.JsonArray;
import javax.json.JsonObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertData {

    public static void insertJSONtoDB(JsonArray jsonArray) throws SQLException, ParseException {

        Connection connection = ConnectionManager.getConnection();

        Statement statement = connection.createStatement();
        int count = 1;

        for (int i = 0; i < jsonArray.size(); i++) {

            JsonObject object = jsonArray.getJsonObject(i);


            String query = " INSERT INTO stock (id, symbol, price, volume, date)"
                    + " values (?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = connection.prepareStatement(query);
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

            preparedStmt.execute();
            count++;
        }

        System.out.println(count + " Number of row's inserted.");


        statement.close();
        connection.close();

    }
}
