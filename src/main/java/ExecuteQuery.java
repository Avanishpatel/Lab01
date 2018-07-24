import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExecuteQuery {


    public static double getMaxPriceOfStock(String stockName, String date) {
        double maxValue = 0;
        Statement st = null;
        Connection connection = null;
        try {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date sDateParsed = simpleDateFormat.parse(date);
            java.sql.Date startSqlDate = new java.sql.Date(sDateParsed.getTime());

            connection = ConnectionManager.getConnection();

            st = connection.createStatement();
            // execute the query and store into resultset
            ResultSet rs = st.executeQuery("SELECT MAX(price) FROM stock WHERE symbol=\'" + stockName + "\' AND date BETWEEN \'" + startSqlDate + " 08:30:00\' AND \'" + date + " 16:30:00\'");
            // move the pointer
            rs.next();

            // store the column value into string name
            maxValue = rs.getDouble("MAX(price)");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return maxValue;
    }


    public static double getMinPriceofStock(String stockName, String sdate) {
        double minValue = 0;

        Statement st = null;
        Connection connection = null;

        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date sDateParsed = simpleDateFormat.parse(sdate);

            java.sql.Date startSqlDate = new java.sql.Date(sDateParsed.getTime());

            connection = ConnectionManager.getConnection();
            st = connection.createStatement();
            // execute the query and store into resultset
            ResultSet rs = st.executeQuery("SELECT MIN(price) FROM stock WHERE symbol=\'" + stockName + "\' AND date BETWEEN \'" + startSqlDate + " 08:30:00\' AND \'" + startSqlDate+ " 16:30:00\'");
            // move the pointer
            rs.next();

            // store the column value into string name
            minValue = rs.getDouble("MIN(price)");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        finally {
            try {
                st.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }


        return minValue;
    }

    public static long totalVolumeOfTrade(String stockName, String date){

        long totalVolume = 0;
        Statement st = null;
        Connection connection = null;

        try{

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date sDateParsed = simpleDateFormat.parse(date);
            java.sql.Date date1 = new java.sql.Date(sDateParsed.getTime());

            connection = ConnectionManager.getConnection();
            st = connection.createStatement();
            // execute the query and store into resultset
            ResultSet rs = st.executeQuery("SELECT SUM(volume) FROM stock WHERE symbol=\'"+stockName+"\' AND date BETWEEN \'" + date1 + " 08:30:00\' AND \'" + date1 + " 16:30:00\'");
            // move the pointer
            rs.next();

            // store the column value into string name
            totalVolume = rs.getLong("SUM(volume)");
            // print the value
            // close the connection

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        finally {
            try {
                st.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return totalVolume;
    }
}
