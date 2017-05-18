/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buddy;

/**
 *
 * @author Hoshi
 */
public class GenericStationLocationInfo 
{
    public String ZID;
    public Vector2 position;
    public String stationCode;
    
    public GenericStationLocationInfo()
    {
        position.x = 0;
        position.y = 0;
    }
    
    public GenericStationLocationInfo(String ZID, Vector2 position, String stationCode)
    {
        this.ZID = ZID;
        this.position = position;
        this.stationCode = stationCode;
    }
    
}
