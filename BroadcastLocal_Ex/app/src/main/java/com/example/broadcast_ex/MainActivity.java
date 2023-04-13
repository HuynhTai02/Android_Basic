package com.example.broadcast_ex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        // Tạo bộ lọc filter để đăng ký lắng nghe Action
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        //Tạo BroadcastReceiver
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                    sayHello();
                } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                    sayGoodBye();

                } else if (intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
                    boolean airplaneModeEnabled = Settings.System.getInt(context.getContentResolver(),
                            Settings.Global.AIRPLANE_MODE_ON, 0) == 1; // On
                    if (airplaneModeEnabled) {
                        Toast.makeText(context, "Air plane mode On", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Air plane mode Off", Toast.LENGTH_SHORT).show();
                    }

                } else if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
                    int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1); // Mức pin hiện tại
                    int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1); // Mức pin tối đa
                    float batteryPct = level * 100 / (float) scale; // Mức pin còn lại
                    int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                    boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                            status == BatteryManager.BATTERY_STATUS_FULL;

                    if (isCharging) {
                        // The battery is charging, show the charging icon and battery level
                        Toast.makeText(context, "Charging " + (int) batteryPct + "%", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, (int) batteryPct + "%", Toast.LENGTH_SHORT).show();
                    }

                } else if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
                    int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
//                    WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//                    int wifiState = wifiManager.getWifiState();
//                    boolean wifiEnabled = wifiManager.isWifiEnabled();
                    if (wifiState == WifiManager.WIFI_STATE_ENABLED) {
                        Toast.makeText(context, "Wifi On", Toast.LENGTH_SHORT).show();
                    } else if (wifiState == WifiManager.WIFI_STATE_DISABLED) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Wifi is disabled");
                        builder.setMessage("Do you want to enable Wifi?");
                        builder.setPositiveButton("Enable", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Bật Wifi
                                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                                wifiManager.setWifiEnabled(true);

                                Toast.makeText(context, "Wifi Enable True", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.setNegativeButton("Cancel", null);
                        builder.show();
                    }
                }
            }
        };

        // Sau khi có bộ lọc và BroadcastReceiver
        // Tiến hành đăng ký lắng nghe
        try {
            registerReceiver(broadcastReceiver, filter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sayGoodBye() {
        MediaPlayer.create(this, R.raw.bye).start();
    }

    private void sayHello() {
        MediaPlayer.create(this, R.raw.hello).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }
}