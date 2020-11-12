package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static Connection connection;

    static {

         final String DRIVER_NAME="com.mysql.jdbc.Driver";
         final String SERVERNAME="localhost";
         final String PORT="3306";
         final String DATABASE="bookstore";
         final String URL="jdbc:mysql://"+SERVERNAME+':'+PORT+'/'+DATABASE;
         final String USERNAME="root";
         final String PASSWORD="";
        try {
            Class.forName(DRIVER_NAME);
            connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("Connextion established");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static Connection getConnection() {
        return connection;
    }
}
