package hr.ferit.filipznaor.f1explorer.POJO;

public class Circuit{
    private String circuitId;
    private String circuitName;
    private Location Location;

    public String getCircuitId(){
        return circuitId;
    }
    public String getName(){
        return circuitName;
    }
    public String getCountry(){
        return Location.getCountry();
    }
}
