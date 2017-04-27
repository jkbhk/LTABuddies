
package Buddy;

import java.util.ArrayList;


public class Route {
    
    private ArrayList<GenericStation> stations;
    
    public Route()
    {
        stations = new ArrayList<GenericStation>();
    }
    
    public ArrayList<GenericStation> GetStationsInRoute()
    {
        return this.stations;
    }
    
    public void AddStationToRoute(GenericStation genericStation)
    {
        this.stations.add(genericStation);
    }
    
    
    
    
}
