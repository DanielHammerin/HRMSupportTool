package Presenter;

import Model.Entity.User;
import Model.UserModel;
import View.LoginUI.LoginWindow;
import org.apache.commons.codec.digest.Crypt;

/**
 * Created by Hatem Houssein on 04-May-16.
 * A class that acts as the presenter for the Login window according to the MVP (Model-View-Presenter) pattern.
 */
public class LoginPresenter {

    private LoginWindow loginWindow;
    private UserModel loginDb;

    /**
     * Constructor of the LoginPresenter
     * @param currentWindow the currentWindow calling the presenter (LoginWindow)
     */
    public LoginPresenter(LoginWindow currentWindow){
        loginWindow = currentWindow;
    }

    /**
     * Method to log in the user
     * @param username the username
     * @param password the user password
     */
    public void loginUser(String username, String password){
        loginDb = new UserModel();
        User user = loginDb.findUserByUserName(username);
        //If the login credentials are correct
        if(user!= null && !username.equals("") && authenticate(password, user.getPassword())){
            loginWindow.showDatabaseSelectionWindow(user.isAdmin());
        } else { //If the login credentials are incorrect
            loginWindow.showLoginErrorMessage();
        }
    }

    /**
     * Method to check if a entered password match with the hashed stored password
     * @param enteredPassword the entered password
     * @param storedPassword teh stored password
     * @return true if the two password match, false otherwise
     */
    private boolean authenticate(String enteredPassword, String storedPassword){
        return storedPassword.equals(Crypt.crypt(enteredPassword, storedPassword));
    }
}
