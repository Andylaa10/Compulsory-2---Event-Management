package dal;

import be.Admin;
import be.EventCoordinator;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allAdmins;
    }
}
