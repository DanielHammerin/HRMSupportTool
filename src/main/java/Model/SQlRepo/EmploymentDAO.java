package Model.SQlRepo;

import Model.Entity.Employment;
import Model.SQlRepo.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon on 12/03/2016.
 * EmploymentDAO is the DAO for employments in HRM databases
 */
public class EmploymentDAO extends DAO<Employment> {

    // Attributes maps with the Table and columns title in the database
    public static final String TABLE_NAME = "Employment";
    public static final String COLUMN_COMPANYID = "CompanyID";
    public static final String COLUMN_PERSONID = "PersonID";
    public static final String COLUMN_EMPLOYMENTID = "EmploymentID";
    public static final String COLUMN_ROWID = "RowID";
    public static final String COLUMN_FIRSTNAME = "FirstName";
    public static final String COLUMN_LASTNAME = "LastName";
    public static final String COLUMN_STARTDATE = "StartDate";
    public static final String COLUMN_ENDDATE = "EndDate";

    /**
     * Constructor implementing the super class constructor
     * @param conn the connection object to connect to the databse
     */
    public EmploymentDAO(Connection conn) {
        super(conn);
    }

    /**
     * Method to get all the employments that are in the database in a list
     * @return the list of employments
     */
    public List<Employment> getEmployments() {
        List<Employment> list = new ArrayList<Employment>();
        try {
            Statement statement = this.connect.createStatement();
            String query = "SELECT * FROM " + TABLE_NAME + ";";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                // For each employment row, create an employment object and add it to the list
                Employment employment =  new Employment(
                        resultSet.getString(COLUMN_COMPANYID),
                        resultSet.getString(COLUMN_PERSONID),
                        resultSet.getString(COLUMN_EMPLOYMENTID),
                        resultSet.getInt(COLUMN_ROWID),
                        resultSet.getString(COLUMN_FIRSTNAME),
                        resultSet.getString(COLUMN_LASTNAME),
                        resultSet.getString(COLUMN_STARTDATE),
                        resultSet.getString(COLUMN_ENDDATE)
                );
                list.add(employment);
            }
        } catch (SQLException e) {
            System.err.println("Error during the get employments process.");
            e.printStackTrace();
        }
        return list;
    }

    /**
     * To create an employment row in the database (not used here)
     * @param obj the employment to create in the database
     * @return true if creation has succeeded, false otherwise
     */
    public boolean create(Employment obj) {
        try {
            String query = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_ROWID + ", " +
                    COLUMN_COMPANYID + ", " + COLUMN_PERSONID + ", " +
                    COLUMN_EMPLOYMENTID + ", " + COLUMN_FIRSTNAME + ", " +
                    COLUMN_LASTNAME + ", " + COLUMN_STARTDATE + ", " + COLUMN_ENDDATE + ") VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = this.connect.prepareStatement(query);
            statement.setInt(1, obj.getRowID());
            statement.setString(2, obj.getCompanyID());
            statement.setString(3, obj.getPersonID());
            statement.setString(4, obj.getEmploymentID());
            statement.setString(5, obj.getFirstName());
            statement.setString(6, obj.getLastName());
            statement.setString(7, obj.getStartDate());
            statement.setString(8, obj.getEndDate());

            int rowCreated = statement.executeUpdate();
            if (rowCreated != 1) {
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error during the create process.");
           e.printStackTrace();
           return false;
        } catch (Exception e) {
            System.err.println("Error during the create process. Not a valid number of rows created");
            e.printStackTrace();
           return false;
        }
        return true;
    }

    /**
     * To find a employment with its (row)ID
     * @param id the (row)ID of the employment
     * @return the employment found (null if not)
     */
    public Employment find(int id) {
        Employment employment = new Employment();
        try {
            Statement statement = this.connect.createStatement();
            String query = "SELECT * FROM " + TABLE_NAME +  " WHERE " + COLUMN_ROWID + " = " + id;
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                employment = new Employment(
                        resultSet.getString(COLUMN_COMPANYID),
                        resultSet.getString(COLUMN_PERSONID),
                        resultSet.getString(COLUMN_EMPLOYMENTID),
                        id,
                        resultSet.getString(COLUMN_FIRSTNAME),
                        resultSet.getString(COLUMN_LASTNAME),
                        resultSet.getString(COLUMN_STARTDATE),
                        resultSet.getString(COLUMN_ENDDATE)
                );
            }
        } catch (SQLException e) {
            System.err.println("Error during the finds process.");
            e.printStackTrace();
        }
        return employment;
    }

    /**
     * To update an employment row in the database (not used here)
     * @param obj the employment to update in the database
     * @return true if updaten has succeeded, false otherwise
     */
    public boolean update(Employment obj) {
        try {
            String query = "UPDATE " + TABLE_NAME + " SET " + COLUMN_COMPANYID  + " = '" + obj.getCompanyID() + "'" +
                    " , " + COLUMN_PERSONID  + " = '" + obj.getCompanyID() + "'" +
                    " , " + COLUMN_EMPLOYMENTID  + " = '" + obj.getEmploymentID() + "'" +
                    " , " + COLUMN_FIRSTNAME  + " = '" + obj.getFirstName() + "'" +
                    " , " + COLUMN_LASTNAME  + " = '" + obj.getLastName() + "'" +
                    " , " + COLUMN_STARTDATE  + " = '" + obj.getStartDate() + "'" +
                    " , " + COLUMN_ENDDATE  + " = '" + obj.getEndDate() +
                    "' WHERE " + COLUMN_ROWID + " = " + obj.getRowID();
            PreparedStatement statement = this.connect.prepareStatement(query);
            int rowUpdated = statement.executeUpdate();
            if (rowUpdated != 1) {
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error during the update process.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error during the update process. Not a valid number of rows updated");
            e.printStackTrace();
        }
        return true;
    }

    /**
     * To delete an employment row in the database
     * @param obj the object to delete in the database
     * @return true if deletion has succeeded, false otherwise
     */
    public boolean delete(Employment obj) {
        try {
            String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ROWID + " = " + obj.getRowID();
            PreparedStatement statement = this.connect.prepareStatement(query);
            int rowDeleted = statement.executeUpdate();
            if (rowDeleted != 1) {
               return false;
            }
        } catch (SQLException e) {
            System.err.println("Error during the delete process.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error during the delete process. Not a valid number of rows deleted");
            e.printStackTrace();
        }
        return true;
    }
}