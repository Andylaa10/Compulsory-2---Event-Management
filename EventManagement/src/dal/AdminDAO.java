package dal;

import be.Admin;
import be.EventCoordinator;
import com.microsoft.sqlserver.jdbc.SQLServerException;
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

            String sql = "SELECT * FROM Login WHERE IsAdmin = 1;";

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
     * Making a coordinator list, connecting to the database and adding the results to our ArrayList.
     * @return a list of coordinators or an empty list of coordinators
     */
    public List<EventCoordinator> getCoordinator() {

        ArrayList<EventCoordinator> allCoordinators = new ArrayList<>();

        try (Connection connection = connector.getConnection()) {

            String sql = "SELECT * FROM Login WHERE IsAdmin =0;";

            Statement statement = connection.createStatement();

            if (statement.execute(sql)) {
                ResultSet resultset = statement.getResultSet();
                while (resultset.next()) {
                    int loginID = resultset.getInt("LoginID");
                    String username = resultset.getString("username");
                    String password = resultset.getString("password");
                    boolean isAdmin = resultset.getBoolean("isAdmin");


                    EventCoordinator coordinator = new EventCoordinator(loginID, username, password, isAdmin);
                    allCoordinators.add(coordinator);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allCoordinators;
    }

    /**
     * Creates an eventCoordinator, by inserting a giving username, password and a boolean saying if this is an admin
     * @param username
     * @param password
     * @param isAdmin
     * @return EventCoordinator
     * @throws SQLException
     */
    public EventCoordinator createCoordinator (String username, String password, boolean isAdmin) throws SQLException {
        try (Connection connection = connector.getConnection()) {
            String sql = "INSERT INTO Login (username, password, isAdmin) VALUES (?,?,?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setBoolean(3, isAdmin);
                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }

                EventCoordinator coordinator = new EventCoordinator(id, username, password, isAdmin);
                return coordinator;

            }

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * @param id
     * Deletes a coordinator by taken the id and if isAdmin is false
     */
    public void deleteCoordinator(int id) {
        try (Connection connection = connector.getConnection()) {
            String sql = "DELETE FROM Login WHERE LoginID =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param eventCoordinator
     * Edits a coordinator
     */
    public void editCoordinator(EventCoordinator eventCoordinator) {
        try (Connection connection = connector.getConnection()) {
            String sql = "UPDATE Login SET username=?, password=?, isAdmin=? WHERE LoginID=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, eventCoordinator.getUsername());
            preparedStatement.setString(2, eventCoordinator.getPassword());
            preparedStatement.setBoolean(3, eventCoordinator.isAdmin());
            preparedStatement.setInt(4, eventCoordinator.getId());
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not edit coordinator");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param loginId
     * @param eventId
     * Adds a selected coordinator to an event
     */
    public void addCoordinatorToEvent(int loginId, int eventId){
        String sql = "INSERT INTO CoordinatorOnEvent (LoginId, EventId) VALUES (?,?);";
        try (Connection con = connector.getConnection();
             PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setInt(1, loginId);
            st.setInt(2, eventId);
            st.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * @param loginId
     * @param eventId
     * Deletes a customer from event
     */
    public void deleteFromEvent(int loginId, int eventId){
        String sql = "DELETE FROM CoordinatorOnEvent WHERE LoginId = ? AND EventId = ?;";
        try (Connection connection = connector.getConnection()) {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, loginId);
            st.setInt(2, eventId);
            st.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * Gets a list of customer on event
     * @param eventId
     * @return List of customers
     * @throws SQLException
     */
    public List<EventCoordinator> getCoordinatorsOnEvent(int eventId) throws SQLException {
        ArrayList<EventCoordinator> allCoordinatorOnEvent = new ArrayList<>();

        try (Connection connection = connector.getConnection()) {
            String sql = "SELECT * FROM Login INNER JOIN CoordinatorOnEvent ON CoordinatorOnEvent.LoginId = Login.LoginID WHERE CoordinatorOnEvent.eventId = ?;";
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, eventId);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                int id = rs.getInt("LoginID");
                String username = rs.getString("username");
                String password = rs.getString("password");
                boolean isAdmin = rs.getBoolean("IsAdmin");
                if (!isAdmin){
                    allCoordinatorOnEvent.add(new EventCoordinator(id, username, password, isAdmin));
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return allCoordinatorOnEvent;
    }

    /**
     * This method gets a login from the database, only if it is an admin
     * @param user
     * @param pass
     * @return Admin from database
     * @throws SQLServerException
     */
    public Admin Login(String user, String pass) throws SQLServerException {
        String sql = "SELECT * FROM Login WHERE username =? AND password =?;";
        try(Connection connection = connector.getConnection()){
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                int id = rs.getInt("LoginID");
                String username = rs.getString("username");
                String password = rs.getString("password");
                boolean isAdmin = rs.getBoolean("IsAdmin");
                if (isAdmin){
                    return new Admin(id, username, password, isAdmin);
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }


    /**
     * Method used for testing the DAO class
     */
    public static void main(String[] args) throws IOException, SQLException {
        AdminDAO adminDAO = new AdminDAO();
        List<EventCoordinator> admins = adminDAO.getCoordinatorsOnEvent(29);
        List<EventCoordinator> admins1 = adminDAO.getCoordinatorsOnEvent(30);
        List<EventCoordinator> admins2 = adminDAO.getCoordinatorsOnEvent(36);
        List<EventCoordinator> admins3 = adminDAO.getCoordinatorsOnEvent(38);
        System.out.println(admins);
        System.out.println(admins1);
        System.out.println(admins2);
        System.out.println(admins3);
    }
}
