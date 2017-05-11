package Buddy;

import java.util.ArrayList;

public abstract class GenericStation implements Comparable
{
    protected String name;
    protected String id; 
    protected ArrayList<StationRouteInfo> stationRouteInfoList = new ArrayList<>();
    
    public float distFromStartPoint;   //G Cost
    public GenericStation parent; 

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
    
    public int compareTo(Object obj)
    {
        GenericStation node2 = (GenericStation)obj;
        if(distFromStartPoint < node2.distFromStartPoint)
        {
            return 1;
        }
        return -1;
    }
}
