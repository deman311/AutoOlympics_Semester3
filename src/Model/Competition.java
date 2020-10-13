package Model;

public class Competition {

    private Stadium stadium;
    private Olympic.eCompetition competition;
    private Referee referee;

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public Olympic.eCompetition getCompetition() {
        return competition;
    }

    public void setCompetition(Olympic.eCompetition competition) {
        this.competition = competition;
    }

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        if(referee.getField().equals(competition))
            this.referee = referee;
        else
            this.referee = null;
    }
}
