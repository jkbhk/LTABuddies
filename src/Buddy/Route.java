
package Buddy;

import java.util.ArrayList;


public class Route 
{
    private ArrayList<StationRouteInfo> stations;

    public ArrayList<StationRouteInfo> getAllStationRouteInfo() {
        return stations;
    }
            
    //Constructor
    public Route(ArrayList<StationRouteInfo> stations)
    {
        this.stations = stations;
       
    }
    public Route()
    {
        stations = new ArrayList<>();
    }
    
    public ArrayList<StationRouteInfo> GetStationsToDistRef()
    {
        return this.stations;
    }
    
    public void AddStationToRef(StationRouteInfo obj)
    {
        stations.add(obj);
    }
    
    // Returns the total number of stations/nodes in the route
    public int TotalNumberOfStationsInRoute()
    {
        return stations.size();
    }
    
    public boolean ContainSubsequentStation(StationRouteInfo sri, GenericStation station)
    {
        for (int i = sri.getRouteSequence() - 1; i < stations.size(); i++)
        {
            if (station.GetID().equals(stations.get(i).getStationCode()))
            {
                return true;
            }
        }
        
        return false;
    }
    
    public StationRouteInfo GetNextStationRouteInfo(int currentSequence)
    {
        if (currentSequence < stations.size())
        {
             return stations.get(currentSequence);
        }
        
        return null;
    }
}
