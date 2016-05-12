package Model;

import java.util.ArrayList;

/**
 * Created by Abeer on 4/22/2016.
 */
public class UserTest {

    public static void main(String[] args) {
        UserModel userModel = new UserModel();

        // create users
        System.out.println("********create users********:");
        System.out.println("is user created: "+userModel.createUser("Abeer", "Alkhars", "cc223de",
                "123123", "null", false));
      //  System.out.println("is user created: "+userModel.createUser("firstName", "LastnName", "bb123de",
             //   "123123", "null", false));

     System.out.println("******** user list********:");
      ArrayList<User> users = (ArrayList<User>) userModel.findUsers();
	 for(User user : users)
		if(user!= null)
	    	System.out.println(user.getFirstName() +" "+ user.getLastName()+" "+user.getId()+" "+user.getUsername()+" "+ user.getEmail()+" "+ user.isAdmin());

       // System.out.println("******** find a user by username & password ********:");
    //     User user =  userModel.findUser("cc223de","123123");
      //  System.out.println(user.getFirstName() +" "+ user.getLastName()+" "+user.getId()+" "+user.getUsername()+" "+ user.getEmail()+" "+ user.isAdmin());

       // System.out.println("******** Delete a user by username & password ********:");
      //  System.out.println("is user deleted:" +userModel.deleteUser("aa123de", "123123"));

      // System.out.println("******** update a user  ********:");
      // System.out.println("is user updated:" +userModel.updateUser( ));

      //  ArrayList<User> users = (ArrayList<User>) userModel.findUsers();
       // for(User user : users)
        //	if(user!= null)
       // System.out.println(user.getFirstName() +" "+ user.getLastName()+" "+user.getId()+" "+user.getUsername()+" "+user.getPassword()+" " + user.getEmail()+" "+ user.isAdmin());


    }
}
