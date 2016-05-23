package Model.Entity;

/**
 * Created by Simon on 12/03/2016.
 * Class Employment representing the employment concept, a employee in the HRM databases
 */
public class Employment {
	private String companyID;
	private String personID;
	private String employmentID;
	private int rowID;
	private String firstName;
	private String lastName;
	private String startDate;
	private String endDate;

	public Employment(String companyID, String personID, String employmentID, int rowID, String firstName, String lastName, String startDate, String endDate) {
		this.companyID = companyID;
		this.personID = personID;
		this.employmentID = employmentID;
		this.rowID = rowID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Employment() {
		// void
	}

	/* Getters */
	public String getCompanyID () {return companyID;}
	public String getPersonID () {return personID;}
	public String getEmploymentID () {return employmentID;}
	public int getRowID () {return rowID;}
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}
	public String getStartDate() {return startDate;}
	public String getEndDate() {return endDate;}

	/* Setters */
	public void setCompanyID(String companyID) {this.companyID = companyID;}
	public void setPersonID(String personID) {this.personID = personID;}
	public void setEmploymentID(String employmentID) {this.employmentID = employmentID;}
	public void setRowID(int rowID) {this.rowID = rowID;}
	public void setFirstName(String firstName) {this.firstName = firstName;}
	public void setLastName(String lastName) {this.lastName = lastName;};
	public void setStartDate(String startDate) {this.startDate = startDate;};
	public void setEndDate(String endDate) {this.endDate = endDate;};

	@Override
	public String toString() {
		return String.format("Customer[id=%d, FirstName='%s', LastName='%s']", rowID,
				firstName, lastName);
	}
}
