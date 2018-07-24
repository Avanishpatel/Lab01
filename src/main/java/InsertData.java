import javax.json.JsonArray;
import javax.json.JsonObject;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertData {

    public static void insertJSONtoDB(JsonArray jsonArray) {

        Connection connection = ConnectionManager.getConnection();

        Statement statement = null;
        try {
            statement = connection.createStatement();
            int count = 1;

            for (int i = 0; i < jsonArray.size(); i++) {

                JsonObject object = jsonArray.getJsonObject(i);


                String query = " INSERT INTO stock (id, symbol, price, volume, date)"
                        + " values (?, ?, ?, ?, ?)";

                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setInt(1, i);

                preparedStmt.setString(2, object.getString("symbol"));

                preparedStmt.setDouble(3, object.getJsonNumber("price").doubleValue());
                preparedStmt.setInt(4, object.getInt("volume"));
//
                String dateString = object.getString("date");
//
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//                Date date = simpleDateFormat.parse(dateString.substring(0,19));
//                //Date date = format.parse(object.getString("date").substring(0,19));

                java.util.Date date = simpleDateFormat.parse(dateString);
                Timestamp timestamp = new Timestamp(date.getTime());

                preparedStmt.setTimestamp(5, timestamp);


                preparedStmt.execute();
                count++;
            }


            System.out.println(count + " Number of row's inserted.");


        } catch (SQLException e) {
            System.out.println("SQL Exception occur.");
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {

            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }
}
