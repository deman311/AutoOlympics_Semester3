package Model;

public class Referee extends Person {


    private Olympic.eCompetition field;

    public Referee(String name, NationalTeam country, Olympic.eCompetition field) {
        super.setName(name);
        super.setCountry(country);
        this.field = field;
    }

    public Olympic.eCompetition getField() {
        return field;
    }

    public void setField(Olympic.eCompetition field) {
        this.field = field;
    }
}
