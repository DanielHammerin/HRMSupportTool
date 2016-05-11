package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon on 10/05/2016.
 * Model to get list of databases from a textfile (here LocalDatabase/databases.txt
 */
public class DatabaseSelectionModel {

    private List<Database> listDatabases;
    private final static String DATABASES_FILE = "LocalDatabase\\databases";
    private final static String SEPARATOR = ":::";

    public static void main(String[] args) {
        System.err.println("Program start.");
        DatabaseSelectionModel dsm = new DatabaseSelectionModel();
        System.out.println("Program end.");
    }

    public DatabaseSelectionModel(){
        listDatabases = new ArrayList<Database>();
        readDatabasesFromFile();
    }

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

    public List<String> getListDatabaseNames(){
        List<String> toReturn = new ArrayList<String>();
        for (Database db : listDatabases) {
            toReturn.add(db.databaseName);
        }
        return toReturn;
    }

    public String getConnectionStringFromDatabaseName(String databaseName) {
        String toReturn = "";
        for (Database db : listDatabases) {
            if (db.getDatabaseName().equals(databaseName)) {
                toReturn = db.getConnectionString();
            }
        }
        return toReturn;
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




