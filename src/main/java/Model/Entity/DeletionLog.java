package Model.Entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Abeer on 4/13/2016.
 */
public class DeletionLog implements Serializable {
    private String whoDelete;
    private String deletedId;
    private String deletedFirstName;
    private String deletedLastName;
    private Date date ;

    public void setDeletedLastName(String deletedLastName) {
        this.deletedLastName = deletedLastName;
    }

    public String getDeletedLastName() {

        return deletedLastName;
    }

    public void setDeletedFirstName(String deletedFirstName) {

        this.deletedFirstName = deletedFirstName;
    }

    public String getDeletedFirstName() {

        return deletedFirstName;
    }

    public DeletionLog (String whoDelete , String deletedId , String deletedFirstName, String deletedLastName, Date date){
        this.whoDelete = whoDelete;
        this.deletedId = deletedId;
        this.deletedFirstName = deletedFirstName;
        this.deletedLastName = deletedLastName;
        this.date  = date;



    }

    public String getWhoDelete() {
        return whoDelete;
    }

    public void setWhoDelete(String whoDelete) {
        this.whoDelete = whoDelete;
    }

    public String getDeletedId() {
        return deletedId;
    }

    public void setDeletedId(String whoWasDeleted) {
        this.deletedId = whoWasDeleted;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
