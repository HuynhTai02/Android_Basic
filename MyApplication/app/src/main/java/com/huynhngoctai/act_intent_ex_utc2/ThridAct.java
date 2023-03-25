package com.huynhngoctai.act_intent_ex_utc2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.huynhngoctai.act_intent_ex_utc2.databinding.ActivityThridBinding;
import com.huynhngoctai.model.Product;

public class ThridAct extends AppCompatActivity {
    ActivityThridBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThridBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getData();
    }

    @SuppressLint("SetTextI18n")
    private void getData() {
        // Get data from MainAct
        //Use Intent show data
//        Intent intent = getIntent();
//        int numb = intent.getIntExtra("numb", 0);
//        float grade = intent.getFloatExtra("grade", 0.0f);
//        boolean checked = intent.getBooleanExtra("checked", false);
//        String string = intent.getStringExtra("string");


        //Use Bundle show data
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("myBundle");
        int numb = bundle.getInt("numb", 0);
        float grade = bundle.getFloat("grade", 0.0f);
        boolean checked = bundle.getBoolean("checked", false);
        String string = bundle.getString("string");

        //Get data object
        Product p = (Product) bundle.getSerializable("product");

        // Show data
        //1
        //binding.tvContent.setText("Numb: " + numb + "\n" + "Grade: " + grade + "\n" + "Checked: " + checked + "\n" + "String: " + string);
        //2
        binding.tvContent.setText("");
        binding.tvContent.append("Numb: " + numb + "\n");
        binding.tvContent.append("Grade: " + grade + "\n");
        binding.tvContent.append("Checked: " + checked + "\n");
        binding.tvContent.append("String: " + string + "\n");
        binding.tvContent.append(("Product Info: " + p.getProductCode() + " _ " + p.getProductName() + " _ " + p.getProductPrice()));
    }
}