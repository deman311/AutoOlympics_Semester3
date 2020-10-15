package Model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;

public class NationalTeam {
    private int goldMedals, silverMedals, bronzeMedals;
    private ArrayList<Athlete> members = new ArrayList<Athlete>();
    
    private SimpleStringProperty country;
    private SimpleStringProperty numOfMedals;

    public NationalTeam(String country) {
    	this.country = new SimpleStringProperty(country);
        this.numOfMedals = new SimpleStringProperty("0");
    }
    
    public NationalTeam(String country,String numOfMedals) {
        this.country = new SimpleStringProperty(country);
        this.numOfMedals = new SimpleStringProperty(numOfMedals);
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

    public int getTotalNumOfMedals(){
        return goldMedals+silverMedals+bronzeMedals;
    }

    public int getGoldMedals() {
        return goldMedals;
    }

    public int getSilverMedals() {
        return silverMedals;
    }

    public int getBronzeMedals() {
        return bronzeMedals;
    }
    
    public void setNumOfMedals(String value) {
    	this.numOfMedals = new SimpleStringProperty(value);
    }

	public String getCountry() {
		return country.get();
	}
	
	public String getNumOfMedals() {
		return numOfMedals.get();
	}

	public void autoGenerate() {
		for(int i=1;i<=10;i++)
			members.add(new Runner(getCountry()+ " Bob Runnerski " + i, this));
		for(int i=1;i<=10;i++)
			members.add(new HighJumper(getCountry()+ " Bob Jumpski " + i, this));
		for(int i=1;i<=10;i++)
			members.add(new RunnerJumper(getCountry()+ " Bob Runjumpski " + i, this));
		
		for(Athlete him : members) {
			him.randomizeMedals();
			setNumOfMedals(""+(Integer.parseInt(getNumOfMedals())+him.getNumOfMedals()));
		}
	}
}
