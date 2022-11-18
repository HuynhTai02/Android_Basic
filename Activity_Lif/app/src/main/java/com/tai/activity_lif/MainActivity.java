package com.tai.activity_lif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btMain = (Button) findViewById(R.id.bt_main);
        btMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
        Log.d("AAA","onCreate Main");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("AAA","onStart Main");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("AAA","onStop Main");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("AAA","onDestroy Main");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("AAA","onPause Main");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("AAA","onResume Main");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("AAA","onRestart Main");

    }
}