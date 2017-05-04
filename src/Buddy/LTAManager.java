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
        ArrayList<StationRouteInfo> allRouteInfo = fileReader.ReadRouteInfoCode();
        
        UpdateBusStationInfo(allBusStations, allRouteInfo);
        
        // Add to Dictionary
        for (int i = 0; i < allBusStations.size(); i++)
        {
            stationHashmap.put(allBusStations.get(i).GetID(), allBusStations.get(i));
            
        }
        System.out.println(allBusStations.get(1).GetID());
        
    }
    
    private void UpdateBusStationInfo(ArrayList<BusStation> allBusStation, ArrayList<StationRouteInfo> allRouteInfo)
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
