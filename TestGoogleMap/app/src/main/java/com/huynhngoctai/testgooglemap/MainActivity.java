package com.huynhngoctai.testgooglemap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class MainActivity extends AppCompatActivity implements OnActionCallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapFragment);

        //Phương thức dùng để lấy đối tượng GoogleMap để hiển thị bản đồ trên app
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                //Thực hiện việc lưu trữ và "set" giá trị GoogleMap đã lấy được vào đối tượng MapManager
                MapManager.getInstance().setMap(googleMap);
                //Khởi tạo và thiết lập bản đồ
                MapManager.getInstance().initMap();
                MapManager.getInstance().setCallBack(MainActivity.this);
            }
        });

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 101);
        }
    }

    @Override
    public void showAlertDialog(String distance, LatLng start, LatLng end) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Notification");
        alertDialog.setMessage("To that about: " + distance);
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Direct", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showDirection(start, end);
            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertDialog.show();
    }

    //Key: how to show google map direction using intent
    private void showDirection(LatLng start, LatLng end) {
        String text = String.format("http://maps.google.com/maps?saddr=%s,%s&daddr=%s,%s"
                , start.latitude, start.longitude, end.latitude, end.latitude);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(text));
        startActivity(intent);
    }
}