package com.example.broadcastreceiver_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.example.broadcastreceiver_ex1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Todo Something
            ConnectivityManager connectivityManager =
                    (ConnectivityManager)context.getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo != null && networkInfo.isConnected()){
                if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
                    binding.imvState.setImageResource(R.drawable.baseline_wifi_24);
                    binding.txtState.setText("Connected with WIFI");
                }else if(networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
                    binding.imvState.setImageResource(R.drawable.baseline_signal_cellular_alt_24);
                    binding.txtState.setText("Connected with Mobile Data");
                }
            }else{
                binding.imvState.setImageResource(R.drawable.baseline_do_disturb_24);
                binding.txtState.setText("No internet connection");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
}