package com.example.nick.mapwithtutorial;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


/**
 * Created by Nick on 07.11.2017.
 */

public class MarketController {

    public static void addMark(Bitmap bit, LatLng lg, String price, GoogleMap map, ArrayList<Marker> CurrentMarkersList, Context it) {
        try {
            if(bit == null) {return;}
            if(lg == null) {return;}
            if(price == null || price == "") {return;}
            if(map == null) {return;}

            BitmapDescriptor pic = BitmapDescriptorFactory.fromBitmap(Croper.cropCircle(bit));

            Marker tmp = map.addMarker(new MarkerOptions()
                    .position(lg)
                    .title(price)
                    .icon(pic));
            CurrentMarkersList.add(tmp);
        }
        catch(Exception e){
            Toast.makeText(it.getApplicationContext(), "KURVA! Exception in addMark!", Toast.LENGTH_LONG).show();
        }
    }

    public static void MarkersDisplayer(float zoom_lvl, ArrayList<Marker> CurrentMarkersList, Context it) { //it for messaging
        try {
            float max_lvl = 14; // level of zoom when marks appear/disappear; 18 - close, 14 - far
            if (zoom_lvl < max_lvl) {
                for (Marker m : CurrentMarkersList) {
                    m.setVisible(false);
                }
            } else {
                for (Marker m : CurrentMarkersList) {
                    m.setVisible(true);
                }
            }
        }
        catch(Exception e) {
        Toast.makeText(it.getApplicationContext(), "KURVA! Exception in MarkersDisplayer!", Toast.LENGTH_LONG).show();
        }
    }
}
