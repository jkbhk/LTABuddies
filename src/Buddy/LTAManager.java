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
                tempService.GetRouteList().add(someRoute);

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
    
    public static GenericStation GetGenericStation(String stationCode)
    {
        if(StationHashmap.containsKey(stationCode))
        {
            return StationHashmap.get(stationCode);
        }
        else
        {
            System.out.println("Invalid Station Code");
            return null;
        }
    }
    
        
    public static Service GetService(String serviceNo)
    {
        if(ServiceHashMap.containsKey(serviceNo))
        {
            return ServiceHashMap.get(serviceNo);
        }
        else
        {
            System.out.println("Invalid Service Code");
            return null;
        }
    }
    
    public static boolean ContainsStationInRoute(StationRouteInfo sri, GenericStation station)
    {
        Route sriRoute = ServiceHashMap.get(sri.getServiceNo()).GetRoute(sri.getDirection() - 1);
        return sriRoute.ContainSubsequentStation(sri,station);
    }
    
    public static StationRouteInfo GetNextStationRouteInfo(String serviceNo, int direction , int stationSequence)
    {
        if(ServiceHashMap.containsKey(serviceNo))
        {
            Service stationService = ServiceHashMap.get(serviceNo);
            Route stationRoute = stationService.GetRoute(direction - 1);
            
            StationRouteInfo nextStationRouteInfo = stationRoute.GetNextStationRouteInfo(stationSequence);
            
            return nextStationRouteInfo;
        }
        else
        {
            System.out.println("Invalid Service No");
            return null;
        }

    }
    
    public static StationRouteInfo GetNextStationRouteInfo(StationRouteInfo curSRI)
    {
        if(ServiceHashMap.containsKey(curSRI.getServiceNo()))
        {
            Service stationService = ServiceHashMap.get(curSRI.getServiceNo());
            Route stationRoute = stationService.GetRoute(curSRI.getDirection() - 1);
            
            StationRouteInfo nextStationRouteInfo = stationRoute.GetNextStationRouteInfo(curSRI.getRouteSequence());
            
            return nextStationRouteInfo;
        }
        else
        {
            System.out.println("Invalid Service No");
            return null;
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
