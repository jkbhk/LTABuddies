package Buddy;

public class StationRouteInfo 
{
    private String serviceNo;
    private int direction;
    private int routeSequence;
    private String stationCode;
    private double distFromStart;

    public StationRouteInfo(String serviceNo, int direction, int routeSequence, String stationCode, double distFromStart) 
    {
        this.serviceNo = serviceNo;
        this.direction = direction;
        this.routeSequence = routeSequence;
        this.stationCode = stationCode;
        this.distFromStart = distFromStart;
    }
    
    public String getServiceNo() 
    {
        return serviceNo;
    }

    public void setServiceNo(String serviceNo) 
    {
        this.serviceNo = serviceNo;
    }

    public int getDirection() 
    {
        return direction;
    }

    public void setDirection(int direction) 
    {
        this.direction = direction;
    }

    public int getRouteSequence() 
    {
        return routeSequence;
    }

    public void setRouteSequence(int routeSequence) 
    {
        this.routeSequence = routeSequence;
    }

    public String getStationCode() 
    {
        return stationCode;
    }

    public void setStationCode(String stationCode)
    {
        this.stationCode = stationCode;
    }

    public double getDistFromStart() 
    {
        return distFromStart;
    }

    public void setDistFromStart(double distFromStart)
    {
        this.distFromStart = distFromStart;
    }
}
