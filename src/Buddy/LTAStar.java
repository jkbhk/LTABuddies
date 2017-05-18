package Buddy;

import java.util.ArrayList;
import java.util.Collections;

public final class LTAStar 
{
    public static final double busSpeed = 40.0; //km per hour
    public static final double transferTime = 2.0; // minutes;
    public static final double stationStopTime = 0.5; // minutes
    
    public static ArrayList<StationRouteInfo> FindPath(GenericStation start, GenericStation end, PathFindFactor selectedFactor)
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
                
                double newDistFromStartCost = currentStation.startCost;
                
                switch (selectedFactor)
                {
                    case BESTROUTE:
                    {
                        if(currentStation instanceof BusStation)
                        {
                            // Bus Travel Time
                            newDistFromStartCost += GetDistance(currentRouteInfo, nextStationRouteInfo)/ (busSpeed/60.0);
                        }
                        // Transfer Time
                        newDistFromStartCost += stationStopTime;
                        break;
                    }
                    case DISTANCE:
                    {
                        newDistFromStartCost += GetDistance(currentRouteInfo, nextStationRouteInfo);
                        break;
                    }
                    case SHORTESTNOOFSTATION:
                    {
                        newDistFromStartCost += 1;
                        break;
                    }                  
                    case LEASTTRANSFER:
                    {
                        if (currentStation.parentInfo != null && !currentRouteInfo.getServiceNo().equals(currentStation.parentInfo.getServiceNo()))
                        {
                            newDistFromStartCost += 1;
                        }
                        break;
                    }
                }
                
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
            if(i+1 < stationList.size() && stationList.get(i).parentInfo != null)
            {
                // Get Current SRI base on parent service number ( i = currentStation; i+1 = previousStation; )
                StationRouteInfo currentSRI = LTAManager.GetNextStationRouteInfo(stationList.get(i).parentInfo);
                sriArray.add(currentSRI);
            }
            if(i+1 < stationList.size() && stationList.get(i).parentInfo != null)
            {
                sriArray.add(stationList.get(i).parentInfo);
            }
        }
        
//        for(StationRouteInfo gs : sriArray)
//        {
//            System.out.println(gs.getStationCode());
//        }
//        
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
        
        for(GenericStation gs : path)
        {
            System.out.println(gs.name);
        }
        
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