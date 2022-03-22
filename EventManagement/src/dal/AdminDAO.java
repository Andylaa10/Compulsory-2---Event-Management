package dal;

import be.Admin;
import dal.db.DatabaseConnector;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    private final DatabaseConnector connector;

    public AdminDAO() throws IOException {
        connector = new DatabaseConnector();
    }

    /**
     * Making an admin list, connecting to the database and adding the results to our ArrayList.
     * @return a list of admins or an empty list of admins
     */
    public List<Admin> getAdmins() {

        ArrayList<Admin> allAdmins = new ArrayList<>();

        try (Connection connection = connector.getConnection()) {

            String sql = "SELECT * FROM Login;";

            Statement statement = connection.createStatement();

            if (statement.execute(sql)) {
                ResultSet resultset = statement.getResultSet();
                while (resultset.next()) {
                    int loginID = resultset.getInt("LoginID");
                    String username = resultset.getString("username");
                    String password = resultset.getString("password");
                    boolean isAdmin = resultset.getBoolean("isAdmin");

                    Admin admin = new Admin(loginID, username, password, isAdmin);
                    allAdmins.add(admin);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allAdmins;
    }

    /**
     * @param admin
     * Edits an admin
     */
    public void editAdmin(Admin admin) {
        try (Connection connection = connector.getConnection()) {
            String sql = "UPDATE Login SET username=?, password=?, isAdmin=? WHERE LoginID=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, admin.getUsername());
            preparedStatement.setString(2, admin.getPassword());
            preparedStatement.setBoolean(3, admin.isAdmin());
            preparedStatement.setInt(4, admin.getId());
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not edit admin");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used for testing the DAO class
     */
    public static void main(String[] args) throws IOException {
        AdminDAO adminDAO = new AdminDAO();
        List<Admin> admins = adminDAO.getAdmins();
        System.out.println(admins);
    }
}
