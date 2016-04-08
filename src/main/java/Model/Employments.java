package Model;

/**
 * Created by Hatem on 3/19/2016.
 */

public class Employments {
    private String companyId;
    private String personId;
    private String employmentId;
    private String firstName;
    private String lastName;

    public Employments(String companyId, String personId, String employmentId, String firstName, String lastName){
        this.companyId = companyId;
        this.personId = personId;
        this. employmentId = employmentId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
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

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getEmploymentId() {
        return employmentId;
    }

    public void setEmploymentId(String employmentId) {
        this.employmentId = employmentId;
    }
}
