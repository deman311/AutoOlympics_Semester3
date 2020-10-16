package Model;

import java.util.Random;

import javafx.beans.property.SimpleStringProperty;

public class RunnerJumper extends Athlete implements iJumper, iRunner{
	Random rand = new Random();
	
	private SimpleStringProperty sBestRun,sBestJump;
	
    public RunnerJumper(String name, NationalTeam country) {
        super(name, country);
        super.setSField("RUNNING, HIGHJUMPING");
    }


    public void generateTeamBestJump() {
    	// NOT RELEVANT
    }


    public void generateTeamBestRun() {
    	// NOT RELEVANT
    }
    
    public String getSBestRun() {
    	return sBestRun.get();
    }
    
    public String getSBestJump() {
    	return sBestJump.get();
    }
    
    @Override
	public void generatePersonalBestJump() {
		sBestJump = new SimpleStringProperty(String.format("%.3f",rand.nextDouble()*10+2));
	}

	@Override
	public void generatePersonalBestRun() {
		sBestRun = new SimpleStringProperty(String.format("%.3f",rand.nextDouble()*10+8+rand.nextInt(2)));
	}
}
