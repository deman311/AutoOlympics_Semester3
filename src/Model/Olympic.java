package Model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
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
	
	public ArrayList<String> getCountryNames() {
		ArrayList<String> temp = new ArrayList<String>();
		for(NationalTeam team : countries)
			temp.add(team.getCountry());
		
		return temp;
	}
	
	public ArrayList<String> getFieldNames() {
		ArrayList<String> temp = new ArrayList<String>();
		for(eCompetition field : eCompetition.values())
			temp.add(field.name());
		
		return temp;
	}
	
	public eCompetition[] getFields() {
		return eCompetition.values();
	}
	
	public void addCountry(NationalTeam country) {
		countries.add(country);
	}
	
	public void removeCountry(NationalTeam country) {
		countries.remove(country);
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
			competitions.add(new TeamCompetition(eCompetition.values()[i]));
		}
		for(Competition com : competitions)
			com.fillCompetitors();
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
	
	public ArrayList<Competition> getCompetitions() {
		return competitions;
	}
}