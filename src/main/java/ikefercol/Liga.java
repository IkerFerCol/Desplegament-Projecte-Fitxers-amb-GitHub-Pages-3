package ikefercol;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Liga {
    @JsonProperty("laliga")
    private List<Equipo> laliga;
    @JsonProperty("premierleague")
    private List<Equipo> premierleague;

    public Liga(List<Equipo> laliga, List<Equipo> premierleague) {
        this.laliga = laliga;
        this.premierleague = premierleague;
    }

    public Liga(){}

    public List<Equipo> getLaliga() {
        return laliga;
    }

    public void setLaliga(List<Equipo> laliga) {
        this.laliga = laliga;
    }

    public List<Equipo> getPremierleague() {
        return premierleague;
    }

    public void setPremierleague(List<Equipo> premierleague) {
        this.premierleague = premierleague;
    }

    @Override
    public String toString() {
        return "Liga{" +
                "laliga=" + laliga +
                ", premierleague=" + premierleague +
                '}';
    }
}
