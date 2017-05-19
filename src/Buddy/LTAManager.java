package Buddy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.*;

public final class LTAManager 
{
    public static HashMap<String, GenericStation> StationHashmap;
    public static HashMap<String,Service> ServiceHashMap;
    public static HashMap<String, GenericStationLocationInfo> StationLocationHashmap;
   
    private LTAManager() {}
    
    public static void init()
    {
        StationHashmap = new HashMap<>();
        ServiceHashMap = new HashMap<>();
        StationLocationHashmap = new HashMap<>();
        
        GenericFileReader fileReader = new GenericFileReader();
        ArrayList<BusStation> allBusStations = fileReader.ReadBusStopCode();
        ArrayList<StationRouteInfo> allBusRouteInfo = fileReader.ReadBusRouteInfoCode();
        ArrayList<GenericStationLocationInfo> allBusStationLocation = fileReader.ReadStationLocation();
        
        UpdateBusStationInfo(allBusStations, allBusRouteInfo);
         
        // Populate BusStation to StationHashmap
        for (int i = 0; i < allBusStations.size(); i++)
        {
            StationHashmap.put(allBusStations.get(i).GetID(), allBusStations.get(i));
        }
        //UpdateInt(allBusRouteInfo);
        // Populate BusStationLocation to LocationHashmap
        for (int i = 0; i < allBusStationLocation.size(); i++)
        {
            StationLocationHashmap.put(allBusStationLocation.get(i).stationCode, allBusStationLocation.get(i));
        }
        // Populate BusServices to ServiceHashmap
        ArrayList<Service> allService = new ArrayList<>();
        Service tempService = new Service();
        Route someRoute = new Route();
 
        for (int i = 0; i < allBusRouteInfo.size(); i++)
        {
            StationRouteInfo currentSRI = allBusRouteInfo.get(i);
            StationRouteInfo nextSRI = null;
    
            if(i+1 < allBusRouteInfo.size())
                nextSRI = allBusRouteInfo.get(i+1);
    
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
    
//    public static void UpdateInt(ArrayList<StationRouteInfo> allSRI)    
//    {
//        ArrayList<StationRouteInfo> interchangeSRI = new ArrayList<>();
//        for(StationRouteInfo currentSRI : allSRI)
//        {
//            GenericStation currentGS = GetGenericStation(currentSRI.getStationCode());
//            
//            if (currentGS.name.toLowerCase().contains("int"))
//            {
//                interchangeSRI.add(currentSRI);
//            }
//        }
//        
//        for(StationRouteInfo firstSRI : interchangeSRI)
//        {                
//            GenericStation firstGS = GetGenericStation(firstSRI.getStationCode());
//            
//            for(StationRouteInfo nextSRI : interchangeSRI)
//            {
//                GenericStation nextGS = GetGenericStation(firstSRI.getStationCode());
//                
//                if(!firstSRI.equals(nextSRI) && firstGS.GetName().toLowerCase().equals(nextGS.GetName().toLowerCase()))
//                {
//                    firstGS.AddStationRouteInfo(new StationRouteInfo(firstSRI, nextGS.GetID()));
//                    nextGS.AddStationRouteInfo(new StationRouteInfo(nextSRI, firstGS.GetID()));
//                }
//            }
//        }
//    }
    
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
    

    public static double round(double value, int places) 
    {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
