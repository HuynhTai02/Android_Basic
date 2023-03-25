package com.huynhngoctai.act_intent_ex_utc2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.huynhngoctai.act_intent_ex_utc2.databinding.ActivityMainBinding;
import com.huynhngoctai.model.Product;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    //C1
//    public static final int REQUEST_CODE = 101;

    //C2
    ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Log.i("MainActivity", "onCreate");

        //C2
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result ->{
            if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                binding.txtNumber.setText(String.valueOf(result.getData().getIntExtra("return_data",1)));
            }
        });
        addEvents();
    }

    private void addEvents() {
        binding.btFirst.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FirstAct.class);
            //intent.setClass(MainActivity.this, FirstAct.class);
            startActivity(intent);
        });

        binding.btSecond.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondAct.class);
            startActivity(intent);
        });

        binding.btThird.setOnClickListener(v -> {
            //Use intent send data
//            Intent intent = new Intent(MainActivity.this, ThridAct.class);
//            intent.putExtra("numb", 60);
//            intent.putExtra("grade", 6.9f);
//            intent.putExtra("checked", true);
//            intent.putExtra("string", "Pro Dev Mobile Future...");
//            startActivity(intent);

            //Use bundle send data
            Intent intent = new Intent(MainActivity.this, ThridAct.class);
            //Dong goi du lieu bang bundle
            Bundle myBundle = new Bundle();
            myBundle.putInt("numb", 60);
            myBundle.putFloat("grade", 6.9f);
            myBundle.putBoolean("checked", true);
            myBundle.putString("string", "Pro Dev Mobile Future...");

            //Put Object
            Product product = new Product("P01", "Tiger", "15000");
            myBundle.putSerializable("product", product);

            //dung intent van chuyen bundle da dong goi
            intent.putExtra("myBundle", myBundle);
            startActivity(intent);
        });
        //C1
        binding.btSendData.setOnClickListener(v -> {
            String number = binding.edtNumber.getText().toString();
            Intent intent = new Intent(MainActivity.this, Fourth.class);
            intent.putExtra("number", number);
//            startActivityForResult(intent, REQUEST_CODE);
            launcher.launch(intent);
        });

    }
    //C1
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
//            int number = data.getIntExtra("return_data", 0);
//            binding.txtNumber.setText(String.valueOf(number));
//        }
//    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity", "onDestroy");
    }
}