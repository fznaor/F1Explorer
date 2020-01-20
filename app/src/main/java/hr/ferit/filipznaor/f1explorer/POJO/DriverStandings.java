package hr.ferit.filipznaor.f1explorer.POJO;

import java.util.List;

public class DriverStandings{
    private int position;
    private double points;
    private int wins;
    private Driver Driver;
    private List<Constructors> Constructors;

    public int getPosition(){
        return position;
    }
    public double getPoints(){
        return points;
    }
    public int getWins(){
        return wins;
    }
    public Driver getDriver(){
        return Driver;
    }
    public List<Constructors> getConstructors(){
        return Constructors;
    }
}
