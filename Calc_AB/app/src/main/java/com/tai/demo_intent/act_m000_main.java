package com.tai.demo_intent;

import static com.tai.demo_intent.act_m001_calc.KEY_RESULT;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
    //C2_B1
    private ActivityResultLauncher<Intent> activityResult;

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

        //C2_B1: register ActivityResultLauncher to listen data callback from M001 screen
        activityResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), this::doM001DataReturn);
    }

    //C2_B2: receive data returning from M001
    private void doM001DataReturn(ActivityResult rs) {
        if (rs.getResultCode() == RESULT_OK && rs.getData() != null) {
            String result = rs.getData().getStringExtra(KEY_RESULT);
            binding.tvRs.setText(result);
        } else {
            Toast.makeText(this, "Kết quả không được trả về", Toast.LENGTH_SHORT).show();
        }
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

        //C1_B1
//        startActivityForResult(intent, 101);

//        //C2_B1 launch intent to display M001 screen
        activityResult.launch(intent);
    }

    //C1_B3
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 101) {
//            if (resultCode == RESULT_OK && data != null) {
//                String rs = data.getStringExtra(KEY_RESULT);
//                binding.tvRs.setText(rs);
//            } else {
//                Toast.makeText(this, "Không có kết quả trả về", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}