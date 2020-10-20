package Model;

import java.util.ArrayList;

import Controller.ProgramRunner;
import Model.Olympic.eMedal;

public class PersonalCompetition extends Competition {
   
	private ArrayList<Athlete> competitors = new ArrayList<Athlete>();

    public PersonalCompetition(Olympic.eCompetition field) {
    	super(Olympic.eType.PERSONAL, field);	
    }
    
    @Override
	public void fillCompetitors() {
		for(NationalTeam team : ProgramRunner.getCurretOlympic().getCountries())
    		for(Athlete competitor: team.getMembers())
    			if(competitor.getSField().equalsIgnoreCase(super.getFieldName()) || competitor.getSField().equalsIgnoreCase("RUNNING, HIGHJUMPING"))
    				if(!competitors.contains(competitor))
    				competitors.add(competitor);
	}
    
	@Override
    public ArrayList<Athlete> getPersonalCompetitors() {
    	return competitors;
    }
	
	@Override
	public void awardVictors() {
		ArrayList<Athlete> runners = new ArrayList<Athlete>();
		ArrayList<Athlete> jumpers = new ArrayList<Athlete>();
		for(Athlete ath : competitors) {
			if(ath.getSField().contains("RUNNING"))
				runners.add(ath);
			if(ath.getSField().contains("HIGHJUMPING"))
				jumpers.add(ath);
		}
		
		if(super.getFieldName().contains("RUNNING")) {
			boolean flag;
			do {
				flag = false;
				for(int i=0; i<runners.size()-1;i++)
					if(Double.parseDouble((runners.get(i+1)).getSBestRun())<Double.parseDouble((runners.get(i)).getSBestRun())) {
						runners.set(i+1, runners.set(i, runners.get(i+1)));
						flag = true;
					}
					else if(Double.parseDouble((runners.get(i+1)).getSBestRun()) == Double.parseDouble((runners.get(i)).getSBestRun())) {
						runners.get(i+1).makeHimLoseRun();
						flag = true;
					}
			} while(flag);
			if(runners.size()>0)
				runners.get(0).winMedal(eMedal.GOLD);
			if(runners.size()>1)
				runners.get(1).winMedal(eMedal.SILVER);
			if(runners.size()>2)
				runners.get(2).winMedal(eMedal.BRONZE);
		}
		else {
			boolean flag;
			do {
				flag = false;
				for(int i=0; i<jumpers.size()-1;i++)
					if(Double.parseDouble((jumpers.get(i+1)).getSBestJump())>Double.parseDouble((jumpers.get(i)).getSBestJump())) {
						jumpers.set(i+1, jumpers.set(i, jumpers.get(i+1)));
						flag = true;
					}
					else if(Double.parseDouble((jumpers.get(i+1)).getSBestJump()) == Double.parseDouble((jumpers.get(i)).getSBestJump())) {
						jumpers.get(i+1).makeHimLoseJump();
						flag = true;
					}
			} while(flag);
			if(jumpers.size()>0)
				jumpers.get(0).winMedal(eMedal.GOLD);
			if(jumpers.size()>1)
				jumpers.get(1).winMedal(eMedal.SILVER);
			if(jumpers.size()>2)
				jumpers.get(2).winMedal(eMedal.BRONZE);
		}
	}
}