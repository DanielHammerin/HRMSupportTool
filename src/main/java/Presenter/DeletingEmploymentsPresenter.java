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
 * updated by Abeer adding createLog , readLog methods
 */
public class DeletingEmploymentsPresenter {

    private DeletingEmploymentsWindow view;
    private EmploymentDAO employmentDAO;
    private DeletionLogModel deletionLogModel;

    /**
     * Contructor the the Presenter
     * @param currentWindow the currentWindow calling the presenter (DeletingEmploymentsWindow)
     * @throws IOException
     */
    public DeletingEmploymentsPresenter(DeletingEmploymentsWindow currentWindow) throws IOException {
        view = currentWindow;
        deletionLogModel = new DeletionLogModel();
        // Create the DAO from the connection string registred in the session variable (set in the DBSelectionPresenter)
        System.out.println("DB name --> " + UI.getCurrent().getSession().getAttribute("databaseName"));
        System.out.println("Connection String --> " + UI.getCurrent().getSession().getAttribute("connectionString"));
        SQLServerConnection connection = new SQLServerConnection(UI.getCurrent().getSession().getAttribute("connectionString").toString());
        employmentDAO = new EmploymentDAO(connection.getInstance());

    }

    /**
     * Method to get the list of employments from the model to populate the grid
     * @return the list of employments of teh current DB
     */
    public List<Employment> getEmploymentsFromDAO() {
        if (employmentDAO == null) {
            return null;
        }
        return employmentDAO.getEmployments();
    }

    /**
     * Method to delete a employment from the model
     * @param employment the employment to delete
     */
    public void deleteEmploymentFromDAO(Employment employment) {
        if (employmentDAO == null) {

        } else if (employmentDAO.delete(employment)) {
            view.showSuccessNotification("Deletion is done successfully");
        }
        else{
            view.ShowErrorNotification("Error,  employments is not deleted");
        }
    }

    /**
     * Method to create a log
     * @param selectedEmployment the employment to register on the log
     */
    public void createLog(Employment selectedEmployment) {
        if (!deletionLogModel.createLog(String.valueOf(UI.getCurrent().getSession().getAttribute("user")), selectedEmployment, new Date())) {
            view.ShowErrorNotification("Error, deletion log is not saved!");
        }
    }

    /**
     * Method to read the deletion logs to populate the view
     * @return a collection of logs
     */
    public Collection readDeletionLog() {
        return deletionLogModel.readLogs();
    }
}

