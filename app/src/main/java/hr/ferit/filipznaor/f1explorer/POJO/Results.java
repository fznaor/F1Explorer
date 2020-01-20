package hr.ferit.filipznaor.f1explorer.POJO;

public class Results{
    private String positionText;
    private double points;
    private Driver Driver;
    private Constructor Constructor;
    private String status;
    private RaceResultsTime Time;
    private int grid;

    public String getPositionText(){
        return positionText;
    }
    public double getPoints(){
        return points;
    }
    public Driver getDriver(){
        return Driver;
    }
    public Constructor getConstructor(){
        return Constructor;
    }
    public String getStatus(){
        return status;
    }
    public RaceResultsTime getResultsTime(){
        return Time;
    }
    public int getGrid(){
        return grid;
    }
}
