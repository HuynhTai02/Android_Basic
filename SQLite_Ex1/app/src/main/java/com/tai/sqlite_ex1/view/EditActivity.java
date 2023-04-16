package com.tai.sqlite_ex1.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tai.sqlite_ex1.databinding.ActivityEditBinding;
import com.tai.sqlite_ex1.model.Product;

public class EditActivity extends AppCompatActivity {

    ActivityEditBinding binding;
    Product p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getData();
        addEvents();
    }

    private void getData() {
        Intent intent = getIntent();
        p = (Product) intent.getSerializableExtra("productInfo");
        binding.edtProductName.setText(p.getProductName());
        binding.edtProductPrice.setText(String.valueOf(p.getProductPrice()));
    }

    private void addEvents() {
        binding.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Edit data
                ContentValues values = new ContentValues();
                values.put("productName", binding.edtProductName.getText().toString());
                values.put("productPrice", Double.parseDouble(binding.edtProductPrice.getText().toString()));

                long numbOfRows = MainActivity.database.update(MainActivity.TABLE_NAME, values,
                        "productId=?", new String[]{String.valueOf(p.getProductID())});
                if (numbOfRows > 0) {
                    Toast.makeText(EditActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditActivity.this, "Update Fail", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

        binding.btClose.setOnClickListener(v -> finish());
    }
}