package com.tai.viewp2_ex_utc2.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.tai.viewp2_ex_utc2.R;
import com.tai.viewp2_ex_utc2.databinding.ActivityFoodBinding;

public class FoodActivity extends AppCompatActivity {
    private ActivityFoodBinding binding;

    String[] foods;
    ArrayAdapter<String> adapterFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadData();
    }

    private void loadData() {
        foods = getResources().getStringArray(R.array.myFoods);
        adapterFoods = new ArrayAdapter<>(FoodActivity.this,
                android.R.layout.simple_list_item_1, foods);

        binding.lvFoods.setAdapter(adapterFoods);
    }
}