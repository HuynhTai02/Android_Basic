package com.tai.sqlite_ex1.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tai.sqlite_ex1.R;
import com.tai.sqlite_ex1.databinding.ActivityAddBinding;

public class AddActivity extends AppCompatActivity {
    private ActivityAddBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents() {
        binding.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Insert data
                ContentValues values = new ContentValues();
                values.put("productName", binding.edtProductName.getText().toString());
                values.put("productPrice", Double.parseDouble(binding.edtProductPrice.getText().toString()));

                long numbOfRows = MainActivity.database.insert(MainActivity.TABLE_NAME, null, values);
                if (numbOfRows > 0) {
                    Toast.makeText(AddActivity.this, "Insert Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddActivity.this, "Insert Fail", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

        binding.btClose.setOnClickListener(v -> finish());
    }
}