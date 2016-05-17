package Presenter;

import Model.Entity.Employment;
import Model.SQlRepo.EmploymentDAO;
import Model.SQlRepo.SQLServerConnection;
import View.DefaultUI.DeletingEmploymentsWindow;
import com.vaadin.ui.UI;

import java.util.List;

/**
 * Created by Hatem on 10-May-16.
 * A class that acts as the presenter for the Deleting Employments window according to the MVP (Model-View-Presenter) pattern.
 */
public class DeletingEmploymentsPresenter {
    private DeletingEmploymentsWindow view;
    private EmploymentDAO employmentDAO;
    private String databaseName;


    public DeletingEmploymentsPresenter(DeletingEmploymentsWindow currentWindow){
        view = currentWindow;
        // Create the DAO from the connection string registred in the session variable (set in the DBSelectionPresenter)
        System.out.println("DB name --> " + UI.getCurrent().getSession().getAttribute("databaseName"));
        System.out.println("Connection String --> " + UI.getCurrent().getSession().getAttribute("connectionString"));
        SQLServerConnection connection = new SQLServerConnection(UI.getCurrent().getSession().getAttribute("connectionString").toString());
        employmentDAO = new EmploymentDAO(connection.getInstance());
    }

    public List<Employment> getEmploymentsFromDAO(){
        if (employmentDAO == null) {
            return null;
        }
        return employmentDAO.getEmployments();
    }

    public void deleteEmploymentFromDAO(Employment employment){
        if (employmentDAO == null) {
            return;
        }
        employmentDAO.delete(employment);
    }
}
