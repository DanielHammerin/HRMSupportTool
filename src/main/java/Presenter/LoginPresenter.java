package Presenter;

import Model.Entity.User;
import Model.UserModel;
import View.DatabaseSelection.DatabaseSelectionWindow;
import View.LoginUI.LoginWindow;

/**
 * Created by Hatem Houssein on 04-May-16.
 * A class that acts as the presenter for the Login window according to the MVP (Model-View-Presenter) pattern.
 */
public class LoginPresenter {

    private LoginWindow loginWindow;
    private UserModel loginDb;

    public LoginPresenter(LoginWindow currentWindow){
        loginWindow = currentWindow;
    }

    public void loginEmployee(String username, String password){
        loginDb = new UserModel();
        User user = loginDb.findUser(username, password);
        //If the login credentials are correct
        if(user!= null && !username.equals("") && !password.equals("")){
            loginWindow.showDatabaseSelectionWindow(user.isAdmin());
        }
        //If the login credentials are incorrect
        else{
            loginWindow.showLoginErrorMessage();
        }
    }
}
