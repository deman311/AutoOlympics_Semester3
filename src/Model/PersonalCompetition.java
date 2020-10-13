package Model;

import java.util.ArrayList;

public class PersonalCompetition extends Competition {

    private ArrayList<Athlete> competitors = new ArrayList<Athlete>();

    public PersonalCompetition(Stadium stadium, Olympic.eCompetition competition, Olympic olympic, Referee referee) {
        super.setStadium(stadium);
        super.setCompetition(competition);
        super.setReferee(referee);
        for(NationalTeam team : olympic.getCountries())
            for(Athlete athlete : team.getMembers()) {
                if(athlete.getField_name().equals(competition))
                    competitors.add(athlete);
            }
    }



}