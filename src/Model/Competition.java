package Model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;

public class Competition {
	
	private Stadium stadium;
 	private Referee referee;
		
    private final SimpleStringProperty type;
    private final SimpleStringProperty field;
    
    public Competition(Olympic.eType type, Olympic.eCompetition field) {
    	this.type = new SimpleStringProperty(type.name());
    	this.field = new SimpleStringProperty(field.name());
    }

    public Stadium getStadium() {
        return stadium;
    }
    
    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }
    
    public boolean setReferee(Referee referee) {
    	if(referee.getField().name().equalsIgnoreCase(this.getField())) {
    		this.referee = referee;
    		return true;
    	}
    	return false;
    }

    public Referee getReferee() {
        return referee;
    }

	public String getType() {
		return type.get();
	}

	public String getField() {
		return field.get();
	}

	public void fillCompetitors() {
		System.out.println("FLAG");
		// Never Used - Only by the inherited
	}
	
	public ArrayList<NationalTeam> getNationalCompetitors(){
		
		return null;
	}

	public ArrayList<Athlete> getPersonalCompetitors() {
		// TODO Auto-generated method stub
		return null;
	}
}
