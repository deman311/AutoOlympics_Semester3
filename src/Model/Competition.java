package Model;

import java.util.ArrayList;

import Model.Olympic.eCompetition;
import javafx.beans.property.SimpleStringProperty;

public class Competition {
	
	private Stadium stadium;
 	private Referee referee;
		
    private final SimpleStringProperty type;
    private final SimpleStringProperty field;
    private eCompetition eField;
    
    public Competition(Olympic.eType type, Olympic.eCompetition field) {
    	this.type = new SimpleStringProperty(type.name());
    	this.field = new SimpleStringProperty(field.name());
    	eField = field;
    }

    public Stadium getStadium() {
        return stadium;
    }
    
    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }
    
    public boolean setReferee(Referee referee) {
    	if(referee.getField().name().equalsIgnoreCase(this.getFieldName())) {
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

	public String getFieldName() {
		return field.get();
	}
	
	public eCompetition getField() {
		return eField;
	}

	public void fillCompetitors() {
		System.out.println("FLAG");
		// Never Used - Only by the inherited
	}
	
	public void genStadium() {
		
	}
	
	public void genReferee() {
		
	}
	
	public ArrayList<NationalTeam> getNationalCompetitors(){
		
		return null;
	}

	public ArrayList<Athlete> getPersonalCompetitors() {
		// TODO Auto-generated method stub
		return null;
	}
}
