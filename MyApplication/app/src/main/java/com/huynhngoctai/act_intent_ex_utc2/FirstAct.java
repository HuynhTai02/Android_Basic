package com.huynhngoctai.act_intent_ex_utc2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.huynhngoctai.act_intent_ex_utc2.databinding.ActivityFirstBinding;

public class FirstAct extends AppCompatActivity {
    private ActivityFirstBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.i("FirstAct", "onCreate");
        addEvents();
    }

    private void addEvents() {
        binding.btBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstAct.this, MainActivity.class);
                //intent.setClass(FirstAct.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("FirstAct", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("FirstAct", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("FirstAct", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("FirstAct", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("FirstAct", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("FirstAct", "onDestroy");
    }
}