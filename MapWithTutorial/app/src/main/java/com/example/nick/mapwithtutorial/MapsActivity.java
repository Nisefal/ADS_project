package com.example.nick.mapwithtutorial;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

import StaticFunctionality.MarketController;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Location my_loc;
    private LatLng currentPosition;

    private float prevScale; //previous map zoom; used in marker displayer
    private ArrayList<Marker> CurrentMarkers  = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapFragment.setRetainInstance(true);
            if(savedInstanceState != null) {
                currentPosition = new LatLng(savedInstanceState.getDouble("current_pos_lat"),
                        savedInstanceState.getDouble("current_pos_lng"));
            }
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */



    @Override
    public void onMapReady(GoogleMap googleMap) {
        if(mMap == null) {
            mMap = googleMap;
            mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
                @Override
                public void onCameraMove() {

                    float curr_scale = mMap.getCameraPosition().zoom;

                    if (prevScale != curr_scale) {
                        MarketController.MarkersDisplayer(curr_scale, CurrentMarkers, getApplication()); //WTF?!
                        prevScale = curr_scale;
                    } else return;

                }
            });
        }

        if(checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, this.getTaskId(),
                this.getBaseContext().getApplicationInfo().uid) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }

        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location arg0) {
                // TODO Auto-generated method stub
                my_loc = arg0;
                update_to_position();
            }
        });

        start_stats();
    }



    /**
     * Moves camera to start position || moves camera to last position
     */                                                                                             //SHIT~
    private void start_stats(){
        LatLng sydney = new LatLng(-34, 151);
        LatLng near_sydney = new LatLng(-34.05, 151);
        LatLng Kyiv = new LatLng(50.479490, 30.421043);

        Bitmap bitmap= BitmapFactory.decodeResource(this.getResources(),
                R.drawable.pic1);

        MarketController.addMark(bitmap, sydney, "11 dls", mMap, CurrentMarkers, this);
        MarketController.addMark(bitmap, near_sydney, "12 dls", mMap, CurrentMarkers, this);
        MarketController.addMark(bitmap, Kyiv, "12 hrn", mMap, CurrentMarkers, this);

        if(currentPosition == null)
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(18), 2000, null);
        prevScale = 18;
    }

    private void update_to_position(){                                                              //SHIT~
        Location me = my_loc;
        //
    }
}
