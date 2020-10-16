package Model;

import java.util.ArrayList;

import Controller.ProgramRunner;
import Model.Olympic.eMedal;

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
    
    @Override
	public void countVictory() {
		if(super.getFieldName().contains("RUNNING")) {
			boolean flag;
			do {
				flag = false;
				for(int i=0; i<competitors.size()-1;i++)
					if(Double.parseDouble((competitors.get(i+1)).getSBestRun())<Double.parseDouble((competitors.get(i)).getSBestRun())) {
						competitors.set(i+1, competitors.set(i, competitors.get(i+1)));
						flag = true;
					}
					else if(Double.parseDouble((competitors.get(i+1)).getSBestRun()) == Double.parseDouble((competitors.get(i)).getSBestRun())) {
						competitors.get(i+1).makeHimLoseRun();
						flag = true;
					}
			} while(flag);
		}
		else {
			boolean flag;
			do {
				flag = false;
				for(int i=0; i<competitors.size()-1;i++)
					if(Double.parseDouble((competitors.get(i+1)).getSBestJump())>Double.parseDouble((competitors.get(i)).getSBestJump())) {
						competitors.set(i+1, competitors.set(i, competitors.get(i+1)));
						flag = true;
					}
					else if(Double.parseDouble((competitors.get(i+1)).getSBestJump()) == Double.parseDouble((competitors.get(i)).getSBestJump())) {
						competitors.get(i+1).makeHimLoseJump();
						flag = true;
					}
			} while(flag);
		}
		competitors.get(0).winMedal(eMedal.GOLD);
		competitors.get(1).winMedal(eMedal.SILVER);
		competitors.get(2).winMedal(eMedal.BRONZE);
	}
}
