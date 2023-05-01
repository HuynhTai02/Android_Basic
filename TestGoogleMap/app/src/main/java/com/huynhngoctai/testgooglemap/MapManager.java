package com.huynhngoctai.testgooglemap;

import android.content.pm.PackageManager;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapManager {
    private static final String TAG = MapManager.class.getName();
    private static MapManager instance;
    private GoogleMap googleMap;

    private Marker myPos;

    public void setMap(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    private MapManager() {
        //singleton
    }

    public static MapManager getInstance() {
        if (instance == null) {
            instance = new MapManager();
        }

        return instance;
    }

    //Config and display Google Maps
    public void initMap() {
        // enable all gestures on the map
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        // display a button that centers the map on the user's current location when pressed.
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        // display buttons to control the zoom level of the map.
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        // set the type of the map
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (ActivityCompat.checkSelfPermission(App.getInstance(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(App.getInstance(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        // show the user's current location on the map.
        googleMap.setMyLocationEnabled(true);

        findMyLocation();
    }

    private void findMyLocation() {
        //create FusedLocationProviderClient object to get location information from GPS
        FusedLocationProviderClient client =
                LocationServices.getFusedLocationProviderClient(App.getInstance());

        if (ActivityCompat.checkSelfPermission(App.getInstance(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(App.getInstance(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        //LocationRequest object is created to request location updates
        LocationRequest req = new LocationRequest();
        //Time request location update
        req.setInterval(2000);
        req.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        // update location
        client.requestLocationUpdates(req, new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                //locationResult: contains current location of the device
                updateMyLocation(locationResult);
            }
        }, Looper.getMainLooper());

    }

    private void updateMyLocation(LocationResult locationResult) {
        double lat = locationResult.getLastLocation().getLatitude();
        double lgt = locationResult.getLastLocation().getLongitude();

        if (myPos == null) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title("I'm here");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker());
            markerOptions.position(new LatLng(lat, lgt));

            myPos = googleMap.addMarker(markerOptions);
            //Move to my location,zoom 16 times
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPos.getPosition(), 16));
        }
        if (myPos.getPosition().latitude != lat || myPos.getPosition().longitude != lgt) {
            myPos.setPosition(new LatLng(lat, lgt));
            //Move to my location,zoom 16 times
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPos.getPosition(), 16));
        }

        Log.i(TAG, "My pos: " + lat + " , " + lgt);
    }
}
