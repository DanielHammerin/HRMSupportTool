package Model;

import java.sql.Connection;

/**
 * Created by Simon on 11/03/2016.
 */

public abstract class DAO<T> {

    protected Connection connect = null;
    public DAO(Connection conn){
        this.connect = conn;
    }

    /**
     * To create
     * @param obj
     * @return boolean
     */
    public abstract T create(T obj);

    /**
     * To delete
     * @param obj
     * @return boolean
     */
    public abstract void delete(T obj);

    /**
     * To update
     * @param obj
     * @return boolean
     */
    public abstract T update(T obj);

    /**
     * To find informations
     * @param id
     * @return T
     */
    public abstract T find(int id);
}