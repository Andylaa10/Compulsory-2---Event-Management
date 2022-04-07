package gui.controller;

import be.EventCoordinator;

import java.io.IOException;
import java.sql.SQLException;

public interface IController {

    void setEventCoordinator(EventCoordinator eventCoordinator) throws SQLException, IOException;

}
