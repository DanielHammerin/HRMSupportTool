package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Simon on 29/03/2016.
 */
public class SQLServerConnection {

    // Used only for test on a local computer
    private static String urlWithWindowsAuthentification = "jdbc:sqlserver://SIMON-PC\\HRMINSTANCE;databaseName=bddvadin;integratedSecurity=true";
    // The URL connection string
    // private static String url = "jdbc:sqlserver://SIMON-PC\\HRMINSTANCE;DatabaseName=bddvadin;";
    private static Connection connect;
    private static String connecionString = "jdbc:sqlserver://hrmdatabase.mssql.somee.com;DatabaseName=hrmdatabase;";
    private static String user = "HRMTEST_SQLLogin_1";
    private static String password = "iwb6wm4m8k";

    /*
    // This is just a main method to test the connection in an easier way
    public static void main(String[] args) {
        System.err.println("Program start.");
        try {
            connect = DriverManager.getConnection(connecionString, user, password);
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
        if(connect == null){
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connect = DriverManager.getConnection(connecionString, user, password);
            } catch (SQLException e) {
                System.err.println("Error : Can't connect to database.");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connect;
    }
}
