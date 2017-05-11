package Buddy;

import java.util.HashMap;
import java.util.*;

public final class LTAManager 
{
    public static HashMap<String, GenericStation> StationHashmap;
    public static HashMap<String,Service> ServiceHashMap;
   
    private LTAManager() {}
    
    public static void init()
    {
        StationHashmap = new HashMap<>();
        ServiceHashMap = new HashMap<>();
        GenericFileReader fileReader = new GenericFileReader();
        ArrayList<BusStation> allBusStations = fileReader.ReadBusStopCode();
        ArrayList<StationRouteInfo> allRouteInfo = fileReader.ReadRouteInfoCode();
        
        UpdateBusStationInfo(allBusStations, allRouteInfo);
        
        // Populate hashmaps
        for (int i = 0; i < allBusStations.size(); i++)
        {
            StationHashmap.put(allBusStations.get(i).GetID(), allBusStations.get(i));
        }
        
        
        ArrayList<Service> allService = new ArrayList<>();
        Service tempService = new Service();
        Route someRoute = new Route();
 
        for (int i = 0; i < allRouteInfo.size(); i++)
        {
            StationRouteInfo currentSRI = allRouteInfo.get(i);
            StationRouteInfo nextSRI = null;
    
            if(i+1 < allRouteInfo.size())
                nextSRI = allRouteInfo.get(i+1);
    
            someRoute.GetStationsToDistRef().add(currentSRI);  
 
    
            if(nextSRI == null || nextSRI.getRouteSequence() < currentSRI.getRouteSequence())
            {
                tempService.GetRoute().add(someRoute);

                someRoute = new Route();

                if(nextSRI == null || !currentSRI.getServiceNo().equals(nextSRI.getServiceNo()))
                {
                    tempService.SetServiceCode(currentSRI.getServiceNo());
                    allService.add(tempService);
                    ServiceHashMap.put(tempService.GetServiceCode(), tempService);
                    tempService = new Service();
                }
            }
        }
           
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
