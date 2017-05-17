package Buddy;

import java.util.ArrayList;

public abstract class GenericStation implements Comparable
{
    protected String name;
    protected String id; 
    protected ArrayList<StationRouteInfo> stationRouteInfoList = new ArrayList<>();
    
    public double startCost;   //G Cost
    public GenericStation parent;
    public StationRouteInfo parentInfo;

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

    public ArrayList<StationRouteInfo> GetStationRouteInfoList() {
        return stationRouteInfoList;
    }

    public void SetStationRouteInfoList(ArrayList<StationRouteInfo> stationRouteInfoList) {
        this.stationRouteInfoList = stationRouteInfoList;
    }
    
    public void AddStationRouteInfo(StationRouteInfo temp)
    {
        stationRouteInfoList.add(temp);
    }
    
    @Override
    public int compareTo(Object obj)
    {
        GenericStation node2 = (GenericStation)obj;
        if(startCost < node2.startCost)
        {
            return 1;
        }
        return -1;
    }
}
