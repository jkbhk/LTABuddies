/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buddy;

public class StationToDistRef
{
    private GenericStation gStation;
    private double distanceFromStart;

    public StationToDistRef(GenericStation gStation, double distanceFromStart)
    {
        this.gStation = gStation;
        this.distanceFromStart = distanceFromStart;
    }

    public GenericStation getgStation() {
        return gStation;
    }

    public void setgStation(GenericStation gStation) {
        this.gStation = gStation;
    }

    public double getDistanceFromStart() {
        return distanceFromStart;
    }

    public void setDistanceFromStart(double distanceFromStart) {
        this.distanceFromStart = distanceFromStart;
    }
    
    

}