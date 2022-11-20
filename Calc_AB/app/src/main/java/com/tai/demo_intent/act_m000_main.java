package com.tai.demo_intent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tai.demo_intent.databinding.ActM000MainBinding;

public class act_m000_main extends AppCompatActivity {

    public static final String SUM_TYPE = "+";
    public static final String SUB_TYPE = "-";
    public static final String MUL_TYPE = "*";
    public static final String DIV_TYPE = "/";
    public static final String KEY_TYPE = "KEY_TYPE";
    public static final String KEY_AB = "KEY_AB";

    private ActM000MainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActM000MainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
    }

    private void initViews() {
        binding.btSum.setOnClickListener(view -> doCalc(SUM_TYPE));
        binding.btSub.setOnClickListener(view -> doCalc(SUB_TYPE));
        binding.btMul.setOnClickListener(view -> doCalc(MUL_TYPE));
        binding.btDiv.setOnClickListener(view -> doCalc(DIV_TYPE));
    }

    private void doCalc(String type) {
        String txtA = binding.edtA.getText().toString();
        String txtB = binding.edtB.getText().toString();

        if (txtA.isEmpty() || txtB.isEmpty()) {
            Toast.makeText(this, "Enter Number A & Number B, Please", Toast.LENGTH_SHORT).show();
            return;
        }

        PairAB ab = new PairAB(Double.parseDouble(txtA), Double.parseDouble(txtB));

        Intent intent = new Intent();
        intent.setClass(act_m000_main.this, act_m001_calc.class);

        Bundle data = new Bundle();
        data.putString(KEY_TYPE, type);
        data.putSerializable(KEY_AB, ab);

        intent.putExtras(data);

        startActivity(intent);

    }
}