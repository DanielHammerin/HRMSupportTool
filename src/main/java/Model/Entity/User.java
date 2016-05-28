package Model.Entity;

import java.io.Serializable;

/**
 * Created by Abeer on 4/21/2016.
 * Class User representing the web users ( HRM support stuff)
 */
public class User implements Serializable {

    private int id;
    private String firstName = "anonymous";
    private String lastName = "user";
    private String username = null;
    private String password = null;
    private String email = null;
    private boolean isAdmin = false;

    public User(int id, String firstName, String lastName, String username,
                String password, String email, boolean isAdmin) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    /* Getters */

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    /* Setters */

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}
