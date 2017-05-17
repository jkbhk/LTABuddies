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
            
            for(StationRouteInfo currentRouteInfo : currentStation.GetStationRouteInfoList())
            {
                StationRouteInfo nextStationRouteInfo = LTAManager.GetNextStationRouteInfo(currentRouteInfo.getServiceNo(), currentRouteInfo.getDirection(), currentRouteInfo.getRouteSequence());
                
                if(nextStationRouteInfo == null)
                {
                    continue;
                }
                 
                GenericStation neighbourStation = LTAManager.GetGenericStation(nextStationRouteInfo.getStationCode());
                
                if (closeSet.Contains(neighbourStation))
                {
                    continue;
                }
                
                double newDistFromStartCost = currentStation.startCost + GetDistance(currentRouteInfo, nextStationRouteInfo);

//                if (currentStation.parentInfo != null && !currentRouteInfo.getServiceNo().equals(currentStation.parentInfo.getServiceNo()))
//                {
//                    newDistFromStartCost += 1;
//                }
                
                if (newDistFromStartCost < neighbourStation.startCost || !openSet.Contains(neighbourStation))
                {
                    neighbourStation.startCost = newDistFromStartCost;
                    neighbourStation.parent = currentStation;
                    neighbourStation.parentInfo = currentRouteInfo;
                    
                    if(!openSet.Contains(neighbourStation))
                    {
                        openSet.Add(neighbourStation);
                        openSet.Sort();
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
            
            ArrayList<StationRouteInfo> pathInfo = GetPathInfo(path);
            
            //Collections.reverse(pathInfo);
            return pathInfo;
        }
        
        
        return null;
    }
    
    private static ArrayList<StationRouteInfo> GetPathInfo(ArrayList<GenericStation> stationList)
    {
        ArrayList<StationRouteInfo> sriArray = new ArrayList<>();
        for(int i = 0; i < stationList.size(); i++)
        {
            if(stationList.get(i).parentInfo != null)
            {
                // Get Current SRI base on parent service number ( i = currentStation; i+1 = previousStation; )
                sriArray.add(LTAManager.GetNextStationRouteInfo(stationList.get(i).parentInfo));
            }
        }
        
        Collections.reverse(sriArray);
        
        return sriArray;
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