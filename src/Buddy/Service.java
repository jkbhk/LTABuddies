package Buddy;

import java.util.ArrayList;

public class Service {

    private String serviceCode;
    private ArrayList<Route> route;
    
    public Service(String serviceCode, ArrayList<Route> route)
    {
        route = new ArrayList<>();
        this.serviceCode = serviceCode;
        this.route = route;
    }
    
    public Service(String serviceCode)
    {
        route = new ArrayList<>();
        this.serviceCode = serviceCode;
    }
    
    public Service()
    {
        route = new ArrayList<>();
    }
    
    public String GetServiceCode()
    {
        return this.serviceCode;
    }
    
    public void SetServiceCode(String serviceCode)
    {
        this.serviceCode = serviceCode;
    }
    
    public ArrayList<Route> GetRouteList()
    {
        return this.route;
    }
    
    public Route GetRoute(RouteDirection dir)
    {
        return this.route.get(dir.ordinal());
    }
    
    public Route GetRoute(int i)
    {
        return this.route.get(i);
    }
    
}
