package Model;

import java.util.Random;

import Model.Olympic.eMedal;
import javafx.beans.property.SimpleStringProperty;

public class Athlete extends Person implements iRunner, iJumper {
	
	private Olympic.eCompetition field;

	private SimpleStringProperty sName,sCountry,sField,sGold,sSilver,sBronze;
	
    public Athlete(String name, NationalTeam country) {
    	sName = new SimpleStringProperty(name);
    	sField = new SimpleStringProperty("Not Set");
    	sGold = new SimpleStringProperty("0");
    	sSilver = new SimpleStringProperty("0");
    	sBronze = new SimpleStringProperty("0");
    	sCountry = new SimpleStringProperty(country.getSName());
        super.setName(name);
        super.setCountry(country);
    }

    public void wonMedal(Olympic.eMedal medal){
        switch (medal){
            case GOLD: sGold = new SimpleStringProperty(""+(Integer.parseInt(sGold.get())+1)); break;
            case SILVER: sSilver = new SimpleStringProperty(""+(Integer.parseInt(sSilver.get())+1)); break;
            case BRONZE: sBronze = new SimpleStringProperty(""+(Integer.parseInt(sBronze.get())+1)); break;
        }
    }

    public int getNumOfMedals(){
        return Integer.parseInt(sGold.get())+Integer.parseInt(sSilver.get())+Integer.parseInt(sBronze.get());
    }

    public void setSField(String field) {
    	sField = new SimpleStringProperty(field);
    }
    
    public void setField(Olympic.eCompetition field) {
        this.field = field;
        sField = new SimpleStringProperty(field.name());
    }

	public String getSName() {
		return sName.get();
	}
	
	public String getSCountry() {
		return sCountry.get();
	}
	
	public String getSField() {
		return sField.get();
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
//	 public String getScore(Athlete athlete) {
//		
//	 }
	
	public Olympic.eCompetition getField() {
		return field;
	}
	
//	public void randomizeMedals() {
//		Random rand = new Random();
//		for(int i=0;i<rand.nextInt(5);i++)
//			wonMedal(eMedal.GOLD);
//		for(int i=0;i<rand.nextInt(5);i++)
//			wonMedal(eMedal.SILVER);
//		for(int i=0;i<rand.nextInt(5);i++)
//			wonMedal(eMedal.BRONZE);
//	}

	public void generatePersonalBestJump() {
		// TODO Auto-generated method stub
		
	}

	public void generateTeamBestJump() {
		// TODO Auto-generated method stub
		
	}

	public void generatePersonalBestRun() {
		// TODO Auto-generated method stub
		
	}

	public void generateTeamBestRun() {
		// TODO Auto-generated method stub
		
	}
}
