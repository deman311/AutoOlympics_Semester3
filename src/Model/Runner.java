package Model;

import java.util.Random;

import javafx.beans.property.SimpleStringProperty;

public class Runner extends Athlete implements iRunner{
	Random rand = new Random();
	private SimpleStringProperty sBestRun;
	
    public Runner(String name, NationalTeam country) {
        super(name, country);
        super.setField(Olympic.eCompetition.RUNNING);
    }
    
    @Override
    public void generatePersonalBestRun() {
    	sBestRun = new SimpleStringProperty(String.format("%.3f",rand.nextDouble()*10+8+rand.nextInt(2)));
    }

    public void generateTeamBestRun() {
    	// NOT RELEVANT
    }
    
    public String getSBestRun() {
    	return sBestRun.get();
    }
}
