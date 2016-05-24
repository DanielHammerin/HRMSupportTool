package Model;

import Model.Entity.User;
import Model.FileRepo.IRepository;
import Model.FileRepo.UserFileRepository;
import org.apache.commons.codec.digest.Crypt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abeer on 4/21/2016.
 * userModel class for logic opertions between User and UserFileRepository
 */
public class UserModel {

    private final IRepository repo;

    /**
     * Constructor of the user model
     */
    public UserModel() {
        super();
        this.repo = new UserFileRepository();
        createUser("Thorvald", "Gudjonsson", "Admin", "123123", "email", true);
    }

    /**
     * Method to create a user
     * @param firstName the user first name
     * @param lastName the user last name
     * @param userName the user name
     * @param password the user password
     * @param email the user mail
     * @param isAdmin a boolean true if the user is a admin, false otherwise
     * @return true if the creation has suceeded, false otherwise
     */
    public boolean createUser(String firstName, String lastName ,String userName, String password,
                              String email , boolean isAdmin) {

        User foundUser = findUserByUserName(userName);
        if (foundUser != null) {
            return false;
        }
        String cryptedPassword = Crypt.crypt(password);

        User member = new User(getNextUserId(), firstName, lastName, userName, cryptedPassword, email, isAdmin);
        return repo.createUser(member);
    }

    /**
     * Method to get a user
     * @param userName the user name
     * @param password the user password
     * @return the user if found, null otherwise
     */
    public User findUser(String userName, String password) {
        List<User> users = repo.readUsers();
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(userName)
                    && user.getPassword().equalsIgnoreCase(password)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Method to get the list of users
     * @return the list of users
     */
    public List<User> findUsers(){
        List<User> users = new ArrayList<User>();
        users= repo.readUsers();
        return users;
    }

    /**
     * Method to get the next user id
     * @return teh next user id
     */
    private int getNextUserId() {
        List<User> users = repo.readUsers();
        if (users.isEmpty()) {
            return 1;
        }
        int lastItemId = users.get(users.size() - 1).getId();
        return ++lastItemId;
    }

    /**
     * Method to delete a user
     * @param userName the user name
     * @param pw the user password
     * @return true if the deletion has suceeded, false otherwise
     */
    public boolean deleteUser(String userName, String pw) {
        User foundUser= findUser(userName, pw);
        boolean status = false;
        if (foundUser != null)
            status =  repo.deleteUser(foundUser.getId());
        return status;
    }

    /**
     * Method to update a user
     * @param firstName the user first name
     * @param lastName the user last name
     * @param userName the user name
     * @param password the user password
     * @param email the user mail
     * @param isAdmin a boolean true if the user is a admin, false otherwise
     */
    public boolean updateUser(int id,String firstName, String lastName ,String userName, String password,
                              String email , boolean isAdmin) {
        User foundUser = findUserById(id);
        if (foundUser != null) {
            User updatedMember = new User(foundUser.getId(),firstName,
                    lastName, userName, password,email,isAdmin);
            return repo.updateUser(foundUser.getId(), updatedMember);
        }
        return false;
    }

    /**
     * Method to find a user by its id
     * @param id the user id
     * @return the user if found, null otherwise
     */
    private User findUserById(int id) {
        List<User> users = repo.readUsers();
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    /**
     * Method to find a user by its name
     * @param username the user name
     * @return the user if found, null otherwise
     */
    public User findUserByUserName(String username) {
        List<User> users = repo.readUsers();
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }
}
