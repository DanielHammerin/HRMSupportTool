package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Simon on 29/03/2016.
 */
public class SQLServerConnection {

    // Used only for test on a local computer
    // private static String urlWithWindowsAuthentification = "jdbc:sqlserver://SIMON-PC\\HRMINSTANCE;databaseName=bddvadin;integratedSecurity=true";
    // The URL connection string
    // private static String url = "jdbc:sqlserver://SIMON-PC\\HRMINSTANCE;DatabaseName=bddvadin;";
    private static Connection connect;
    private static String connectionString = "jdbc:sqlserver://hrmdatabase.mssql.somee.com;DatabaseName=hrmdatabase;User=HRMTEST223_SQLLogin_1;Password=anpkc9z788";
    private static String user = "HRMTEST223_SQLLogin_1";
    private static String password = "anpkc9z788";

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


    /**
     * Method to get a connection with this SQLServer Database.
     * Singleton class, create a connection if it's null, or return teh existing instance of it
     * @return a connection object for this database
     */
    public static Connection getInstance(){
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
