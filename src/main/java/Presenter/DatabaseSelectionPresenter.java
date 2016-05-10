package Presenter;

import View.DatabaseSelection.DatabaseSelectionWindow;

/**
 * Created by Abeer on 5/10/2016.
 */
public class DatabaseSelectionPresenter {
    private DatabaseSelectionWindow DBselectionWindow ;
   // we should add DatabaseSelectionModel in the constructor;
    public DatabaseSelectionPresenter(DatabaseSelectionWindow DBselectionWindow){
        this.DBselectionWindow = DBselectionWindow;
    }
  public void handleSelectedDB(Object selectedDB) {
      if(selectedDB==null){
          DBselectionWindow.showDBSelectionErrorMessage();


      }
  }
}