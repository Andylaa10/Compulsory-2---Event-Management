package bll.helpers;

import be.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventSearcherTest {

    @DisplayName("Test valid inputs, to check if the method work and give you anything back")
    @Test
    void validSearch(){
        //Triple A pattern
        //Arrange - set up our test object etc.
        //Make a list of Events
        List<Event> searchData = new ArrayList<>();
        //Make an instance of EventSearcher
        EventSearcher searcher = new EventSearcher();

        //Making data to insert into searchData list
        Event event1 = new Event(1, "PartyHardy", "22-05-22", "20:00", "06:00", "EASV's bar",
                "Remember ticket", "50 kr", 20, 500);
        Event event2 = new Event(2, "Jumping party", "30-05-22", "19:00", "03:00", "EASV's parking lot ",
                "Remember light", "for free", 10, 300);
        Event event3 = new Event(3, "Sleep over party", "02-07-22", "18:00", "10:00", "EASV's innovatorium",
                "Remember sleeping bag and accessories", "200 kr", 25, 50);
        Event event4 = new Event(4, "Eating party", "09-04-22", "15:00", "22:00", "EASV's Cafe",
                "Remember ticket", "100 kr", 30, 60);


        //Added data
        searchData.add(event1);
        searchData.add(event2);
        searchData.add(event3);
        searchData.add(event4);

        int expected = 4;
        int actual = searcher.search(searchData, "party").size();

        Assertions.assertEquals(expected, actual);

    }

    @DisplayName("Test invalid input, to check if the method work and give you nothing back")
    @Test
    void invalidSearch(){
        //Triple A pattern

        //Arrange - set up our test object etc.
        //Make a list of Events
        List<Event> searchData = new ArrayList<>();
        //Make an instance of EventSearcher
        EventSearcher searcher = new EventSearcher();

        //Making data to insert into searchData list
        Event event1 = new Event(1, "PartyHardy", "22-05-22", "20:00", "06:00", "EASV's bar",
                "Remember ticket", "50 kr", 20, 500);
        Event event2 = new Event(2, "Jumping party", "30-05-22", "19:00", "03:00", "EASV's parking lot ",
                "Remember light", "for free", 10, 300);
        Event event3 = new Event(3, "Sleep over party", "02-07-22", "18:00", "10:00", "EASV's innovatorium",
                "Remember sleeping bag and accessories", "200 kr", 25, 50);
        Event event4 = new Event(4, "Eating party", "09-04-22", "15:00", "22:00", "EASV's Cafe",
                "Remember ticket", "100 kr", 30, 60);

        //Added data
        searchData.add(event1);
        searchData.add(event2);
        searchData.add(event3);
        searchData.add(event4);

        //Act - do the actual cal or method run
        // Act + assert
        Exception err = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            searcher.search(searchData, "Magnus");
        });

        // Extra assert
        String expectedMessage = "Nothing is found";
        String actualMessage = err.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }



}