package Presenter;

import View.DefaultUI.DeletingEmploymentsWindow;

/**
 * Created by Hatem on 10-May-16.
 * A class that acts as the presenter for the Deleting Employments window according to the MVP (Model-View-Presenter) pattern.
 */
public class DeletingEmploymentsPresenter {
    private DeletingEmploymentsWindow view;


    public DeletingEmploymentsPresenter(DeletingEmploymentsWindow currentWindow){
        view = currentWindow;
    }

    public void deleteEmployment(Object employment){

    }
}
