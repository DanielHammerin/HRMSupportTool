package Model.SQlRepo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Simon on 29/03/2016.
 */
public class SQLServerConnection {

    private String connectionString;
    private Connection connect;

    /**
     * Constructor of the SQLServerConnection
     * @param connectionString the connection string to create the connection
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
