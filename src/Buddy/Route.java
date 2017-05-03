
package Buddy;

import java.util.ArrayList;


public class Route {
    

            
    private ArrayList<StationToDistRef> stations;
            
    //Constructor
    public Route()
    {
        stations = new ArrayList<>();
    }
    
    public ArrayList<StationToDistRef> GetStationsToDistRef()
    {
        return this.stations;
    }
    
    
    
    public void AddStationToRef(StationToDistRef obj)
    {
        stations.add(obj);
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
