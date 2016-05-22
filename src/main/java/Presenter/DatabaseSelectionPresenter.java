package Presenter;

import Model.DatabaseSelectionModel;
import View.DatabaseSelection.DatabaseSelectionWindow;
import View.DefaultUI.DeletingEmploymentsWindow;

/**
 * Created by Abeer on 5/10/2016.
 * Class that acts as the presenter for the Database selection window according to the MVP (Model-View-Presenter) pattern.
 * Modified by Simon on 5/10/2016 : add database in the DBSelctionWindow from teh textfile
 * Modified by Simon on 5/16/2016 : add a test on the DBSelectionWindow and create DAO from connection string
 * Modified by Simon on 5/22/2016 : adding a feature to handle when the connection to the DB is not possible
 */


public class DatabaseSelectionPresenter {

    private DatabaseSelectionModel DBselectionModel;
    private DatabaseSelectionWindow DBselectionWindow ;

    /**
     * Contructor of the Presenter
     * @param DBselectionWindow the currentWindow calling the presenter (DatabaseSelectionWindow)
     */
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
            DBselectionWindow.ShowErrorNotification("No Database selected");
        } else {
            // Test if teh connection is possible
            if (DBselectionModel.testDatabaseConnection(selectedDB.toString())){
                DBselectionWindow.showDeletingEmploymentsWindow(selectedDB.toString(), DBselectionModel.getConnectionStringFromDatabaseName(selectedDB.toString()));
            } else {
                DBselectionWindow.ShowErrorNotification("Can't connect to " + selectedDB.toString());
            }
        }
    }
}