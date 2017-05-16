
package Buddy;

import java.util.ArrayList;


public class Route 
{
    private ArrayList<StationRouteInfo> stations;
            
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
    
    public StationRouteInfo GetNextStationRouteInfo(int currentSequence)
    {
        return stations.get(currentSequence);
    }
}
