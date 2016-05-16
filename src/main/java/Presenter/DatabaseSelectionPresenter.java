package Presenter;

import Model.DatabaseSelectionModel;
import View.DatabaseSelection.DatabaseSelectionWindow;
import View.DefaultUI.DeletingEmploymentsWindow;

/**
 * Created by Abeer on 5/10/2016.
 *  class that acts as the presenter for the Database selection window according to the MVP (Model-View-Presenter) pattern.
 * Modified by Simon on 5/10/2016 : add database in the DBSelctionWindow from teh textfile
 * Modified by Simon on 5/16/2016 : add a test on the DBSelectionWindow and create DAO from connection string
 */


public class DatabaseSelectionPresenter {

    private DatabaseSelectionModel DBselectionModel;
    private DatabaseSelectionWindow DBselectionWindow ;

    public DatabaseSelectionPresenter(DatabaseSelectionWindow DBselectionWindow){
        this.DBselectionWindow = DBselectionWindow;
        DBselectionModel = new DatabaseSelectionModel();
        // Add each database in the file to the window
        for (String dbName : DBselectionModel.getListDatabaseNames()) {
            DBselectionWindow.addDatabaseToSelection(dbName);
        }
    }

    /**
     * Method to handle when the user select a database
     * @param selectedDB the selected database (String)
     */
    public void handleSelectedDB(Object selectedDB) {
        if(selectedDB==null){
            DBselectionWindow.showDBSelectionErrorMessage();
        } else {
            // Create a Session Variable containing the connection string to create the DAO from it
            //DBselectionWindow.getUI().getSession().setAttribute("databaseName", selectedDB.toString());
            //DBselectionWindow.getUI().getSession().setAttribute("connectionStrring", DBselectionModel.getConnectionStringFromDatabaseName(selectedDB.toString()));
            // Then we naviguate to the next Window
            DBselectionWindow.showDeletingEmploymentsWindow(selectedDB.toString(), DBselectionModel.getConnectionStringFromDatabaseName(selectedDB.toString()));
        }
    }
}