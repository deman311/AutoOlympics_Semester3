package Model;

public abstract class Person {

    private String name;
    private NationalTeam country;

    public NationalTeam getCountry() {
        return country;
    }

    public void setCountry(NationalTeam country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
