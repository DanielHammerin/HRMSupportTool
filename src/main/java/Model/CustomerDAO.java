package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Simon on 12/03/2016. 555
 */
public class CustomerDAO extends DAO<Customer> {

    public static final String TABLE_NAME = "Employment";
    public static final String COLUMN_COMPANYID = "CompanyID";
    public static final String COLUMN_PERSONID = "PersonID";
    public static final String COLUMN_EMPLOYMENTID = "EmploymentID";
    public static final String COLUMN_ROWID = "RowID";
    public static final String COLUMN_FIRSTNAME = "FirstName";
    public static final String COLUMN_LASTNAME = "LastName";

    public CustomerDAO(Connection conn) {
        super(conn);
    }

    public int getNbCustomer(){
        int nb =0;
        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            ).executeQuery(
                    "SELECT COUNT(*) FROM " + TABLE_NAME + ";"
            );
            if(result.first()){
                nb = result.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error during the count number process.");
            e.printStackTrace();
        }
        return nb;
    }

    public Customer create(Customer obj) {
        try {
            ResultSet result = this.connect.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT " + COLUMN_ROWID + " FROM " + TABLE_NAME + " ORDER BY " + COLUMN_ROWID + " DESC LIMIT 1;"
                    );
            if(result.first()){
                int id = result.getInt(COLUMN_ROWID)+1;
                PreparedStatement prepare = this.connect.prepareStatement(
                                "INSERT INTO " + TABLE_NAME + " (" + COLUMN_ROWID + ", " +
                                        COLUMN_COMPANYID + ", " + COLUMN_PERSONID + ", " +
                                        COLUMN_EMPLOYMENTID + ", " + COLUMN_FIRSTNAME + ", " +
                                        COLUMN_LASTNAME + ") VALUES(?, ?, ?, ?, ?, ?)"
                        );
                prepare.setInt(1, id);
                prepare.setInt(2, obj.getCompanyID());
                prepare.setInt(3, obj.getPersonID());
                prepare.setInt(4, obj.getEmploymentID());
                prepare.setString(5, obj.getFirstName());
                prepare.setString(6, obj.getLastName());

                prepare.executeUpdate();
                obj = this.find(id);

            }
        } catch (SQLException e) {
            System.err.println("Error during the create process.");
            e.printStackTrace();
        }
        return obj;
    }

    public Customer find(int id) {
        Customer customer = new Customer();
        try {
            ResultSet result = this.connect.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT * FROM " + TABLE_NAME +  " WHERE id = " + id
                    );
            if(result.first()) {
                customer = new Customer(
                        result.getInt(COLUMN_COMPANYID),
                        result.getInt(COLUMN_PERSONID),
                        result.getInt(COLUMN_EMPLOYMENTID),
                        id,
                        result.getString(COLUMN_FIRSTNAME),
                        result.getString(COLUMN_LASTNAME)
                );
            }
        } catch (SQLException e) {
            System.err.println("Error during the finds process.");
            e.printStackTrace();
        }
        return customer;

    }

    public Customer update(Customer obj) {
        // NOT NEEDED HERE
        /*
        try {
            this.connect.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                    "UPDATE " + TABLE_NAME + " SET firstName = '" + obj.getFirstName() + "'" +
                            " , lastName = '" + obj.getLastName() +
                            "' WHERE id = " + obj.getId()
            );

            obj = this.find(obj.getId());
        } catch (SQLException e) {
            System.err.println("Error during the update process.");
            e.printStackTrace();
        }
        */
        return obj;
    }

    public void delete(Customer obj) {
        try {

            this.connect.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                    "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ROWID + " = " + obj.getRowID()
            );

        } catch (SQLException e) {
            System.err.println("Error during the delete process.");
            e.printStackTrace();
        }
    }
}