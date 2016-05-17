package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Simon on 10/05/2016.
 * Model to get list of databases from a textfile (here LocalDatabase/databases.txt
 */
public class DatabaseSelectionModel {

    private List<Database> listDatabases;
    private final static String DATABASES_FILE = "LocalDatabase" + File.separator + "databases";
    /* Separator used in the text file to separate teh database name from its connection string */
    private final static String SEPARATOR = ":::";

    public DatabaseSelectionModel(){
        listDatabases = new ArrayList<Database>();
        readDatabasesFromFile();
    }

    /**
     * Method to read teh text file and add the databases in the list
     */
    public void readDatabasesFromFile(){
        String currentLine;
        try {
            BufferedReader br = new BufferedReader(new FileReader(DATABASES_FILE));
            while ((currentLine = br.readLine()) != null) {
                String[] parts = currentLine.split(SEPARATOR);
                listDatabases.add(new Database(parts[0], parts[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to get all the database names registred
     * @return a list of string corresponding to the database names
     */
    public List<String> getListDatabaseNames(){
        List<String> toReturn = new ArrayList<String>();
        for (Database db : listDatabases) {
            toReturn.add(db.databaseName);
        }
        return toReturn;
    }

    /**
     * Method to get the connection string from the database name
     * @param databaseName the database name
     * @return the connection string corresponding to the database name
     */
    public String getConnectionStringFromDatabaseName(String databaseName) {
        String toReturn = "";
        for (Database db : listDatabases) {
            if (db.getDatabaseName().equals(databaseName)) {
                toReturn = db.getConnectionString();
            }
        }
        return toReturn;
    }

    /**
     * Method to test the connection to a database
     * @param databaseName the name of the database to test
     * @return true if the program can connect to the database, false otherwise
     */
    public boolean testDatabaseConnection(String databaseName) {
        String connectionString = getConnectionStringFromDatabaseName(databaseName);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connect = DriverManager.getConnection(connectionString);
            if (connect != null) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error : Can't connect to database.");
            e.printStackTrace();
            return false;
        } catch (Exception ec) {
            System.err.println("Error : Unknown error.");
            ec.printStackTrace();
            return false;
        }
    }


    /* Private class representing a database (no connection here, just properties) */
    private class Database {

        private String databaseName;
        private String connectionString;

        public Database(String databaseName, String connectionString) {
            this.databaseName = databaseName;
            this.connectionString = connectionString;
        }

        /* Getters */
        public String getDatabaseName() { return databaseName; }
        public String getConnectionString() { return connectionString; }


    }
}




