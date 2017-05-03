package Buddy;

import java.util.HashMap;
import java.util.*;

public class LTAManager 
{
    public HashMap<String, GenericStation> stationHashmap;
   
    public LTAManager()
    {
        stationHashmap = new HashMap<>();
        GenericFileReader fileReader = new GenericFileReader();
        ArrayList<BusStation> allBusStations = fileReader.ReadBusStopCode();
        
        for (int i = 0; i < allBusStations.size(); i++)
        {
            stationHashmap.put(allBusStations.get(i).GetID(), allBusStations.get(i));
//          System.out.println(allBusStations.get(1).GetID());
        }
        
        
        System.out.println(stationHashmap.get("LEIS9").GetName());
    }
}
