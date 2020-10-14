package Model;

import java.util.ArrayList;

import Controller.ProgramRunner;

public class TeamCompetition extends Competition {
	private ArrayList<NationalTeam> competitors = new ArrayList<NationalTeam>();

    public TeamCompetition(Olympic.eCompetition field) {
    	super(Olympic.eType.TEAM, field);
    }

    @Override
	public void fillCompetitors() {
		for(NationalTeam team : ProgramRunner.getCurretOlympic().getCountries())
			competitors.add(team);
	}
    
    public ArrayList<NationalTeam> getNationalCompetitors(){
    	return competitors;
    }
}
