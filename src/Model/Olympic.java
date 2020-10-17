package Model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
	
	public ArrayList<String> getCountryNames() {
		ArrayList<String> temp = new ArrayList<String>();
		for(NationalTeam team : countries)
			temp.add(team.getSName());
		
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
		genCompetitions(true);
	}

	public void genContries() {
		try {
			Scanner readCountry = new Scanner(new FileReader("./worldCountries.txt"));
			ArrayList<NationalTeam> temp = new ArrayList<NationalTeam>();
			Random rand = new Random();
			NationalTeam tempCountry;
			while(readCountry.hasNext())
				temp.add(new NationalTeam(readCountry.nextLine()));
			for(int i = 0 ; i<6 ; i++) {
				tempCountry = temp.get(rand.nextInt(temp.size()));
				countries.add(tempCountry);
				temp.remove(tempCountry);
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Could not find coutries file!","ErrorMsg",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			
		}
	}
	
	public void genCompetitions(boolean isRandom) {
		competitions.clear();
		if(isRandom)
			for(NationalTeam team : countries)
				team.autoGenerate();
		for(int i=0;i<eCompetition.values().length;i++) {
			competitions.add(new PersonalCompetition(eCompetition.values()[i]));	
			competitions.add(new TeamCompetition(eCompetition.values()[i]));
		}
		for(Competition com : competitions) {
			com.fillCompetitors();	
			if(isRandom) {
				com.genReferee();
				com.genStadium();
			}		
		}
	}
	
	public void countMedals() {
		for(NationalTeam country : countries)
			country.countMedals();
	}
	
	public ArrayList<Competition> getCompetitions() {
		return competitions;
	}
	
	public void awardVictors() {
		for(Competition com : competitions)
			com.awardVictors();
	}
	
	public String findWinners(int place) {
		ArrayList<NationalTeam> firstPlace = new ArrayList<NationalTeam>();
		ArrayList<NationalTeam> secondPlace = new ArrayList<NationalTeam>();
		ArrayList<NationalTeam> thirdPlace = new ArrayList<NationalTeam>();
		ArrayList<NationalTeam> temp = new ArrayList<NationalTeam>(countries);
		
		int maxNumOfMedals, counter=0;
		do {
			maxNumOfMedals = Integer.parseInt(temp.get(0).getSNumOfMedals());
			for(int i=1;i<temp.size();i++) {
				if(maxNumOfMedals<Integer.parseInt(temp.get(i).getSNumOfMedals()))
					maxNumOfMedals = Integer.parseInt(temp.get(i).getSNumOfMedals());
			}
			for(int i=0;i<temp.size();i++) {
				if(maxNumOfMedals==Integer.parseInt(temp.get(i).getSNumOfMedals())) {
						switch(counter) {
						case 0: firstPlace.add(temp.get(i)); break;
						case 1: secondPlace.add(temp.get(i)); break;
						case 2: thirdPlace.add(temp.get(i)); break;
						
						}
						temp.remove(i);
						i--;
				}
			
			}
			counter++;
		}while(counter<3);
		
		
		switch(place){
			case 1:temp = new ArrayList<NationalTeam>(firstPlace); break;
			case 2:temp = new ArrayList<NationalTeam>(secondPlace); break;
			case 3:temp = new ArrayList<NationalTeam>(thirdPlace); break;
			default:System.err.println("Wrong place, should be 1,2,3"); break;
				
		}
		StringBuilder sb = new StringBuilder();
		for(NationalTeam team: temp) 
			sb.append(team.getSName() +"\n");
		
		return sb.toString();
		
	}
	
}