package com.tai.skeleton_shimmer;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.tai.skeleton_shimmer.databinding.ActivityMain210Binding;
import com.tai.skeleton_shimmer.databinding.ActivityMainBinding;

public class MainActivity2_1_0 extends AppCompatActivity {

    private ActivityMain210Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain210Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
    }

    private void initViews() {
        binding.shimmerLayout.startShimmerAnimation();
    }
}