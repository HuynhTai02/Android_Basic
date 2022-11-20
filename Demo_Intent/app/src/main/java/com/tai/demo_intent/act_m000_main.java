package com.tai.demo_intent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.tai.demo_intent.databinding.ActM000MainBinding;

public class act_m000_main extends AppCompatActivity {

    private ActM000MainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActM000MainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
    }

    private void initViews() {
        binding.btCall.setOnClickListener(view -> doCall());
        binding.btSms.setOnClickListener(view -> doSms());
    }

    private void doCall() {
        if (binding.edtPhone.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter Number Phone, Please!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.CALL_PHONE
            }, 101);
            return;
        }
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel: " + binding.edtPhone.getText().toString()));

        startActivity(intent);
    }

    private void doSms() {
        if (binding.edtPhone.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter Number Phone, Please!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("sms:09845953366"));

        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Please Allow Call", Toast.LENGTH_SHORT).show();
                return;
            }
            doCall();
        }
    }
}