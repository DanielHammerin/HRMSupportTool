package Presenter;

import Model.DatabaseSelectionModel;
import View.DatabaseSelection.DatabaseSelectionWindow;
import View.DefaultUI.DeletingEmploymentsWindow;

/**
 * Created by Abeer on 5/10/2016.
 *  class that acts as the presenter for the Database selection window according to the MVP (Model-View-Presenter) pattern.
 * Modified by Simon on 5/10/2016
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
  public void handleSelectedDB(Object selectedDB) {
      if(selectedDB==null){
          DBselectionWindow.showDBSelectionErrorMessage();
      } else {
          DBselectionWindow.getDeletingEmploymentsWindow(selectedDB);
      }
  }
}