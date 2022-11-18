package com.tai.activity_lif;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    Button btSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btSecond = findViewById(R.id.bt_second);
        btSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Log.d("AAA", "onCreate Second");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("AAA", "onStart Second");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("AAA", "onStop Second");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("AAA", "onDestroy Second");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("AAA", "onPause Second");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("AAA", "onResume Second");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("AAA", "onRestart Second");

    }
}