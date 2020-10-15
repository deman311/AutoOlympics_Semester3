package Model;

import javafx.beans.property.SimpleStringProperty;

public class Referee extends Person {


    private Olympic.eCompetition field;
    private final SimpleStringProperty sName,sCountry,sField;

    public Referee(String name, NationalTeam country, Olympic.eCompetition field) {
        sName = new SimpleStringProperty(name);
        sCountry = new SimpleStringProperty(country.getSName());
        sField = new SimpleStringProperty(field.name());
    	super.setName(name);
        super.setCountry(country);
        this.field = field;
    }

    public Olympic.eCompetition getField() {
        return field;
    }

    public void setField(Olympic.eCompetition field) {
        this.field = field;
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
}
