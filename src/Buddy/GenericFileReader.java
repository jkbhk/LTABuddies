package Buddy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GenericFileReader 
{
    private final String busStopPath = "Data/lta-bus_stop_codes.csv";
    private final String routePath = "Data/lta-sbst_route.csv";;
    private final String delimiter = ",";
    private String line = "";
    private BufferedReader buffer;
    
    public ArrayList<BusStation> ReadBusStopCode()
    {
        ArrayList<BusStation> allBusStations = new ArrayList<>();
        
        try
        {
            buffer = new BufferedReader(new FileReader(busStopPath));
            
            while ((line = buffer.readLine()) != null)
            {
                String[] busStop = line.split(delimiter);
                
                if(busStop[0].contains("BUS_CODE"))
                {
                    continue;
                }
                
                allBusStations.add(new BusStation(busStop[2], busStop[0], busStop[1]));
            }
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            System.out.println("Bus Station Error");
        }
        finally
        {
            if (buffer != null)
            {
                try
                {
                    buffer.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        
        return allBusStations;
    }
    
    public ArrayList<StationRouteInfo> ReadRouteInfoCode()
    {
        ArrayList<StationRouteInfo> allRouteInfo = new ArrayList<>();
        
        int offset = 0;
        
        try
        {
            buffer = new BufferedReader(new FileReader(routePath));
            
            while ((line = buffer.readLine()) != null)
            {
                String[] myStrArr = line.split(delimiter);
                
                if(myStrArr[0].contains("SVC_NUM"))
                {
                    continue;
                }
                
                if (myStrArr[4].contains("-"))
                {
                    offset--;
                    continue;
                }
                else if (myStrArr[2].equals("1"))
                {
                    offset = 0;
                }
                
                StationRouteInfo newInfo = new StationRouteInfo(myStrArr[0], Integer.parseInt(myStrArr[1]), Integer.parseInt(myStrArr[2]) + offset,
                        myStrArr[3], Double.parseDouble(myStrArr[4]));
                allRouteInfo.add(newInfo);
                
//                System.out.println( newInfo.getServiceNo() + "," + newInfo.getDirection()+ "," + newInfo.getStationCode() + "," + newInfo.getRouteSequence() + "," + newInfo.getDistFromStart());
                 
                
            }
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            System.out.println("Route Info Error");
        }
        finally
        {
            if (buffer != null)
            {
                try
                {
                    buffer.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        
        return allRouteInfo;
    }
}
