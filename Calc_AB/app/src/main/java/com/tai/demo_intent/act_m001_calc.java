package com.tai.demo_intent;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tai.demo_intent.databinding.ActM001CalcBinding;

public class act_m001_calc extends AppCompatActivity {

    private ActM001CalcBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActM001CalcBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
    }

    private void initViews() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }

        Bundle data = intent.getExtras();

        String type = data.getString(act_m000_main.KEY_TYPE);
        PairAB ab = (PairAB) data.getSerializable(act_m000_main.KEY_AB);

        double rs;
        switch (type) {
            case act_m000_main.SUM_TYPE:
                rs = ab.getA() + ab.getB();
                break;
            case act_m000_main.SUB_TYPE:
                rs = ab.getA() - ab.getB();
                break;
            case act_m000_main.DIV_TYPE:
                rs = ab.getA() / ab.getB();
                break;
            default:
                rs = ab.getA() * ab.getB();
                break;
        }

        binding.tvAns.setText(String.format("%s %s %s = %s", ab.getA(), type, ab.getB(), rs));
    }
}