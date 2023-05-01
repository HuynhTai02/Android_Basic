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

    //Lưu trữ vị trí hiện tại của người dùng trên bản đồ
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
        // - kích hoạt tất cả các cử chỉ(hành động) mà người dùng có thể tương tác trong gg map
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        // display a button that centers the map on the user's current location when pressed.
        // - Bật nút vị trí của tôi(My Location) trên gg map
        // -> Khi nhấn vào nó -> giúp người dùng chuyển đến vị trí hiện tại tại trên bản đồ
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        // display buttons to control the zoom level of the map.
        // - Hiển thị nút thu nhỏ(-) - phóng to (+)
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        // set the type of the map
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //request permission
        if (ActivityCompat.checkSelfPermission(App.getInstance(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(App.getInstance(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // show the user's current location on the map.
        googleMap.setMyLocationEnabled(true);

        findMyLocation();
    }

    private void findMyLocation() {
        //FusedLocationProviderClient là đối tượng được cung cấp bởi Google Play Services
        // cho phép ứng dụng định vị vị trí hiện tại của người dùng
        // thông qua: GPS,3G-4G,Wifi,sim sóng điện thoại
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

        //LocationRequest object is created to request location updates from FusedLocationProviderClient
        LocationRequest req = new LocationRequest();
        //Time request location update
        //Tiêu chí 1:Thời gian (đặt khoảng thời gian giữa các lần cập nhật vị trí)
        req.setInterval(2000);
        //Tiêu chí 2: Yêu cầu cập nhật vị trí khi khoảng cách điểm hiện tại đến điểm tiếp theo vượt quá giá trị khoảng cách được đặt
        //Ví dụ: 20m thì cập nhật lại
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
        //Kinh độ
        double lat = locationResult.getLastLocation().getLatitude();
        //Vĩ độ
        double lgt = locationResult.getLastLocation().getLongitude();

        if (myPos == null) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title("I'm here");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker());
            //Đặt vị trí Marker là vị trí hiện tại người dùng
            markerOptions.position(new LatLng(lat, lgt));
            //Thêm Marker vào Map và gán giá trị cho myPos
            myPos = googleMap.addMarker(markerOptions);
            //Move to my location,zoom 16 times
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPos.getPosition(), 16));
        }
        if (myPos.getPosition().latitude != lat || myPos.getPosition().longitude != lgt) {
            myPos.setPosition(new LatLng(lat, lgt));
            //Move to my location,zoom 16 times
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPos.getPosition(), 16));
        }
    }
}
