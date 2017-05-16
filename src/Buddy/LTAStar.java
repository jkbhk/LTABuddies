package Buddy;

import java.util.ArrayList;
import java.util.Collections;

public final class LTAStar 
{
    public static ArrayList<StationRouteInfo> FindPath(GenericStation start, GenericStation end)
    {
        System.out.println("Start");
        boolean isFound = false;
        
        PriorityQueue<GenericStation> openSet = new PriorityQueue<>();
        PriorityQueue<GenericStation> closeSet = new PriorityQueue<>();
        openSet.Add(start);
        while (openSet.Count()> 0)
        {
            GenericStation currentStation = openSet.First();
            
            openSet.Pop();
            closeSet.Add(currentStation);
            
            if (currentStation == end)
            {
                System.out.println("Found");
                isFound = true;
                break;
            }
            
            for(StationRouteInfo routeInfo : currentStation.GetStationRouteInfoList())
            {
                StationRouteInfo nextStationRouteInfo = LTAManager.GetNextStationRouteInfo(routeInfo.getServiceNo(), routeInfo.getDirection(), routeInfo.getRouteSequence());
                
                if(nextStationRouteInfo == null)
                {
                    continue;
                }
                 
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
        System.out.println("ED");
       
        
        if(isFound)
        { 
            ArrayList<GenericStation> path;
            System.out.println("GetPath");
            path = GetPath(start, end);
            System.out.println("End");
            
            
            ArrayList<StationRouteInfo> pathInfo = new ArrayList<>();
            
            for(int i = 0; i < path.size(); i++)
            {
                ArrayList<StationRouteInfo> pathStationRouteInfoList = path.get(i).GetStationRouteInfoList();
                for(int j = 0; j < pathStationRouteInfoList.size(); j++)
                {
                    StationRouteInfo nextSRI = pathStationRouteInfoList.get(j);
                    if (path.get(i).id.equals(nextSRI.getStationCode()))
                    {
                        pathInfo.add(nextSRI);
                        break;
                    }
                }
            }
            //Collections.reverse(pathInfo);
            return pathInfo;
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
        path.add(currentStation);
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