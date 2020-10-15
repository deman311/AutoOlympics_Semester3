package Model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;

public class NationalTeam {
    private ArrayList<Athlete> members = new ArrayList<Athlete>();
    
    private final SimpleStringProperty sName;
    private SimpleStringProperty sGold,sSilver,sBronze,sNumOfMedals;

    public NationalTeam(String country) {
    	sName = new SimpleStringProperty(country);
        sGold = new SimpleStringProperty("0");
        sSilver = new SimpleStringProperty("0");
        sBronze = new SimpleStringProperty("0");
        sNumOfMedals = new SimpleStringProperty("0");
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
    	return sNumOfMedals.get();
    }
    
    public int getTotalNumOfMedals(){
        return Integer.parseInt(sGold.get())+Integer.parseInt(sSilver.get())+Integer.parseInt(sBronze.get());
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
		for(int i=1;i<=10;i++)
			members.add(new Runner(getSName()+ " Bob Runnerski " + i, this));
		for(int i=1;i<=10;i++)
			members.add(new HighJumper(getSName()+ " Bob Jumpski " + i, this));
		for(int i=1;i<=10;i++)
			members.add(new RunnerJumper(getSName()+ " Bob Runjumpski " + i, this));
	}
}
