package Model;

import java.util.Random;

import javafx.beans.property.SimpleStringProperty;

public class HighJumper extends Athlete implements iJumper {
	Random rand = new Random();
	
	private SimpleStringProperty sBestJump;
	
    public HighJumper(String name, NationalTeam country) {
        super(name, country);
        super.setField(Olympic.eCompetition.HIGHJUMPING);
        generatePersonalBestJump();
    }

    @Override
	public void generatePersonalBestJump() {
		sBestJump = new SimpleStringProperty(String.format("%.3f",rand.nextDouble()+2));
	}
    
    @Override
    public void setJump(String score) {
    	sBestJump = new SimpleStringProperty(score);
    }

    public void generateTeamBestJump() {
    	// NOT RELEVANT
    }
    
    public String getSBestJump() {
    	return sBestJump.get();
    }
    
    public void makeHimLoseJump() {
    	sBestJump = new SimpleStringProperty(String.format("%.3f",Double.parseDouble(sBestJump.get()) - 0.001));
    	if(Double.parseDouble(sBestJump.get())<0)
    		sBestJump = new SimpleStringProperty(String.format("%.3f", rand.nextFloat()));
    }
}
