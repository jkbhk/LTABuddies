
package Buddy;

import java.util.ArrayList;


public class BusStation extends GenericStation{
    
    private String road;
    
    public BusStation(String name, String id, String road)
    {
        super(name,id);
        this.road = road;      
    }
    
    public String GetRoad()
    {
        return this.road;
    }
    
    public void SetRoad(String road)
    {
        this.road = road;
    }
    
    
    
    
}
