package com.huynhngoctai.act_intent_ex_utc2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.huynhngoctai.act_intent_ex_utc2.databinding.ActivityFourthBinding;

public class Fourth extends AppCompatActivity {
    private ActivityFourthBinding binding;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFourthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getData();
        addEvents();
    }

    private void addEvents() {
        binding.btBackAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(binding.txtNumber.getText().toString());
                int pow_num = number * number;

                intent.putExtra("return_data", pow_num);
                setResult(MainActivity.RESULT_OK, intent);
                finish();
            }
        });
    }

    private void getData() {
        intent = getIntent();
        String number = intent.getStringExtra("number");
        binding.txtNumber.setText(number);
    }
}