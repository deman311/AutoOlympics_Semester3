package Model;

import java.util.Random;

import javafx.beans.property.SimpleStringProperty;

public class HighJumper extends Athlete implements iJumper {
	Random rand = new Random();
	
	private SimpleStringProperty sBestJump;
	
    public HighJumper(String name, NationalTeam country) {
        super(name, country);
        super.setField(Olympic.eCompetition.HIGHJUMPING);
    }

    @Override
	public void generatePersonalBestJump() {
		sBestJump = new SimpleStringProperty(String.format("%.3f",rand.nextDouble()*10+2));
	}

    public void generateTeamBestJump() {
    	// NOT RELEVANT
    }
    
    public String getSBestJump() {
    	return sBestJump.get();
    }
}
