package Presenter;

import Model.UserModel;
import View.UserUI.UsersWindow;

import javax.validation.constraints.Future;
import java.util.List;

/**
 * Created by Abeer on 5/19/2016.
 * class that acts as the presenter for the users window according to the MVP (Model-View-Presenter) pattern.
 */
public class UserPresenter {
    private UsersWindow userView ;
    private UserModel userModel ;

    /**
     * Constructor of the UserPresenter
     * @param userView the currentWindow calling the presenter (DeletingEmploymentsWindow)
     */
    public UserPresenter(UsersWindow userView){
        this.userView= userView;
        userModel = new UserModel();

    }

    /**
     * Method to add a new user
     * @param firstName the user first name
     * @param lastName the user last name
     * @param userName the user name
     * @param password the user password
     * @param email the user mail
     * @param isAdmin a boolean true if the user is a admin, false otherwise
     */
   public void addNewUser(String firstName, String lastName ,String userName, String password,
                          String email , boolean isAdmin) {
       if (userModel.createUser(firstName, lastName, userName, password,
               email, isAdmin)) {
           userView.updateUsersGrid();
           userView.showSuccessNotification("User is created successfully");
       } else {
           userView.showErrorNotification("Error, User is not created");
       }
   }

    /**
     * Method to delete a user
     * @param username the username
     * @param password the userpassword
     */
    public void deleteUser(String username, String password){
        if(userModel.deleteUser(username,password)){
            userView.showSuccessNotification("User is deleted successfully");
            userView.updateUsersGrid();
        } else {
            userView.showErrorNotification("Error, User is not deleted");
        }
    }

    /**
     * A method to update a user
     * @param firstName the user first name
     * @param lastName the user last name
     * @param userName the user name
     * @param password the user password
     * @param email the user mail
     * @param isAdmin a boolean true if the user is a admin, false otherwise
     */
    public void updateUser(int id, String firstName, String lastName ,String userName, String password,
                           String email , boolean isAdmin){
        if ( userModel.updateUser(id,firstName, lastName, userName, password,
                email, isAdmin)) {
            userView.showSuccessNotification("User is updated successfully");
            userView.updateUsersGrid();
        } else {
            userView.showErrorNotification("Error, User is not updated");
        }
    }

    /**
     * Method to get the list of users
     * @return the list of users
     */
    public List readUsers(){
     return userModel.findUsers();
    }
}
