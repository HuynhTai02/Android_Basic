package com.tai.viewp2_ex_utc2.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.tai.viewp2_ex_utc2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    //1. Tạo nguồn dữ liệu cho ListView (data source)
    String[] drinks = {"Coca-Cola", "Pepsi", "Olong Tea", "Sting", "RedBull",
            "Coffee", "Milk", "Lemon Tea", "Milk Tea", "C2", "Soda","Coca-Cola", "Pepsi", "Olong Tea", "Sting", "RedBull",
            "Coffee", "Milk", "Lemon Tea", "Milk Tea", "C2", "Soda"};

    ArrayAdapter<String> adapterDrinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadData();
    }

    private void loadData() {
        //2. Gán data source vào đối tượng ArrayAdapter
        adapterDrinks = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_expandable_list_item_1, drinks);

        //3. Đưa dữ liệu lên ListView thông qua đối tượng ArrayAdapter
        binding.lvDrinks.setAdapter(adapterDrinks);
    }
}