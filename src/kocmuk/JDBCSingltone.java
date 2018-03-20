package kocmuk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by admin on 20.03.2018.
 */
public class JDBCSingltone {
    private Connection connection=null;
    private void createConnection(){
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("Opened database successfully");

    }
    public Connection getConnection(){
        if(connection==null)createConnection();
        return connection;
    }
    public String closeConnection(){
        try {
            connection.close();
            return "Connection have been closeed";
        } catch (SQLException e) {
           return "Connection didn't founded";

        }
    }
}
