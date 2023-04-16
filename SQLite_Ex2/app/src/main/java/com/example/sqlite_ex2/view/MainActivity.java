package com.example.sqlite_ex2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.sqlite_ex2.R;
import com.example.sqlite_ex2.databinding.ActivityMainBinding;
import com.example.sqlite_ex2.db.MyDBHelper;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    MyDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prePareDB();
    }

    private void prePareDB() {
        db = new MyDBHelper(MainActivity.this);
        db.createSampleData();
    }
}