package Model.FileRepo;

import Model.Entity.User;

import java.util.List;

/**
 * Created by Abeer on 4/21/2016.
 * CRUD operation
 Create, Read, Update, Delete for user
 */
public interface IRepository {

    /**
     * Method to create a user
     * @param user the user to create
     * @return true if the user creation has suceeded, false otherwise
     */
    boolean createUser(User user);

    /**
     * Method to get all the users
     * @return the list of all users registred
     */
    List<User> readUsers();

    /**
     * Method to update a user
     * @param userId the user id
     * @param updateUser the new user object
     * @return true if the update has suceeded, false otherwise
     */
    boolean updateUser(int userId, User updateUser);

    /**
     * Method to delete a user
     * @param UserId the user id
     * @return true if the deletion has suceeded, false otherwise
     */
    boolean deleteUser(int UserId);
}
