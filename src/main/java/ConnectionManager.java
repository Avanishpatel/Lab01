import java.sql.*;

public class ConnectionManager {

    public static Connection getConnection() {


        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            // create connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "tesla");
        } catch (ClassNotFoundException e) {

            System.out.println("Drive not found.");
            e.printStackTrace();
        } catch (SQLException e) {

            System.out.println("Database access error occur.");
            e.printStackTrace();
        }

        return connection;
    }


}
