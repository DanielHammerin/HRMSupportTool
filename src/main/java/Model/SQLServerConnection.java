package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Simon on 29/03/2016.
 */
public class SQLServerConnection {



    private static String urlWithWindowsAuthentification = "jdbc:sqlserver://SIMON-PC\\HRMINSTANCE;databaseName=bddvadin;integratedSecurity=true";

    /**
     * URL de connection
     */
    private static String url = "jdbc:sqlserver://SIMON-PC\\HRMINSTANCE;DatabaseName=bddvadin;";

    /**
     * Nom du user
     */
    private static String user = "hrmconnection";
    /**
     * Mot de passe du user
     */
    private static String password = "hrmpassword";
    /**
     * Objet Connection
     */
    private static Connection connect;

    /**
     * Méthode qui va nous retourner notre instance
     * et la créer si elle n'existe pas...
     * @return
     */

    /* @TODO Connection test program
    public static void main(String[] args) {
        System.err.println("Program start.");
        try {
            connect = DriverManager.getConnection(url, user, password);
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
    }*/



    public static Connection getInstance(){
        if(connect == null){
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connect = DriverManager.getConnection(url, user, password);
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
