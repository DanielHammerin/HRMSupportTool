package Presenter;

import Model.DeletionLogModel;
import Model.Entity.Employment;
import Model.SQlRepo.EmploymentDAO;
import Model.SQlRepo.SQLServerConnection;
import View.DefaultUI.DeletingEmploymentsWindow;
import com.vaadin.ui.UI;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Hatem on 10-May-16.
 * A class that acts as the presenter for the Adding, Deleting ,updating Employments window according to
 * the MVP (Model-View-Presenter) pattern.
 * updated by Abeer adding addNewEmployment, createLog , readLog,  updateEmployment methods
 */
public class EmploymentsPresenter {
    private DeletingEmploymentsWindow view;
    private EmploymentDAO employmentDAO;
    private String databaseName;
    private DeletionLogModel deletionLogModel;
    private boolean isUserAdmin ;


    public EmploymentsPresenter(DeletingEmploymentsWindow currentWindow,boolean isUserAdmin) throws IOException {
        view = currentWindow;
        deletionLogModel = new DeletionLogModel();
        this.isUserAdmin = isUserAdmin;
        // Create the DAO from the connection string registred in the session variable (set in the DBSelectionPresenter)
        System.out.println("DB name --> " + UI.getCurrent().getSession().getAttribute("databaseName"));
        System.out.println("Connection String --> " + UI.getCurrent().getSession().getAttribute("connectionString"));
        SQLServerConnection connection = new SQLServerConnection(UI.getCurrent().getSession().getAttribute("connectionString").toString());
        employmentDAO = new EmploymentDAO(connection.getInstance());

    }

    public List<Employment> getEmploymentsFromDAO() {
        if (employmentDAO == null) {
            return null;
        }
        return employmentDAO.getEmployments();
    }

    public void setUserAdmin(boolean userAdmin) {
        isUserAdmin = userAdmin;
    }

    public boolean isUserAdmin() {

        return isUserAdmin;
    }

    public void deleteEmploymentFromDAO(Employment employment) {
        if (employmentDAO == null) {
            return;
        } else if (employmentDAO.delete(employment)) {
            view.showSuccessNotification("Deletion is done successfully");
        }
        else{
            view.ShowErrorNotification("Error,  employments is not deleted");
        }
    }

    public void createLog(Employment selectedEmployment) {
        if (!deletionLogModel.createLog(String.valueOf(UI.getCurrent().getSession().getAttribute("user")), selectedEmployment, new Date())) {

            view.ShowErrorNotification("Error, deletion log is not saved!");
            return;
        }

    }
    public Collection readDeletionLog() {
        return deletionLogModel.readLogs();
    }
    public void addNewEmployment(String companyID, String personID, String employmentID, int rowID,
                                 String firstName, String lastName, String startDate, String endDate){

        Employment newEmployment = new Employment(companyID,personID,employmentID,rowID,firstName,
                lastName,startDate,endDate);
 /*
        there is an error when calling employmentDAO create method
         */
     //  if(employmentDAO.create(newEmployment))
      //    {
        //   view.showSuccessNotification("Employment is created successfully ");
   //  }
      // else
      //   view.ShowErrorNotification("Error, employment is not created");

    }


    public void updateEmployment(Employment employment, String companyID, String personID, String employmentID,
                                 int rowID, String firstName, String lastName) {
        employment.setCompanyID(companyID);
        employment.setPersonID(personID);
        employment.setEmploymentID(employmentID);
        employment.setRowID(rowID);
        employment.setFirstName(firstName);
        employment.setLastName(lastName);

        /*
        there is an error when calling employmentDAO  update method
         */
       // if (employmentDAO.update(employment)) {
       //     view.showSuccessNotification("Employment is updated successfully");}
      //      else{
       //     view.showSuccessNotification("Error, employment is not updated");
    //    }
        }

    }

