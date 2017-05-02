package Buddy;

import java.util.ArrayList;


public class Service {

    private String serviceCode;
    private ArrayList<Route> route;
    
    public Service(String serviceCode, ArrayList<Route> route)
    {
        this.serviceCode = serviceCode;
        this.route = route;
    }
    
    public String GetServiceCode()
    {
        return this.serviceCode;
    }
    
    public void SetServiceCode(String serviceCode)
    {
        this.serviceCode = serviceCode;
    }
    
    public ArrayList<Route> GetRoute()
    {
        return this.route;
    }
    
}
