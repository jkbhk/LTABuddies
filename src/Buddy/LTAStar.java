package Buddy;

import java.util.ArrayList;

public final class LTAStar 
{
    public static ArrayList<GenericStation> FindPath(GenericStation start, GenericStation end)
    {
        PriorityQueue<GenericStation> openSet = new PriorityQueue<>();
        PriorityQueue<GenericStation> closeSet = new PriorityQueue<>();
        
        openSet.Add(start);
        
        while (openSet.Count()> 0)
        {
            GenericStation currentStation = openSet.First();
                       
            if (currentStation == end)
            {
                break;
            }
            
            for(StationRouteInfo routeInfo : currentStation.GetStationRouteInfoList())
            {
                
                StationRouteInfo nextStationRouteInfo = LTAManager.GetNextStationRouteInfo(routeInfo.getServiceNo(), routeInfo.getDirection(), routeInfo.getRouteSequence());

                GenericStation nextStation = LTAManager.GetGenericStation(nextStationRouteInfo.getStationCode());
                
                if (closeSet.Contains(nextStation))
                {
                    continue;
                }
                
                double newDistFromStartCost = currentStation.distFromStartPoint + GetDistance(routeInfo, nextStationRouteInfo);

                if (newDistFromStartCost < nextStation.distFromStartPoint || !openSet.Contains(nextStation))
                {
                    nextStation.distFromStartPoint = newDistFromStartCost;
                    nextStation.parent = currentStation;
                    
                    if(!openSet.Contains(nextStation))
                    {
                        openSet.Add(nextStation);
                    }
                    else
                    {
                        openSet.Sort();
                    }
                }
            }
        }
        
        
        
    }
    
    private static double GetDistance(StationRouteInfo start, StationRouteInfo end)
    {
        return end.getDistFromStart() - start.getDistFromStart();
    }
    
    public static void FindShortestPath()
    {
        
    }
}