package com.tai.view_example_utc2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tai.view_example_utc2.databinding.ActivityImageBinding;

public class ImageActivity extends AppCompatActivity {
    private ActivityImageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
        clickClose();
    }

    private void addEvents() {
        binding.ibtChange.setOnClickListener(v -> {
            if (binding.imgPhoto.getTag() == null || binding.imgPhoto.getTag() == "dog") {
                binding.imgPhoto.setImageResource(R.drawable.ic_cat);
                binding.imgPhoto.setTag("cat");
            } else {
                binding.imgPhoto.setImageResource(R.drawable.ic_dog);
                binding.imgPhoto.setTag("dog");
            }
        });
    }

    private void clickClose() {
        binding.ibtClose.setOnClickListener(v -> finish());
    }
}