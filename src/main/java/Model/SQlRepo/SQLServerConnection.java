package Model.SQlRepo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Simon on 29/03/2016.
 */
public class SQLServerConnection {


    //private static String connectionString = "jdbc:sqlserver://hrmdatabase.mssql.somee.com;DatabaseName=hrmdatabase;User=HRMTEST223_SQLLogin_1;Password=anpkc9z788";
    private String connectionString;
    private Connection connect;

    /*
    // This is just a main method to test the connection in an easier way
    public static void main(String[] args) {
        System.err.println("Program start.");
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connect = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            System.err.println("Error : Can't connect to database.");
            e.printStackTrace();
        } catch (Exception ec) {
            System.err.println("Error : Unknown error.");
            ec.printStackTrace();
        }
        if (connect != null) {
            System.out.println("OK !");
        } else {
            System.out.println("Not OK !");
        }
        System.out.println("Program end.");
    }
    */

    public SQLServerConnection(String connectionString) {
        this.connectionString = connectionString;
    }


    /**
     * Method to get a connection with this SQLServer Database.
     * Create a connection if it's null, or return teh existing instance of it
     * @return a connection object for this database
     */
    public Connection getInstance(){
        try {
            if(connect == null || connect.isClosed() ){
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    connect = DriverManager.getConnection(connectionString);
                } catch (SQLException e) {
                    System.err.println("Error : Can't connect to database.");
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connect;
    }
}
