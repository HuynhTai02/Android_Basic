package com.tai.activity_lif;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tai.activity_lif.databinding.ActM001LoginBinding;

public class act_m001_login extends AppCompatActivity implements View.OnClickListener {

    private ActM001LoginBinding binding;
    private static final String USER_NAME = "098456236";
    private static final String PASSWORD = "ABC145";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActM001LoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
    }

    private void initViews() {
        binding.btSignin.setOnClickListener(this);

        binding.tvForgot.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt_signin) {
            doSignIn();
        } else if (view.getId() == R.id.tv_forgot) {
            doForgotPassword();
        }
    }

    private void doForgotPassword() {
        Toast.makeText(this, "click forgot password", Toast.LENGTH_SHORT).show();
    }

    private void doSignIn() {
        String userName = binding.edtUsername.getText().toString();
        String pass = binding.edtPassword.getText().toString();

        if (userName.equals(USER_NAME) && pass.equals(PASSWORD)) {
            Toast.makeText(this, "Sign in successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "User name or Password is invalid", Toast.LENGTH_SHORT).show();
        }
    }

    int time = 0;

    @Override
    public void onBackPressed() {
        if (time == 0) {
            Toast.makeText(this, "Click thêm lần nữa để thoát app", Toast.LENGTH_SHORT).show();
            time++;

            //Xử lý reset time sau 2s
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    time = 0;
                }
            }, 2000);
        } else {
            super.onBackPressed();
            //finish();
        }
    }
}