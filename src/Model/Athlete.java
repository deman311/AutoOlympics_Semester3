package Model;

import java.util.Random;

import Model.Olympic.eMedal;
import javafx.beans.property.SimpleStringProperty;

public class Athlete extends Person {
	@SuppressWarnings("unused")
	private Olympic.eCompetition field;

    private SimpleStringProperty athlete_name;
	private SimpleStringProperty field_name;
	private SimpleStringProperty gold_Medals;
	private SimpleStringProperty silver_Medals;
	private SimpleStringProperty bronze_Medals;
    
    public Athlete(String name, NationalTeam country) {
    	this.athlete_name = new SimpleStringProperty(name);
    	this.field_name = new SimpleStringProperty("temp");
    	this.gold_Medals = new SimpleStringProperty("0");
    	this.silver_Medals = new SimpleStringProperty("0");
    	this.bronze_Medals = new SimpleStringProperty("0");
        super.setName(name);
        super.setCountry(country);
    }

    public void wonMedal(Olympic.eMedal medal){
        switch (medal){
            case GOLD: this.gold_Medals = new SimpleStringProperty(""+(Integer.parseInt(gold_Medals.getValue())+1)); break;
            case SILVER: this.silver_Medals = new SimpleStringProperty(""+(Integer.parseInt(silver_Medals.getValue())+1)); break;
            case BRONZE: this.bronze_Medals = new SimpleStringProperty(""+(Integer.parseInt(bronze_Medals.getValue())+1)); break;
        }
    }

    public int getNumOfMedals(){
        return Integer.parseInt(gold_Medals.getValue())+Integer.parseInt(silver_Medals.getValue())+Integer.parseInt(bronze_Medals.getValue());
    }

    public void setField(Olympic.eCompetition field) {
        this.field = field;
        this.field_name = new SimpleStringProperty(field.name());
    }
    
    public void setField_Name(String name) {
    	this.field_name = new SimpleStringProperty(name);
    }
    
	public String getAthlete_name() {
		return athlete_name.get();
	}

	public String getField_name() {
		return field_name.get();
	}

	public String getGold_Medals() {
		return gold_Medals.get();
	}

	public String getSilver_Medals() {
		return silver_Medals.get();
	}

	public String getBronze_Medals() {
		return bronze_Medals.get();
	}
	
	public void randomizeMedals() {
		Random rand = new Random();
		for(int i=0;i<rand.nextInt(5);i++)
			wonMedal(eMedal.GOLD);
		for(int i=0;i<rand.nextInt(5);i++)
			wonMedal(eMedal.SILVER);
		for(int i=0;i<rand.nextInt(5);i++)
			wonMedal(eMedal.BRONZE);
	}
}
