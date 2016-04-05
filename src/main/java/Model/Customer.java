package Model;

public class Customer {

	private Long id;

	private int CompanyID;
	private int PersonID;
	private int EmploymentID;
	private int RowID;
	private String FirstName;
	private String LastName;

	public Customer(int CompanyID, int PersonID, int EmploymentID, int RowID, String FirstName, String LastName) {
		this.CompanyID = CompanyID;
		this.PersonID = PersonID;
		this.EmploymentID = EmploymentID;
		this.RowID = RowID;
		this.FirstName = FirstName;
		this.LastName = LastName;
	}

	public Customer() {

	}

	/* Getters */
	public int getCompanyID () {return CompanyID;}
	public int getPersonID () {return PersonID;}
	public int getEmploymentID () {return EmploymentID;}
	public int getRowID () {return RowID;}
	public String getFirstName() {return FirstName;}
	public String getLastName() {return LastName;}

	/* Setters */

	public void setCompanyID(int CompanyID) {this.CompanyID = CompanyID;}
	public void setPersonID(int PersonID) {this.PersonID = PersonID;}
	public void setEmploymentID(int EmploymentID) {this.EmploymentID = EmploymentID;}
	public void setRowID(int RowID) {this.RowID = RowID;}

	@Override
	public String toString() {
		return String.format("Customer[id=%d, firstName='%s', lastName='%s']", RowID,
				FirstName, LastName);
	}

}
