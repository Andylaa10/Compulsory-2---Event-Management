package be;

import be.Interface.ILogin;

public class Admin implements ILogin {

    private int id;
    private String usernameAdmin;
    private String passwordAdmin;
    private boolean isAdmin;

    /**
     * Constructor
     */
    public Admin() {
    }

    /**
     * @param id
     * @param usernameAdmin
     * @param passwordAdmin
     * @param isAdmin
     * Constructor with id, usernameAdmin and passwordAdmin
     */
    public Admin(int id, String usernameAdmin, String passwordAdmin, boolean isAdmin) {
        this.id = id;
        this.usernameAdmin = usernameAdmin;
        this.passwordAdmin = passwordAdmin;
        this.isAdmin = isAdmin;
    }

    /**
     * Constructor with username and password
     * @param username
     * @param password
     */
    public Admin(String username, String password) {
        this.usernameAdmin = username;
        this.passwordAdmin = password;
    }

    /**
     * Gets the id
     * @return id
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Sets the id
     * @param id
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the username
     * @return usernameAdmin
     */
    @Override
    public String getUsername() {
        return usernameAdmin;
    }

    /**
     * Sets the username
     * @param username
     */
    @Override
    public void setUsername(String username) {
        this.usernameAdmin = username;
    }

    /**
     * Gets the password
     * @return passwordAdmin
     */
    @Override
    public String getPassword() {
        return passwordAdmin;
    }

    /**
     * Sets the password
     * @param password
     */
    @Override
    public void setPassword(String password) {
        this.passwordAdmin = password;
    }

    /**
     * Check if this is an Admin
     * @return true
     */
    @Override
    public Boolean isAdmin() {
        return true;
    }


}
