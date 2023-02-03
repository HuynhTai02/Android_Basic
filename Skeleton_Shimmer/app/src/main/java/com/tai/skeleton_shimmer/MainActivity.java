package com.tai.skeleton_shimmer;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.tai.skeleton_shimmer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
    }

    private void initViews() {

        binding.dataView.setVisibility(View.INVISIBLE);
        binding.shimmerView.startShimmer();

        new Handler().postDelayed(() -> {
            binding.dataView.setVisibility(View.VISIBLE);
            binding.shimmerView.stopShimmer();
            binding.shimmerView.setVisibility(View.INVISIBLE);
        }, 5000);

    }
}