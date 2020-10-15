package Model;

import javafx.beans.property.SimpleStringProperty;

public class Stadium {

    private final SimpleStringProperty sName,sLocation,sNumOfSeats;
    
    public Stadium(String name, String location, int numOfSeats) {
    	sName = new SimpleStringProperty(name);
    	sLocation = new SimpleStringProperty(location);
        sNumOfSeats = new SimpleStringProperty(""+numOfSeats);
    }

    public String getSName() {
        return sName.get();
    }

    public String getSNumOfSeats() {
        return sNumOfSeats.get();
    }

    public String getSLocation() {
        return sLocation.get();
    }
}