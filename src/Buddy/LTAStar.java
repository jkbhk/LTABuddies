package Buddy;

import java.util.ArrayList;
import java.util.Collections;

public final class LTAStar 
{
    public static ArrayList<GenericStation> FindPath(GenericStation start, GenericStation end)
    {
        boolean isFound = false;
        
        PriorityQueue<GenericStation> openSet = new PriorityQueue<>();
        PriorityQueue<GenericStation> closeSet = new PriorityQueue<>();
        
        openSet.Add(start);
        
        while (openSet.Count()> 0)
        {
            GenericStation currentStation = openSet.First();
                       
            if (currentStation == end)
            {
                isFound = true;
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
        
        ArrayList<GenericStation> path = new ArrayList<>();
        
        if(isFound)
        {
            path = GetPath(start, end);
            
            return path;
        }
        
        
        return null;
    }
    
    private static ArrayList<GenericStation> GetPath(GenericStation start, GenericStation end)
    {
        ArrayList<GenericStation> path = new ArrayList<>();
        
        GenericStation currentStation = end;
        
        while (currentStation != start)
        {
            path.add(currentStation);
            currentStation = currentStation.parent;
        }
        
        Collections.reverse(path);
        
        return path;
    }
    
    private static double GetDistance(StationRouteInfo start, StationRouteInfo end)
    {
        return end.getDistFromStart() - start.getDistFromStart();
    }
    
    public static void FindShortestPath()
    {
        
    }
}