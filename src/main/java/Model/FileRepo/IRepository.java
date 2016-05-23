package Model.FileRepo;

import Model.Entity.User;

import java.util.List;

/**
 * Created by Abeer on 4/21/2016.
 * CRUD operation
 Create, Read, Update, Delete for user
 */
public interface IRepository {
    boolean createUser(User user);
    List<User> readUsers();
    boolean updateUser(int userId, User updateUser);
    boolean deleteUser(int UserId);
}
