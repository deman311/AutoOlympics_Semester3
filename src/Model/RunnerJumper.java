package Model;

public class RunnerJumper extends Athlete implements iJumper, iRunner{


    public RunnerJumper(String name, NationalTeam country) {
        super(name, country);
        super.setSField("RUNNING, HIGHJUMPING");
    }

    public void generatePersonalBestJump() {

    }

    public void generateTeamBestJump() {

    }

    public void generatePersonalBestRun() {

    }

    public void generateTeamBestRun() {

    }
}
