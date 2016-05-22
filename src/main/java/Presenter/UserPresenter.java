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

    public UserPresenter(UsersWindow userView){
        this.userView= userView;
        userModel = new UserModel();

    }

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
    public void deleteUser(String username, String password){
        if(userModel.deleteUser(username,password))       {
            userView.showSuccessNotification("User is deleted successfully");
            userView.updateUsersGrid();

        }else{
            userView.showErrorNotification("Error, User is not deleted");
        }


    }
    public void updateUser(int id, String firstName, String lastName ,String userName, String password,
                           String email , boolean isAdmin){
        {
            if ( userModel.updateUser(id,firstName, lastName, userName, password,
                    email, isAdmin))
            {
                userView.showSuccessNotification("User is updated successfully");
                userView.updateUsersGrid();

            }else{
                userView.showErrorNotification("Error, User is not updated");
            }
        }

    }
    public List readUsers(){
     return userModel.findUsers();
    }
}
