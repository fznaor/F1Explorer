package hr.ferit.filipznaor.f1explorer.POJO;

import java.util.List;

public class Race{
    private int round;
    private String raceName;
    private int season;
    private List<Results> Results;
    private List<QualifyingResults> QualifyingResults;
    private Circuit Circuit;

    public int getRound(){
        return round;
    }
    public String getRaceName(){
        return raceName;
    }
    public int getSeason() {
        return season;
    }
    public List<Results> getRaceResults(){
        return Results;
    }
    public Circuit getCircuit(){
        return Circuit;
    }
    public List<QualifyingResults> getQualifyingResults(){
        return QualifyingResults;
    }
}
