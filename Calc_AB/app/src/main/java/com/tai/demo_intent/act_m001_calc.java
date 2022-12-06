package com.tai.demo_intent;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tai.demo_intent.databinding.ActM001CalcBinding;

public class act_m001_calc extends AppCompatActivity {

    public static final String KEY_RESULT = "KEY_RESULT";
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
        binding.btTraKq.setOnClickListener(v -> returnResult(binding.tvAns.getText().toString()));
    }

    //C1_B2
    private void returnResult(String result) {
        Intent data = new Intent();
        data.putExtra(KEY_RESULT, result);
        setResult(RESULT_OK, data);
        finish();
    }

    //Không trả về dữ liệu
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
    }
}