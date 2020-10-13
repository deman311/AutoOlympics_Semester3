package Model;

import javafx.beans.property.SimpleStringProperty;

public class Stadium {
    private int numOfSeats;

    private final SimpleStringProperty name;
    private final SimpleStringProperty location;
    
    public Stadium(String name, String location, int numOfSeats) {
    	this.name = new SimpleStringProperty(name);
    	this.location = new SimpleStringProperty(location);
        this.numOfSeats = numOfSeats;
    }

    public String getName() {
        return name.get();
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public String getLocation() {
        return location.get(); 
    }

	public void autoGenerate() {
		
	}
}