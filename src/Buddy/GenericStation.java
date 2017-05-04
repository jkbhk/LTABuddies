package Buddy;

import java.util.ArrayList;

public abstract class GenericStation
{
    protected String name;
    protected String id; 
    protected ArrayList<StationRouteInfo> stationRouteInfoList = new ArrayList<>();

    public GenericStation(String name, String id)
    {
        this.name = name;
        this.id = id;
    }
    
    public String GetName()
    {
        return this.name;
    }
    
    public void SetName(String name)
    {
        this.name = name;
    }
    
    public String GetID()
    {
        return this.id;
    }
    
    public void SetID(String id)
    {
        this.id = id;
    }
    
    public void AddStationRouteInfo(StationRouteInfo temp)
    {
        stationRouteInfoList.add(temp);
    }
}
