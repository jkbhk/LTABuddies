
package Buddy;

import java.util.ArrayList;


public class Route {
    
    private ArrayList<GenericStation> stations;
    
    //Constructor
    public Route()
    {
        stations = new ArrayList<>();
    }
    
    public ArrayList<GenericStation> GetStationsInRoute()
    {
        return this.stations;
    }
    
    public void AddStationToRoute(GenericStation genericStation)
    {
        stations.add(genericStation);
    }
    
    // Returns the total number of stations/nodes in the route
    public int TotalNumberOfStationsInRoute()
    {
        return stations.size();
    }
    
    // Returns the required number of stations/nodes to finish the route
    public int StationsFromDestination(GenericStation s)
    {
        return (stations.size()-1) - stations.indexOf(s);   
    }
    
    
    
    
}
