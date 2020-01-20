package hr.ferit.filipznaor.f1explorer.POJO;

import java.util.List;

public class StandingsLists{
    private int season;
    private List<DriverStandings> DriverStandings;
    private List<ConstructorStandings> ConstructorStandings;

    public int getSeason(){
        return season;
    }
    public List<DriverStandings> getDriverStandings(){
        return DriverStandings;
    }
    public List<ConstructorStandings> getConstructorStandings(){
        return ConstructorStandings;
    }
}
