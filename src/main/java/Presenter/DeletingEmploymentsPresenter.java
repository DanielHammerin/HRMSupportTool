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
    private String databaseName;
    private DeletionLogModel deletionLogModel;


    public DeletingEmploymentsPresenter(DeletingEmploymentsWindow currentWindow) throws IOException {
        view = currentWindow;
        deletionLogModel = new DeletionLogModel();
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



    }

