package Buddy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GenericFileReader 
{
    public void ReadBusStopCode()
    {
        String filePath = "/lta-bus_stop_codes.csv";
        BufferedReader buffer = null;
        String line = "";
        String delimiter = ",";
        
        try
        {
            buffer = new BufferedReader(new FileReader(filePath));
            
            while ((line = buffer.readLine()) != null)
            {
                String[] busStop = line.split(delimiter);
                
                System.out.println("Bus Stop Code: " + busStop[0] + 
                        "\nBusRoute: " + busStop[1] +
                        "\nBusDesc:" + busStop[2]);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
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
    }
}
