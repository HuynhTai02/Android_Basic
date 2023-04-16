package com.example.broadcastexternalreceiver_ex;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.BatteryManager;
import android.provider.Settings;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Lấy ra giá trị của Action được gửi đến BroadcastReceiver thông qua đối tượng Intent
        String action = intent.getAction();

        if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(action)) {
            boolean airplaneModeEnabled = Settings.System.getInt(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0) == 1; // On
            if (airplaneModeEnabled) {
                Toast.makeText(context, "Air plane mode On", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Air plane mode Off", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
