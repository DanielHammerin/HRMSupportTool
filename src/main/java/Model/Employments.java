package Model;

/**
 * Created by Hatem on 3/19/2016.
 */

public class Employments {
    private int staffId;
    private String firstName;
    private String lastName;

    public Employments(int staffId, String firstName, String lastName){
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
