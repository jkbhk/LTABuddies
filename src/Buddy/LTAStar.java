package Buddy;

import java.util.ArrayList;

public final class LTAStar 
{
    public static void FindPath(GenericStation start, GenericStation end)
    {
        PriorityQueue<GenericStation> openSet = new PriorityQueue<>();
        PriorityQueue<GenericStation> closeSet = new PriorityQueue<>();
        
        openSet.Add(start);
        
        while (openSet.Count()> 0)
        {
            GenericStation currentStation = openSet.First();
        }
//        ArrayList<StationRouteInfo> startStationRouteInfo = start.GetStationRouteInfoList();
//        ArrayList<StationRouteInfo> endStationRouteInfo = end.GetStationRouteInfoList();
//        
//        for (int i = 0; i < startStationRouteInfo.size(); i++) 
//        {
//            for (int j = 0; j < startStationRouteInfo.size(); j++) 
//            {
//                String eachStartSvcNo = startStationRouteInfo.get(i).getServiceNo();
//                String eachEndSvcNo = endStationRouteInfo.get(i).getServiceNo();
//                
//            }
//        }
    }
    
    public static void FindShortestPath()
    {
        
    }
}