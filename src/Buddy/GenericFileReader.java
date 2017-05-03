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
                
//                System.out.println("Bus Stop Code: " + busStop[0] + 
//                        "\nBusRoute: " + busStop[1] +
//                        "\nBusDesc:" + busStop[2] + "\n");
            }
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            System.out.println("Error");
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
    
     public ArrayList<Route> ReadRouteCode()
    {
        ArrayList<Route> allRoutes = new ArrayList<>();
        
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
                
                //TO DO
                //myStrArr.add(new Route(myStrArr[2], myStrArr[0], myStrArr[1]));
                
            }
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            System.out.println("Error");
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
        
        return allRoutes;
    }
}
