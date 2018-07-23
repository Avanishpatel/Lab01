import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExecuteQuery {


    public static double getMaxPriceBetweenRangeforStock(String stockName, String sdate, String edate) {
        double maxValue = 0;
        Statement st = null;
        Connection connection = null;
        try {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date sDateParsed = simpleDateFormat.parse(sdate);
            Date eDateParsed = simpleDateFormat.parse(edate);

            java.sql.Date startSqlDate = new java.sql.Date(sDateParsed.getTime());
            java.sql.Date endSqlDate = new java.sql.Date(eDateParsed.getTime());

            connection = ConnectionManager.getConnection();

            st = connection.createStatement();
            // execute the query and store into resultset
            ResultSet rs = st.executeQuery("select max(price) from stock where symbol=\'" + stockName + "\' and date between \'" + startSqlDate + "\' and \'" + endSqlDate + "\'");
            // move the pointer
            rs.next();

            // store the column value into string name
            maxValue = rs.getDouble("max(price)");

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


    public static double getMinPriceBetweenRangeforStock(String stockName, String sdate, String edate) {
        double minValue = 0;

        Statement st = null;
        Connection connection = null;

        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date sDateParsed = simpleDateFormat.parse(sdate);
            Date eDateParsed = simpleDateFormat.parse(edate);

            java.sql.Date startSqlDate = new java.sql.Date(sDateParsed.getTime());
            java.sql.Date endSqlDate = new java.sql.Date(eDateParsed.getTime());

            connection = ConnectionManager.getConnection();
            st = connection.createStatement();
            // execute the query and store into resultset
            ResultSet rs = st.executeQuery("select min(price) from stock where symbol=\'" + stockName + "\' and date between \'" + startSqlDate + "\' and \'" + endSqlDate + "\'");
            // move the pointer
            rs.next();

            // store the column value into string name
            minValue = rs.getDouble("min(price)");

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

    public static long totalValueOfStockInRange(String sdate, String edate){

        long totalVolume = 0;
        Statement st = null;
        Connection connection = null;

        try{

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date sDateParsed = simpleDateFormat.parse(sdate);
            Date eDateParsed = simpleDateFormat.parse(edate);

            java.sql.Date startSqlDate = new java.sql.Date(sDateParsed.getTime());
            java.sql.Date endSqlDate = new java.sql.Date(eDateParsed.getTime());

            connection = ConnectionManager.getConnection();
            st = connection.createStatement();
            // execute the query and store into resultset
            ResultSet rs = st.executeQuery("select sum(volume) from stock where date between \'" + startSqlDate + "\' and \'" + endSqlDate + "\'");
            // move the pointer
            rs.next();

            // store the column value into string name
            totalVolume = rs.getLong("sum(volume)");
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
