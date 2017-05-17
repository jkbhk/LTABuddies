/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buddy;

import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapViewOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.swing.MapView;

/**
 *
 * @author Hoshi
 */
public class LTAMap extends MapView {
    public LTAMap(MapViewOptions options) {
        super(options);
        setOnMapReadyHandler(new MapReadyHandler() 
        {
            @Override
            public void onMapReady(MapStatus status) {
                if (status == MapStatus.MAP_STATUS_OK) {
                    final Map map = getMap();
                    map.setZoom(11.0);
                    GeocoderRequest request = new GeocoderRequest(map);
                    request.setAddress("Singapore");

                    getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
                        @Override
                        public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
                            if (status == GeocoderStatus.OK) {
                                map.setCenter(result[0].getGeometry().getLocation());
//                                Marker marker = new Marker(map);
//                                marker.setPosition(result[0].getGeometry().getLocation());
//
//                                final InfoWindow window = new InfoWindow(map);
//                                window.setContent("Hello, World!");
//                                window.open(map, marker);
                            }
                        }
                    });
                }
            }
        });
    }   

    LTAMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
