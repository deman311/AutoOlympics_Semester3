package Model;

import java.util.Random;

import javafx.beans.property.SimpleStringProperty;

public class Runner extends Athlete implements iRunner{
	Random rand = new Random();
	private SimpleStringProperty sBestRun;
	
    public Runner(String name, NationalTeam country) {
        super(name, country);
        super.setField(Olympic.eCompetition.RUNNING);
        sBestRun = new SimpleStringProperty("0");
    }
    
    @Override
    public void generatePersonalBestRun() {
    	sBestRun = new SimpleStringProperty(String.format("%.3f",rand.nextDouble()*10+10+rand.nextInt(2)));
    }

    public void generateTeamBestRun() {
    	// NOT RELEVANT
    }
    
    public String getSBestRun() {
    	return sBestRun.get();
    }
    
    public void makeHimLoseRun() {
    	sBestRun = new SimpleStringProperty(String.format("%.3f",Double.parseDouble(sBestRun.get()) + 0.001));
    }
}
