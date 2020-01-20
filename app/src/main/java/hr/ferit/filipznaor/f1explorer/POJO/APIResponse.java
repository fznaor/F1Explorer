package hr.ferit.filipznaor.f1explorer.POJO;

import java.util.ArrayList;
import java.util.List;

public class APIResponse {
    private MRData MRData;

    public List<Results> getRaceResults(){
        return MRData.getRaceResults();
    }
    public List<QualifyingResults> getQualifyingResults(){
        return MRData.getQualifyingResults();
    }
    public List<Race> getRaces(){
        return MRData.getRaces();
    }
    public List<Season> getSeasons(){
        return MRData.getSeasons();
    }
    public List<Driver> getDrivers(){
        return MRData.getDrivers();
    }
    public List<Constructor> getConstructors(){
        return MRData.getConstructors();
    }
    public List<Circuit> getCircuits(){
        return MRData.getCircuits();
    }
    public List<StandingsLists> getStandings(){
        return MRData.getStandings();
    }
}

class MRData{
    private RaceTable RaceTable;
    private SeasonTable SeasonTable;
    private DriverTable DriverTable;
    private ConstructorTable ConstructorTable;
    private CircuitTable CircuitTable;
    private StandingsTable StandingsTable;

    public List<Results> getRaceResults(){
        return RaceTable.getRaceResults();
    }
    public List<QualifyingResults> getQualifyingResults(){
        return RaceTable.getQualifyingResults();
    }
    public List<Race> getRaces(){
        return RaceTable.getRaces();
    }
    public List<Season> getSeasons(){
        return SeasonTable.getSeasons();
    }
    public List<Driver> getDrivers(){
        return DriverTable.getDrivers();
    }
    public List<Constructor> getConstructors(){
        return ConstructorTable.getConstructors();
    }
    public List<Circuit> getCircuits(){
        return CircuitTable.getCircuits();
    }
    public String getCircuitId(){
        return SeasonTable.getCircuitId();
    }
    public List<StandingsLists> getStandings(){
        return StandingsTable.getStandingsLists();
    }
}

class RaceTable{
    private List<Race> Races;

    public List<Race> getRaces(){
        return Races;
    }
    public List<Results> getRaceResults(){
        List<Results> results = new ArrayList<>();
        for(Race race : Races){
            results.addAll(race.getRaceResults());
        }
        return results;
    }
    public List<QualifyingResults> getQualifyingResults(){
        List<QualifyingResults> qualifyingResults = new ArrayList<>();
        for(Race race : Races){
            qualifyingResults.addAll(race.getQualifyingResults());
        }
        return qualifyingResults;
    }
}

class SeasonTable{
    private List<Season> Seasons;
    private String circuitId;

    public List<Season> getSeasons() {
        return Seasons;
    }
    public String getCircuitId(){
        return circuitId;
    }
}

class DriverTable{
    private List<Driver> Drivers;

    public List<Driver> getDrivers(){
        return Drivers;
    }
}

class ConstructorTable{
    private List<Constructor> Constructors;

    public List<Constructor> getConstructors(){
        return Constructors;
    }
}

class CircuitTable{
    private List<Circuit> Circuits;

    public List<Circuit> getCircuits(){
        return Circuits;
    }
}

class StandingsTable{
    private List<StandingsLists> StandingsLists;

    public List<StandingsLists> getStandingsLists(){
        return StandingsLists;
    }
}