package com.tai.gridview_practice.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.tai.gridview_practice.databinding.ActivityDetailBinding;

public class ActivityDetail extends AppCompatActivity {
    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvent();
        getData();
    }

    private void getData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name").toString();
        int idImg = intent.getIntExtra("idImg", 101);
        binding.txtBeerName.setText(name);
        binding.imvthump.setImageResource(idImg);
    }

    private void addEvent() {
        binding.btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityDetail.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
