package Model;

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

    public void winMedal(Olympic.eMedal medal){
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
	
	public Olympic.eCompetition getField() {
		return field;
	}
	
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
	
	public String getSBestRun() {
		// ONLY INHERITED
		return null;
	}
	
	public String getSBestJump() {
		// ONLY INHERITED
		return null;
	}
	
	public void makeHimLoseRun() {
		// ONLY INHERITED
	}
	
	public void makeHimLoseJump() {
		// ONLY INHERITED
	}

	@Override
	public void setJump(String score) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRun(String score) {
		// TODO Auto-generated method stub
		
	}
}
