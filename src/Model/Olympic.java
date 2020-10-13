package Model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Olympic {
	
	public static enum eMedal {GOLD, SILVER, BRONZE}
	public static enum eCompetition {RUNNING, HIGHJUMPING}
	public static enum eType{PERSONAL, TEAM}
	private String name,startDate, endDate;
	private ArrayList<NationalTeam> countries = new ArrayList<NationalTeam>();
	private ArrayList<Competition> competitions = new ArrayList<Competition>();

	public Olympic(String name, String startDate, String endDate) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public ArrayList<NationalTeam> getCountries() {
		return countries;
	}

	public ArrayList<Stadium> getStadiums() {
        return stadiums;
    }
	
	public void addCountry(NationalTeam country) {
		countries.add(country);
	}
	
	public void addStadium(Stadium stadium) {
		stadiums.add(stadium);
	}
	
	public void removeCountry(NationalTeam country) {
		countries.remove(country);
	}
	
	public void removeStadium(Stadium stadium) {
		stadiums.remove(stadium);
	}
	
	public String getName() {
		return name;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void autoGenerate() {
		genContries();
		for(NationalTeam team : countries)
			team.autoGenerate();
		for(int i=0;i<eCompetition.values().length;i++) {
			competitions.add(new PersonalCompetition(eCompetition.values()[i]));
			competitions.add(new Competition(eType.TEAM, eCompetition.values()[i]));
		}
			
	}

	public void genContries() {
		try {
			Scanner readCountry = new Scanner(new FileReader("./worldCountries.txt"));
			while(readCountry.hasNext())
				countries.add(new NationalTeam(readCountry.nextLine()));
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "ErrorMsg","Could not find coutries file!",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
