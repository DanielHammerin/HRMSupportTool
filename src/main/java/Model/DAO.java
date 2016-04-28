package Model;

import java.sql.Connection;

/**
 * Abstract class of DAO for generic objetcs T
 * Created by Simon on 11/03/2016.
 */

public abstract class DAO<T> {

    // Connection to the database
    protected Connection connect = null;

    /**
     * Constructor for the DAO
     * @param conn the connection object to connect DAO to the database
     */
    public DAO(Connection conn){
        this.connect = conn;
    }

    /**
     * To create an object
     * @param obj the object to create
     * @return the T object
     */
    public abstract T create(T obj);

    /**
     * To delete an object
     * @param obj the object to delete
     */
    public abstract void delete(T obj);

    /**
     * To update an object
     * @param obj the object to update
     * @return the T object
     */
    public abstract T update(T obj);

    /**
     * To find a object with its ID
     * @param id the ID of the object
     * @return T the object found (null if not)
     */
    public abstract T find(int id);
}