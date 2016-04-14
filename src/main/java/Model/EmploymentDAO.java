package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon on 12/03/2016.
 */
public class EmploymentDAO extends DAO<Employment> {

    public static final String TABLE_NAME = "Employment";
    public static final String COLUMN_COMPANYID = "CompanyID";
    public static final String COLUMN_PERSONID = "PersonID";
    public static final String COLUMN_EMPLOYMENTID = "EmploymentID";
    public static final String COLUMN_ROWID = "RowID";
    public static final String COLUMN_FIRSTNAME = "FirstName";
    public static final String COLUMN_LASTNAME = "LastName";

    public EmploymentDAO(Connection conn) {
        super(conn);
    }

    public List<Employment> getEmployments() {
        List<Employment> list = new ArrayList<Employment>();

        try {
            Statement statement = this.connect.createStatement();
            String query = "SELECT * FROM " + TABLE_NAME + ";";
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                Employment employment =  new Employment(
                        resultSet.getString(COLUMN_COMPANYID),
                        resultSet.getString(COLUMN_PERSONID),
                        resultSet.getString(COLUMN_EMPLOYMENTID),
                        resultSet.getInt(COLUMN_ROWID),
                        resultSet.getString(COLUMN_FIRSTNAME),
                        resultSet.getString(COLUMN_LASTNAME)
                );
                list.add(employment);
            }
        } catch (SQLException e) {
            System.err.println("Error during the get employments process.");
            e.printStackTrace();
        }
        return list;
    }

    public Employment create(Employment obj) {
        try {
            String query = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_ROWID + ", " +
                    COLUMN_COMPANYID + ", " + COLUMN_PERSONID + ", " +
                    COLUMN_EMPLOYMENTID + ", " + COLUMN_FIRSTNAME + ", " +
                    COLUMN_LASTNAME + ") VALUES(?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = this.connect.prepareStatement(query);
            statement.setInt(1, obj.getRowID());
            statement.setString(2, obj.getCompanyID());
            statement.setString(3, obj.getPersonID());
            statement.setString(4, obj.getEmploymentID());
            statement.setString(5, obj.getFirstName());
            statement.setString(6, obj.getLastName());

            int rowCreated = statement.executeUpdate();
            if (rowCreated != 1) {
                throw new Exception();
            }
        } catch (SQLException e) {
            System.err.println("Error during the create process.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error during the create process. Not a valid number of rows created");
            e.printStackTrace();
        }

        return obj;
    }

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
                        resultSet.getString(COLUMN_LASTNAME)
                );
            }
        } catch (SQLException e) {
            System.err.println("Error during the finds process.");
            e.printStackTrace();
        }
        return employment;
    }

    public Employment update(Employment obj) {
        try {
            String query = "UPDATE " + TABLE_NAME + " SET " + COLUMN_COMPANYID  + " = '" + obj.getCompanyID() + "'" +
                    " , " + COLUMN_PERSONID  + " = '" + obj.getCompanyID() + "'" +
                    " , " + COLUMN_EMPLOYMENTID  + " = '" + obj.getEmploymentID() + "'" +
                    " , " + COLUMN_FIRSTNAME  + " = '" + obj.getFirstName() + "'" +
                    " , " + COLUMN_LASTNAME  + " = '" + obj.getLastName() +
                    "' WHERE " + COLUMN_ROWID + " = " + obj.getRowID();
            PreparedStatement statement = this.connect.prepareStatement(query);
            int rowUpdated = statement.executeUpdate();
            if (rowUpdated != 1) {
                throw new Exception();
            }
        } catch (SQLException e) {
            System.err.println("Error during the update process.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error during the update process. Not a valid number of rows updated");
            e.printStackTrace();
        }
        return obj;
    }

    public void delete(Employment obj) {
        try {
            String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ROWID + " = " + obj.getRowID();
            PreparedStatement statement = this.connect.prepareStatement(query);
            int rowDeleted = statement.executeUpdate();
            if (rowDeleted != 1) {
                throw new Exception();
            }
        } catch (SQLException e) {
            System.err.println("Error during the delete process.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error during the delete process. Not a valid number of rows deleted");
            e.printStackTrace();
        }
    }
}