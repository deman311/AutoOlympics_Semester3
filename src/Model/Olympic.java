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
	private String name,startDate, endDate;
	private ArrayList<NationalTeam> countries = new ArrayList<NationalTeam>();
	private ArrayList<Stadium> stadiums = new ArrayList<Stadium>();

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
		genStadiums();
	}
	
	private void genStadiums() {
		Random rand = new Random();
		for(int i=1;i<=10;i++)
			stadiums.add(new Stadium("Stadium " + i, "A" + i, rand.nextInt(5000)+2000));	
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
