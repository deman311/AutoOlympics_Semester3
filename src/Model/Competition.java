package Model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Controller.ProgramRunner;
import Model.Olympic.eCompetition;
import javafx.beans.property.SimpleStringProperty;

public class Competition {
	
	private Stadium stadium;
 	private Referee referee;
		
    private final SimpleStringProperty type;
    private final SimpleStringProperty field;
    private eCompetition eField;
    
    public Competition(Olympic.eType type, Olympic.eCompetition field) {
    	this.type = new SimpleStringProperty(type.name());
    	this.field = new SimpleStringProperty(field.name());
    	eField = field;
    }

    public Stadium getStadium() {
        return stadium;
    }
    
    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }
    
    public boolean setReferee(Referee referee) {
    	if(referee.getField().name().equalsIgnoreCase(this.getFieldName())) {
    		this.referee = referee;
    		return true;
    	}
    	return false;
    }

	public Referee getReferee() {
        return referee;
    }

	public void genStadium() {
		ArrayList<String> stadiums = new ArrayList<String>();
		try {
			Scanner readStadium = new Scanner(new FileReader("./Stadiums.txt"));
			while(readStadium.hasNext()) {
				stadiums.add(readStadium.nextLine());
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Could not find stadium file!","ErrorMsg",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		Random rand = new Random();
		char location = (char)(rand.nextInt(26)+65);
		stadium = new Stadium (stadiums.get(rand.nextInt(10)), ""+location +(1+rand.nextInt(9)), rand.nextInt(10000)+1000);
	}
	
	public void genReferee() {
		Random rand = new Random();
		ArrayList<NationalTeam> countries =  ProgramRunner.getCurretOlympic().getCountries();
		referee = new Referee("Bob Judgeski "+(rand.nextInt(100)+1), countries.get(rand.nextInt(countries.size())), eField);
	}
	
    
	public String getType() {
		return type.get();
	}

	public String getFieldName() {
		return field.get();
	}
	
	public eCompetition getField() {
		return eField;
	}

	public void fillCompetitors() {
		// Never Used - Only by the inherited
	}
	public ArrayList<NationalTeam> getNationalCompetitors(){
		
		return null;
	}

	public ArrayList<Athlete> getPersonalCompetitors() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void countVictory() {
		// NOT USED
	}
}
