package Model;

public class Runner extends Athlete implements iRunner{

    public Runner(String name, NationalTeam country) {
        super(name, country);
        super.setField(Olympic.eCompetition.RUNNING);
    }

    public void generatePersonalBestRun() {

    }

    public void generateTeamBestRun() {

    }
}
