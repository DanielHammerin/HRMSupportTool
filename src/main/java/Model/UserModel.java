package Model;

import Model.Entity.User;
import Model.FileRepo.IRepository;
import Model.FileRepo.UserFileRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abeer on 4/21/2016.
 * userModel class for logic opertions between User and UserFileRepository
 */
public class UserModel {
    private final IRepository repo;

    public UserModel() {
        super();
        this.repo = new UserFileRepository();}

    public boolean createUser(String firstName, String lastName ,String userName, String password,
                              String email , boolean isAdmin) {

        User foundUser = findUser(userName,password);
        if (foundUser != null) {
            return false;
        }

        User member = new User(getNextUserId(), firstName, lastName, userName, password, email, isAdmin);
        return repo.createUser(member);
    }

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

    public List<User> findUsers(){
        List<User> users = new ArrayList<User>();
        users= repo.readUsers();
        return users;
    }


    private int getNextUserId() {

        List<User> users = repo.readUsers();
        if (users.isEmpty()) {
            return 1;
        };
        int lastItemId = users.get(users.size() - 1).getId();
        return ++lastItemId;
    }


    public boolean deleteUser(String userName, String pw) {
        User foundUser= findUser(userName, pw);
        boolean status = false;

        if (foundUser != null)
            status =  repo.deleteUser(foundUser.getId());
        return status;

    }


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


    private User findUserById(int id) {

        List<User> users = repo.readUsers();
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }

        return null;
    }


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
