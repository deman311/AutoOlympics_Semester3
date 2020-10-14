package Model;

import java.util.ArrayList;

import Controller.ProgramRunner;

public class PersonalCompetition extends Competition {
   
	private ArrayList<Athlete> competitors = new ArrayList<Athlete>();

    public PersonalCompetition(Olympic.eCompetition field) {
    	super(Olympic.eType.PERSONAL, field);	
    }
    
    @Override
	public void fillCompetitors() {
		for(NationalTeam team : ProgramRunner.getCurretOlympic().getCountries())
    		for(Athlete competitor: team.getMembers())
    			if(competitor.getField_name().equalsIgnoreCase(super.getField()))
    				competitors.add(competitor);
	}
}