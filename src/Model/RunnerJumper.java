package Model;

import java.util.Random;

import javafx.beans.property.SimpleStringProperty;

public class RunnerJumper extends Athlete implements iJumper, iRunner{
	Random rand = new Random();
	
	private SimpleStringProperty sBestRun,sBestJump;
	
    public RunnerJumper(String name, NationalTeam country) {
        super(name, country);
        super.setSField("RUNNING, HIGHJUMPING");
        sBestJump = new SimpleStringProperty("0");
        sBestRun = new SimpleStringProperty("0");
    }
    
    @Override
    public void setJump(String score) {
    	sBestJump = new SimpleStringProperty(score);
    }

    @Override
    public void setRun(String score) {
    	sBestRun = new SimpleStringProperty(score);
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
		sBestJump = new SimpleStringProperty(String.format("%.3f",rand.nextDouble()+2));
	}

	@Override
	public void generatePersonalBestRun() {
		sBestRun = new SimpleStringProperty(String.format("%.3f",rand.nextDouble()*10+10+rand.nextInt(2)));
	}
	
	public void makeHimLoseRun() {
    	sBestRun = new SimpleStringProperty(String.format("%.3f",Double.parseDouble(sBestRun.get()) + 0.001));
    }
	
	public void makeHimLoseJump() {
	    sBestJump = new SimpleStringProperty(String.format("%.3f",Double.parseDouble(sBestJump.get()) - 0.001));
	}
}