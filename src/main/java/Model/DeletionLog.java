package Model;

import java.util.Date;

/**
 * Created by Hussain on 4/13/2016.
 */
public class DeletionLog {
    private String whoDelete;
    private String whoWasDeleted;
    private Date date ;
    public DeletionLog (String whoDelete , String whoWasDeleted , Date date){
        this.whoDelete = whoDelete;
        this.whoWasDeleted = whoWasDeleted;
        this.date  = date;

    }

    public String getWhoDelete() {
        return whoDelete;
    }

    public void setWhoDelete(String whoDelete) {
        this.whoDelete = whoDelete;
    }

    public String getWhoWasDeleted() {
        return whoWasDeleted;
    }

    public void setWhoWasDeleted(String whoWasDeleted) {
        this.whoWasDeleted = whoWasDeleted;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
