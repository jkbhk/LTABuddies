package Buddy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GenericFileReader 
{
    public ArrayList<BusStation> ReadBusStopCode()
    {
        ArrayList<BusStation> allBusStations = new ArrayList<BusStation>();
        String filePath = "Data/lta-bus_stop_codes.csv";
        BufferedReader buffer = null;
        String line = "";
        String delimiter = ",";
        
        try
        {
            buffer = new BufferedReader(new FileReader(filePath));
            
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
}
