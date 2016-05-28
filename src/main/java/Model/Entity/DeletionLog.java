package Model.Entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Abeer on 4/13/2016.
 *  Class DeletionLog representing the deletion log entity
 */
public class DeletionLog implements Serializable {
    private String whoDelete;
    private String deletedId;
    private String deletedFirstName;
    private String deletedLastName;
    private Date date ;

    /**
     * Method to set the last name of the deleted employment
     * @param deletedLastName the last name
     */
    public void setDeletedLastName(String deletedLastName) {
        this.deletedLastName = deletedLastName;
    }

    /**
     * Method to get last name of the deleted employment
     * @return the last name
     */
    public String getDeletedLastName() {
        return deletedLastName;
    }

    /**
     * Method to set the first name of the deleted employment
     * @param deletedFirstName the first name
     */
    public void setDeletedFirstName(String deletedFirstName) {
        this.deletedFirstName = deletedFirstName;
    }

    /**
     * Method to get the first name of the deleted employment
     * @return the first name
     */
    public String getDeletedFirstName() {
        return deletedFirstName;
    }

    /**
     * Constructor of the deletion log
     * @param whoDelete the name of the user tha tdeleted this employment
     * @param deletedId the id of the deleted employment
     * @param deletedFirstName the first name of the deleted employment
     * @param deletedLastName the last name of the deleted employment
     * @param date the date of the deletion
     */
    public DeletionLog (String whoDelete , String deletedId , String deletedFirstName, String deletedLastName, Date date){
        this.whoDelete = whoDelete;
        this.deletedId = deletedId;
        this.deletedFirstName = deletedFirstName;
        this.deletedLastName = deletedLastName;
        this.date  = date;
    }

    /**
     * Method to get the name of the user that deleted the employment
     * @return the name of the user
     */
    public String getWhoDelete() {
        return whoDelete;
    }

    /**
     * Method to set the name of the user that deleted the employment
     * @param whoDelete the name of the user
     */
    public void setWhoDelete(String whoDelete) {
        this.whoDelete = whoDelete;
    }

    /**
     * Method to get the id of the deleted employment
     * @return the id of the employment
     */
    public String getDeletedId() {
        return deletedId;
    }

    /**
     * Method to set the id of teh deleted employment
     * @param id the id of the employment
     */
    public void setDeletedId(String id) {
        this.deletedId = id;
    }

    /**
     * Method to get the date of the deletion
     * @return the date of the deletion
     */
    public Date getDate() {
        return date;
    }

    /**
     * Method to set the date of the deletion
     * @param date the date of the deletion
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
