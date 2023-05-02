package com.huynhngoctai.testgooglemap;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapManager implements GoogleMap.OnInfoWindowClickListener {
    private static final String TAG = MapManager.class.getName();
    private static MapManager instance;
    private GoogleMap googleMap;

    //Lưu trữ vị trí hiện tại của người dùng trên bản đồ(đánh dấu)
    private Marker myPos;
    private List<PlaceEntity> placeEntityList;
    private OnActionCallBack mCallBack;

    public void setCallBack(OnActionCallBack mCallBack) {
        this.mCallBack = mCallBack;
    }

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
        //InfoWindowAdapter là một interface định nghĩa cách hiển thị nội dung cho info window trên Google Maps
        //Khi click vào một marker trên Google Maps
        // -> info window sẽ được hiển thị với các nội dung được thiết lập trong getInfoWindow() và getInfoContents().
        googleMap.setInfoWindowAdapter(initWindow());
        googleMap.setOnInfoWindowClickListener(this);

        //request permission
        if (ActivityCompat.checkSelfPermission(App.getInstance()
                , android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(App.getInstance()
                , android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // show the user's current location on the map.
        googleMap.setMyLocationEnabled(true);

        findMyLocation();

        //dummy data
        initPlaces();
        addPlaceToMap();
    }

    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {
        PlaceEntity place = (PlaceEntity) marker.getTag();
        LatLng end = place.getLocation();
        LatLng start = myPos.getPosition();

        String distance = calcDistance(start, end);
        mCallBack.showAlertDialog(distance, start, end);
    }

    //Key: calculate distance from latitude and longtitude
    private String calcDistance(LatLng start, LatLng end) {

        double lat1 = start.latitude;
        double lat2 = end.latitude;

        double lgn1 = start.longitude;
        double lgn2 = end.longitude;

        double R = 6371; // Radius of the earth in km
        double dLat = deg2rad(lat2 - lat1);
        double dLgn = deg2rad(lgn2 - lgn1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
                * Math.sin(dLgn / 2) * Math.sin(dLgn / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c; // Distance in km
        return new DecimalFormat("#.#").format(d) + "km";
    }

    double deg2rad(double deg) {
        return deg * (Math.PI / 180);
    }

    private GoogleMap.InfoWindowAdapter initWindow() {
        return new GoogleMap.InfoWindowAdapter() {
            @Nullable
            @Override
            public View getInfoContents(@NonNull Marker marker) {
                // Trả về View hiển thị info window với các thông tin tùy chỉnh
                return initViewAdapter(marker);
            }

            @Nullable
            @Override
            public View getInfoWindow(@NonNull Marker marker) {
                // Trả về View hiển thị các nội dung của info window
                return initViewAdapter(marker);
            }
        };
    }

    private View initViewAdapter(Marker marker) {
        if (marker.getTag() == null) {
            return null;
        }

        PlaceEntity place = (PlaceEntity) marker.getTag();
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(App.getInstance()).inflate(R.layout.item_infomap, null);
        ImageView ivPlace = view.findViewById(R.id.ivPlace);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvAddress = view.findViewById(R.id.tvAddress);
        TextView tvDescription = view.findViewById(R.id.tvDescription);

        ivPlace.setImageResource(place.getPhotoBG());
        tvName.setText(place.getName());
        tvAddress.setText(place.getAddress());
        tvDescription.setText(place.getDescription());

        return view;
    }

    private void initPlaces() {
        placeEntityList = new ArrayList<>();

        placeEntityList.add(new PlaceEntity(App.getInstance().getString(R.string.txt_dinh_doc_lap), App.getInstance().getString(R.string.txt_dinh_doc_lap_address), App.getInstance().getString(R.string.txt_dinh_doc_lap_description), R.drawable.img_dinh_doc_lap, new LatLng(10.77718918263018, 106.69579562646025)));

        placeEntityList.add(new PlaceEntity(App.getInstance().getString(R.string.txt_zoo), App.getInstance().getString(R.string.txt_zoo_address), App.getInstance().getString(R.string.txt_zoo_description), R.drawable.img_thao_cam_vien, new LatLng(10.787748915021973, 106.70659951800334)));

        placeEntityList.add(new PlaceEntity(App.getInstance().getString(R.string.txt_bitexco), App.getInstance().getString(R.string.txt_bitexco_address), App.getInstance().getString(R.string.txt_bitexco_description), R.drawable.img_bitexco, new LatLng(10.771922628409296, 106.70483730734219)));
    }

    private void findMyLocation() {
        //FusedLocationProviderClient là đối tượng được cung cấp bởi Google Play Services
        // cho phép ứng dụng định vị vị trí hiện tại của người dùng
        // thông qua: GPS,3G-4G,Wifi,sim sóng điện thoại
        //create FusedLocationProviderClient object to get location information from GPS
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(App.getInstance());

        if (ActivityCompat.checkSelfPermission(App.getInstance(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(App.getInstance(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

            String address = getAddress(lat, lgt);
            markerOptions.snippet(address);

            //Thêm Marker vào Map và gán giá trị cho myPos
            myPos = googleMap.addMarker(markerOptions);
            //Move to my location,zoom 16 times
            if (myPos != null) {
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPos.getPosition(), 16));
            }
        }

        if (myPos != null && (myPos.getPosition().latitude != lat || myPos.getPosition().longitude != lgt)) {
            myPos.setPosition(new LatLng(lat, lgt));

            //Lấy ra địa chỉ hiện tại người dùng
            // thông qua vị trí toạ độ trên map (kinh độ và vĩ độ)
            String address = getAddress(lat, lgt);
            //Hiển thị địa chỉ của vị trí hiện tại người dùng được đánh dấu trên map
            // khi người dùng chạm vào mục đánh dấu.
            myPos.setSnippet(address);

            //Move to my location,zoom 16 times
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPos.getPosition(), 16));
        }

        Log.i(TAG, "My pos: " + lat + " , " + lgt);
    }

    private String getAddress(double lat, double lgt) {
        try {
            Geocoder geocoder = new Geocoder(App.getInstance(), Locale.getDefault());
            List<Address> result = geocoder.getFromLocation(lat, lgt, 1);
            if (result != null && !result.isEmpty()) {
                return result.get(0).getAddressLine(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Unknown";
    }

    private void addPlaceToMap() {
        BitmapDescriptor iconPlace = BitmapDescriptorFactory.fromResource(R.drawable.ic_place);
        for (PlaceEntity place : placeEntityList) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title(place.getName());
            markerOptions.snippet(place.getAddress());
            markerOptions.icon(iconPlace);
            markerOptions.position(place.getLocation());

            Marker marker = googleMap.addMarker(markerOptions);
            assert marker != null;
            marker.setTag(place);
        }
    }
}
