package com.tai.activity_lif;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.tai.activity_lif.databinding.ActM000SplashBinding;

public class act_m000_splash extends AppCompatActivity {
    private ActM000SplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActM000SplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
    }

    private void initViews(){
    }
}