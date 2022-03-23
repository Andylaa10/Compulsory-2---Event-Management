package gui.controller;

import gui.model.EventModel;

import java.io.IOException;

public class CreateEventController {

    EventModel eventModel;

    public CreateEventController() throws IOException {
        this.eventModel = new EventModel();
    }


}
