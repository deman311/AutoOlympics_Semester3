package Model;

public class HighJumper extends Athlete implements iJumper {

    public HighJumper(String name, NationalTeam country) {
        super(name, country);
        super.setField(Olympic.eCompetition.HIGHJUMPING);
    }

    public void generatePersonalBestJump() {

    }

    public void generateTeamBestJump() {

    }
}
