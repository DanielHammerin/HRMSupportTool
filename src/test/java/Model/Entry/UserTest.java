package Model.Entry;

import org.junit.Assert;
import org.junit.Test;
import Model.Entity.User;
/**
 * Created by Markus Lyconhold on 17/05/16.
 *
 * The purpose of this class is to test the class User and achieve as much
 * code coverage as possible.
 */
public class UserTest {

    private int id = 12342;
    private String firstName = "Markus";
    private String lastName = "Lyconhold";
    private String userName = "ma231";
    private String passWord = "asd123";
    private String email = "sportgo@gmail.com";
    private boolean isAdmin = true;

    private User user = new User(id, firstName, lastName, userName, passWord
    , email, isAdmin);

    @Test
    public void getIdTest(){


    Assert.assertEquals(id, user.getId());
    }
    @Test
    public void getFirstNameTest(){

        Assert.assertTrue(user.getFirstName().equals(firstName));

    }
    @Test
    public void getLastNameTest(){

        Assert.assertTrue(user.getLastName().equals(lastName));

    }
    @Test
    public void getUserNameTest(){

        Assert.assertTrue(user.getUsername().equals(userName));

    }
    @Test
    public void getPasswordTest(){

        Assert.assertTrue(user.getPassword().equals(passWord));

    }
    @Test
    public void getEmailTest(){

        Assert.assertTrue(user.getEmail().equals(email));

    }
   @Test
    public void isAdmitTest(){

        Assert.assertNotNull(user.isAdmin());

    }

}
