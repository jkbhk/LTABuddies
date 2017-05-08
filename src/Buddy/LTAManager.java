package Buddy;

import java.util.HashMap;
import java.util.*;

public final class LTAManager 
{
    public static HashMap<String, GenericStation> stationHashmap;
   
    private LTAManager()
    {}
    
    public static void init()
    {
        stationHashmap = new HashMap<>();
        GenericFileReader fileReader = new GenericFileReader();
        ArrayList<BusStation> allBusStations = fileReader.ReadBusStopCode();
        ArrayList<StationRouteInfo> allRouteInfo = fileReader.ReadRouteInfoCode();
        
        UpdateBusStationInfo(allBusStations, allRouteInfo);
        
        // Add to Dictionary
        for (int i = 0; i < allBusStations.size(); i++)
        {
            stationHashmap.put(allBusStations.get(i).GetID(), allBusStations.get(i));
            
        }
        System.out.println(allBusStations.get(1).GetID());
    }
    
    private static void UpdateBusStationInfo(ArrayList<BusStation> allBusStation, ArrayList<StationRouteInfo> allRouteInfo)
    {
        for (BusStation busStation : allBusStation) 
        {
            for (StationRouteInfo routeInfo : allRouteInfo)
            {
                if(routeInfo.getStationCode().equals(busStation.id))
                {
                    busStation.AddStationRouteInfo(routeInfo);
                }
            }
        }
    }
}
