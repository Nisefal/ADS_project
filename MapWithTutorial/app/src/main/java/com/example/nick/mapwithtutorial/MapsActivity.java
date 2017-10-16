package com.example.nick.mapwithtutorial;

import android.Manifest;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.filterTouchesWhenObscured;
import static android.R.attr.icon;
import static android.R.attr.radius;
import static android.R.attr.title;
import static android.R.attr.width;
import static com.example.nick.mapwithtutorial.R.attr.height;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private NotificationCompat.Builder sys_mesg = new NotificationCompat.Builder(this)
    .setSmallIcon(R.drawable.pic1)
    .setContentTitle("My notification")
    .setContentText("Hello World!");

    private GoogleMap mMap;
    private ArrayList<Marker> CurrentMarkers  = new ArrayList<>();
    private Location my_loc;

    private float prevScale;
    private LatLng currentPosition;
    private boolean marker_state = true; // true - is visible; false - is unvisible

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapFragment.setRetainInstance(true);

            //sys_mesg.setContentText("onCreate");
            //sys_mesg.build();
            //sys_mesg.notify();
            if(savedInstanceState != null) {
                currentPosition = new LatLng(savedInstanceState.getDouble("current_pos_lat"), savedInstanceState.getDouble("current_pos_lng"));
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

    public Bitmap cropCircle(Bitmap bitmap){
        bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, false);
        int _width = bitmap.getWidth(), _height = bitmap.getHeight();
        Bitmap out = Bitmap.createBitmap(_width, _height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        canvas.setBitmap(out);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);
        canvas.drawCircle(_width / 2, _height / 2, 50, paint);
        return out;
    }

    public Bitmap cropCircle(int id){
        Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(),
                id), 100, 100, false);
        int _width = bitmap.getWidth(), _height = bitmap.getHeight();
        Bitmap out = Bitmap.createBitmap(_width, _height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        canvas.setBitmap(out);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);
        canvas.drawCircle(_width / 2, _height / 2, 50, paint);
        return out;
    }

    private boolean MarkersDisplayer(float zoom_lvl){
        float max_lvl = 14; // level of zoom when marks appear/disappear
        if(zoom_lvl < max_lvl){
            marker_state = false;
            for (Marker m: CurrentMarkers) {
                m.setVisible(false);
            }
        }
        else{
            if(!marker_state) {
                marker_state = true;
            }
            if(zoom_lvl >= max_lvl){
                for (Marker m: CurrentMarkers) {
                    m.setVisible(true);
                }
            }
        }
        return true;
    }

    private void addMark(Bitmap bit, LatLng lg, String price, GoogleMap map) {
        try {
            if(bit == null) {return;}
            if(lg == null) {return;}
            if(price == null || price == "") {return;}
            if(map == null) {return;}

            BitmapDescriptor pic = BitmapDescriptorFactory.fromBitmap(cropCircle(bit));

            Marker tmp = map.addMarker(new MarkerOptions()
                    .position(lg)
                    .title(price)
                    .icon(pic));
            CurrentMarkers.add(tmp);
        }
    catch(Exception e){
        Toast.makeText(getApplicationContext(), "KURVA! Exception in addMark!", Toast.LENGTH_LONG).show();
    }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        if(mMap == null) {
            mMap = googleMap;
            mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
                @Override
                public void onCameraMove() {
                    float curr_scale = mMap.getCameraPosition().zoom;
                    //currentPosition = mMap.getCameraPosition().target;
                    if (prevScale != curr_scale) {
                        MarkersDisplayer(curr_scale);
                        prevScale = curr_scale;
                    } else return;
                }
            });
        }
        // Add a marker in Sydney and move the camera
        start_stats();


        mMap.setMyLocationEnabled(true);

        //Location m = mMap.getMyLocation();

        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {

            @Override
            public void onMyLocationChange(Location arg0) {
                // TODO Auto-generated method stub
                my_loc = arg0;
                update_to_position();
            }
        });
    }

    public Location getMy_loc(){
        return my_loc;
    }


    /**
     * Moves camera to start position || moves camera to last position
     */
    private void start_stats(){
        LatLng sydney = new LatLng(-34, 151);
        LatLng near_sydney = new LatLng(-34.05, 151);
        LatLng Kyiv = new LatLng(50.479490, 30.421043);

        Bitmap bitmap= BitmapFactory.decodeResource(this.getResources(),
                R.drawable.pic1);

        addMark(bitmap, sydney, "11 dls", mMap);
        addMark(bitmap, near_sydney, "12 dls", mMap);
        addMark(bitmap, Kyiv, "12 hrn", mMap);

        if(currentPosition == null)
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(18), 2000, null);
        prevScale = 18;
    }

    private void update_to_position(){
        Location _my = getMy_loc();
        //mMap.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(), arg0.getLongitude())).title("It's Me!"));
    }
}
