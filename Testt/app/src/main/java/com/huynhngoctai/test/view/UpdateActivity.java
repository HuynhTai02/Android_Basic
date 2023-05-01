package com.huynhngoctai.test.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomDatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.huynhngoctai.test.database.entity.Tour;
import com.huynhngoctai.test.database.room.TourRoom;
import com.huynhngoctai.test.databinding.ActivityUpdateBinding;

public class UpdateActivity extends AppCompatActivity {
    private ActivityUpdateBinding binding;
    private Tour tour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        initViews();
    }

    private void initData() {
        tour = (Tour) getIntent().getExtras().get("object_tour");
        if (tour != null){
            binding.edtTourName.setText(tour.getTourName());
            binding.edtSchedule.setText(tour.getSchedule());
            binding.edtTourDescription.setText(tour.getTourDescription());
            binding.edtTourNumbGuests.setText(String.valueOf(tour.getNumbOfGuests()));
            binding.edtTourPrice.setText(String.format("%.2f",tour.getTourPrice()));
        }
    }

    private void initViews() {
        binding.btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTour();
            }});

        binding.btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void updateTour() {
        String tourName = binding.edtTourName.getText().toString().trim();
        String tourDes = binding.edtTourDescription.getText().toString().trim();
        int tourNumb = Integer.parseInt(binding.edtTourNumbGuests.getText().toString().trim());
        String tourSchedule = binding.edtSchedule.getText().toString().trim();
        double tourPrice = Double.parseDouble(binding.edtTourPrice.getText().toString().trim());

        if (TextUtils.isEmpty(tourName) || TextUtils.isEmpty(tourSchedule) || TextUtils.isEmpty(tourDes)) {
            return;
        }

        tour.setTourName(tourName);
        tour.setTourDescription(tourDes);
        tour.setSchedule(tourSchedule);
        tour.setNumbOfGuests(tourNumb);
        tour.setTourPrice(tourPrice);

        TourRoom.getInstance(this).tourDAO().editTour(tour);
        Toast.makeText(this,"Update Success",Toast.LENGTH_SHORT).show();

        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK, intentResult);
        finish();
    }
}