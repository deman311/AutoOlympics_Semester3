package Model;

import java.util.ArrayList;
import java.util.Random;

import javafx.beans.property.SimpleStringProperty;

public class NationalTeam implements iRunner,iJumper {
	Random rand = new Random();
    private ArrayList<Athlete> members = new ArrayList<Athlete>();
    
    private final SimpleStringProperty sName;
    private SimpleStringProperty sGold,sSilver,sBronze,sNumOfMedals;
    private SimpleStringProperty sBestJump,sBestRun;

    public NationalTeam(String country) {
    	sName = new SimpleStringProperty(country);
        sGold = new SimpleStringProperty("0");
        sSilver = new SimpleStringProperty("0");
        sBronze = new SimpleStringProperty("0");
        sNumOfMedals = new SimpleStringProperty("0");
        sBestJump = new SimpleStringProperty("0");
        sBestRun = new SimpleStringProperty("0");
    }

    public void addMember(Athlete athlete){
        members.add(athlete);
    }
    
    public void removeMember(Athlete athlete) {
    	members.remove(athlete);
    }

    public ArrayList<Athlete> getMembers() {
        return members;
    }
    
    public String getSNumOfMedals() {
    	countNumOfMedals();
    	return sNumOfMedals.get();
    }

	public String getSName() {
		return sName.get();
	}
	
	public String getSGold() {
		return sGold.get();
	}
	public String getSSilver() {
		return sSilver.get();
	}
	public String getSBronze() {
		return sBronze.get();
	}

	public void autoGenerate() {
		for(int i=1;i<=5;i++)
			members.add(new Runner(getSName()+ " Bob Runnerski " + i, this));
		for(int i=1;i<=5;i++)
			members.add(new HighJumper(getSName()+ " Bob Jumpski " + i, this));
		for(int i=1;i<=5;i++)
			members.add(new RunnerJumper(getSName()+ " Bob Runjumpski " + i, this));
		
		generateTeamBestRun();
		generateTeamBestJump();
		
		for(Athlete member : members)
			if(member.getClass().getSimpleName().equalsIgnoreCase("Runner"))
				member.generatePersonalBestRun();
			else if(member.getClass().getSimpleName().equalsIgnoreCase("HighJumper"))
				member.generatePersonalBestJump();
			else {
				member.generatePersonalBestRun();
				member.generatePersonalBestJump();
			}
	}
	
	public void countMedals() {
		for(Athlete member: members) {
			sGold = new SimpleStringProperty(""+(Integer.parseInt(sGold.get()) + Integer.parseInt(member.getSGold())));
			sSilver = new SimpleStringProperty(""+(Integer.parseInt(sSilver.get()) + Integer.parseInt(member.getSSilver())));
			sBronze = new SimpleStringProperty(""+(Integer.parseInt(sBronze.get()) + Integer.parseInt(member.getSBronze())));
		}
	}
	
	public void winMedal(Olympic.eMedal medal) {
		switch (medal.name()) {
		case "GOLD": sGold = new SimpleStringProperty(""+(Integer.parseInt(sGold.get()) + 1)); break;
		case "SILVER": sSilver = new SimpleStringProperty(""+(Integer.parseInt(sSilver.get()) + 1)); break;
		case "BRONZE": sBronze = new SimpleStringProperty(""+(Integer.parseInt(sBronze.get()) + 1)); break;
		}
	}
	
	public void countNumOfMedals() {
		sNumOfMedals = new SimpleStringProperty(""+(Integer.parseInt(sGold.get())+Integer.parseInt(sSilver.get())+Integer.parseInt(sBronze.get())));
	}

	@Override
	public void generatePersonalBestJump() {
		// NOT RELEVANT
		
	}

	public void generatePersonalBestRun() {
		// NOT RELEVANT
		
	}
	@Override
	public void generateTeamBestJump() {
		sBestJump = new SimpleStringProperty(String.format("%.3f",rand.nextDouble()+2));
	}

	@Override
	public void generateTeamBestRun() {
		sBestRun = new SimpleStringProperty(String.format("%.3f",rand.nextDouble()*10+8+rand.nextInt(2)));	
	}
	
	public String getSBestJump() {
		return sBestJump.get();
	}
	
	public String getSBestRun() {
		return sBestRun.get();
	}
	
	public void makeHimLoseRun() {
    	sBestRun = new SimpleStringProperty(String.format("%.3f",Double.parseDouble(sBestRun.get()) + 0.001));
    }
	
	public void makeHimLoseJump() {
	    	sBestJump = new SimpleStringProperty(String.format("%.3f",Double.parseDouble(sBestJump.get()) - 0.001));
	}
}
