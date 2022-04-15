package be.Interface;

public interface ILogin {

    //Interface used for the database login
    int getId();
    void setId(int id);
    String getUsername();
    void setUsername(String username);
    String getPassword();
    void setPassword(String password);
    Boolean isAdmin();

}
