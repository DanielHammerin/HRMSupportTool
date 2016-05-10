package Model.FileRepo;

import Model.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Abeer on 4/21/2016.
 * Updated by Hatem Houssein 4/28/2016 (Changed the logFilePath to "demo\LocalDatabase\Users_DB.ser")
 */
public class UserFileRepository implements IRepository{
   // private final String prefixPath = "D:\\HRM_SupportTool_Users\\";
    private final String UserFilePath = "LocalDatabase\\Users_DB.ser";

    public UserFileRepository() {
        try {
            Path path = Paths.get(UserFilePath);
            Files.createDirectories(path.getParent());

            if (!isFileExist(path.toString())) {
                Files.createFile(path);
            }

        } catch (IOException ex) {
            Logger.getLogger(UserFileRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean isFileExist(String path) {
        return new File(path).exists();
    }



    @Override
    public boolean createUser(User user) {
        List<User> users = readUsers();
        users.add(user);
        return overwriteUsers(users);
    }

    private boolean overwriteUsers(List<User> users) {
        if (isFileExist(UserFilePath)) {
            try {
                FileOutputStream fileOut = new FileOutputStream(UserFilePath);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(users);
                out.close();
                fileOut.close();
            } catch (IOException i) {
                return false;
            }

            return true;
        }

        return false;
    }

    @Override
    public List<User> readUsers() {
        List<User> users = new ArrayList<>();

        if (isFileExist(UserFilePath) && !isEmptyFile(UserFilePath)) {
            try {
                FileInputStream fileIn = new FileInputStream(UserFilePath);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                users = (ArrayList<User>) in.readObject();
                in.close();
                fileIn.close();
            } catch (Exception i) {
            }
        }

        return users;
    }

    private boolean isEmptyFile(String userFilePath) {
        return new File(userFilePath).length() == 0;
    }

    @Override
    public boolean updateUser(int userId, User updateUser) {
        List<User> users = readUsers();
        int index = getMemberIndex(userId, users);

        if (index < 0) {
            return false;
        }

        User oldUser = users.get(index);

        oldUser.setFirstName(updateUser.getFirstName());
        oldUser.setLastName(updateUser.getLastName());
        oldUser.setUsername(updateUser.getUsername());
        oldUser.setPassword(updateUser.getPassword());
        oldUser.setEmail(updateUser.getEmail());
        oldUser.setAdmin(updateUser.isAdmin());

        return overwriteUsers(users);
    }

    @Override
    public boolean deleteUser(int userId) {
        List<User> users = readUsers();
        int index = getMemberIndex(userId, users);

        if (index < 0) {
            return false;
        }

        users.remove(index);
        return overwriteUsers(users);
    }

    private int getMemberIndex(int userId, List<User> users) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);

            if (user.getId() ==userId) {
                return i;
            }
        }

        return -1;
    }
}
