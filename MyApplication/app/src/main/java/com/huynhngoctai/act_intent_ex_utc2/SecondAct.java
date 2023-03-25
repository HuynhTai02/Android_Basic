package com.huynhngoctai.act_intent_ex_utc2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.huynhngoctai.act_intent_ex_utc2.R;

public class SecondAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Log.i("SecondAct", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("SecondAct", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("SecondAct", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("SecondAct", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("SecondAct", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("SecondAct", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("SecondAct", "onDestroy");
    }
}