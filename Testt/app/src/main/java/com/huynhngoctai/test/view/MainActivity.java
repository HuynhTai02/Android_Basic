package com.huynhngoctai.test.view;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.huynhngoctai.test.adapter.TourAdapter;
import com.huynhngoctai.test.database.entity.Tour;
import com.huynhngoctai.test.database.room.TourRoom;
import com.huynhngoctai.test.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private TourAdapter tourAdapter;
    private List<Tour> tours;
    ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                LoadData();
            }
        });

        initAdapter();
        initViews();
    }

    private void initAdapter() {
        tourAdapter = new TourAdapter(new TourAdapter.IClickItemTour() {
            @Override
            public void editTour(Tour tour) {
                clickEditTour(tour);
            }

            @Override
            public void deleteTour(Tour tour) {
                clickDeleteTour(tour);
            }
        });

        tours = new ArrayList<>();
        tourAdapter.setData(tours);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rcvListTour.setLayoutManager(linearLayoutManager);

        binding.rcvListTour.setAdapter(tourAdapter);
        LoadData();
    }

    private void initViews() {
        binding.imvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertTour();
            }
        });

        binding.imgDelAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllTour();
            }
        });

        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchByNameTour();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void searchByNameTour() {
        String nameSearch = binding.edtSearch.getText().toString().trim();

        tours.clear();
        tours = TourRoom.getInstance(MainActivity.this).tourDAO().searchNameTour(nameSearch);
        tourAdapter.setData(tours);
    }

    private void deleteAllTour() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Delete All Tour")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TourRoom.getInstance(MainActivity.this).tourDAO().deleteAllTour();
                        Toast.makeText(MainActivity.this, "Delete All Success", Toast.LENGTH_SHORT).show();

                        LoadData();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void insertTour() {
        String tourName = binding.edtTourName.getText().toString().trim();
        String tourDes = binding.edtTourDescription.getText().toString().trim();
        int tourNumb = Integer.parseInt(binding.edtTourNumbGuests.getText().toString().trim());
        String tourSchedule = binding.edtSchedule.getText().toString().trim();
        double tourPrice = Double.parseDouble(binding.edtTourPrice.getText().toString().trim());

        if (TextUtils.isEmpty(tourName) || TextUtils.isEmpty(tourSchedule) || TextUtils.isEmpty(tourDes)) {
            return;
        }

        Tour tour = new Tour(tourName, tourDes, tourNumb, tourSchedule, tourPrice);

        TourRoom.getInstance(this).tourDAO().insertTour(tour);
        Toast.makeText(this, "Insert Success", Toast.LENGTH_SHORT).show();

        binding.edtTourPrice.setText("");
        binding.edtSchedule.setText("");
        binding.edtTourNumbGuests.setText("");
        binding.edtTourName.setText("");
        binding.edtTourDescription.setText("");

        LoadData();
    }

    private void LoadData() {
        tours = TourRoom.getInstance(this).tourDAO().getAllTour();
        tourAdapter.setData(tours);
    }

    private void clickEditTour(Tour tour) {
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("object_tour", tour);
        intent.putExtras(bundle);
        launcher.launch(intent);
    }

    private void clickDeleteTour(Tour tour) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Delete Tour")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TourRoom.getInstance(MainActivity.this). tourDAO().deleteTour(tour);
                        Toast.makeText(MainActivity.this, "Delete Success", Toast.LENGTH_SHORT).show();

                        LoadData();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}