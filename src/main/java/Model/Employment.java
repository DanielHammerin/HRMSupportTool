package Model;

public class Employment {

	//private Long id;

	private String CompanyID;
	private String PersonID;
	private String EmploymentID;
	private int RowID;
	private String FirstName;
	private String LastName;

	public Employment(String CompanyID, String PersonID, String EmploymentID, int RowID, String FirstName, String LastName) {
		this.CompanyID = CompanyID;
		this.PersonID = PersonID;
		this.EmploymentID = EmploymentID;
		this.RowID = RowID;
		this.FirstName = FirstName;
		this.LastName = LastName;
	}

	public Employment() {

	}

	/* Getters */
	public String getCompanyID () {return CompanyID;}
	public String getPersonID () {return PersonID;}
	public String getEmploymentID () {return EmploymentID;}
	public int getRowID () {return RowID;}
	public String getFirstName() {return FirstName;}
	public String getLastName() {return LastName;}

	/* Setters */

	public void setCompanyID(String CompanyID) {this.CompanyID = CompanyID;}
	public void setPersonID(String PersonID) {this.PersonID = PersonID;}
	public void setEmploymentID(String EmploymentID) {this.EmploymentID = EmploymentID;}
	public void setRowID(int RowID) {this.RowID = RowID;}
	public void setFirstName(String FirstName) {this.FirstName = FirstName;}
	public void setLastName(String LastName) {this.LastName = LastName;};


	@Override
	public String toString() {
		return String.format("Customer[id=%d, FirstName='%s', LastName='%s']", RowID,
				FirstName, LastName);
	}

}
