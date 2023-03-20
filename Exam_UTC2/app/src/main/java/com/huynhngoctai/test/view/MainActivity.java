package com.huynhngoctai.test.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.huynhngoctai.test.R;
import com.huynhngoctai.test.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgAnsang.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, RestaurantActivity.class);
            intent.putExtra("type","LUNCH_BOX");
            intent.putExtra("idImg", R.drawable.breakfast_banner);
            startActivity(intent);
        });

        binding.imgTrua.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, RestaurantActivity.class);
            intent.putExtra("idImg", R.drawable.lunch_banner);
            intent.putExtra("type","NOODLE");
            startActivity(intent);
        });
    }
}