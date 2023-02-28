package com.tai.learnmvvm.view.act;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.tai.learnmvvm.databinding.M001MainActBinding;
import com.tai.learnmvvm.viewmodel.ActM001MainVM;

public class ActM001Main extends AppCompatActivity {

    private M001MainActBinding binding;
    private ActM001MainVM m001VM;

    //Sử dụng Handler
//    private final Handler mHandler = new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(@NonNull Message msg) {
//            binding.tvTime.setText(String.format("%s", msg.arg1));
//            binding.progressTime.setProgress(msg.arg1);
//            return false;
//        }
//    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = M001MainActBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
    }

    private void initViews() {
        //Ánh xạ ViewModel
        m001VM = new ViewModelProvider(this).get(ActM001MainVM.class);

        //Bước 2: Cho View observe timeData trong ViewModel
        //Bước 4: Khi dữ liệu được thay đổi thì dữ liệu sẽ cập nhật về onChanged
        // Để trả dữ liệu về ViewModel
        m001VM.getTimeData().observe(this, i -> {
            binding.tvTime.setText(String.format("%s", i));
            binding.progressTime.setProgress(i);
        });

        //Thread Handler
//        m001VM.setCallBack(i -> {
//            Message msg = new Message();
//            msg.arg1 = i;
//            msg.setTarget(mHandler);
//            msg.sendToTarget();
//        });

        // Sử dụng runUiThread
//        m001VM.setCallBack(i -> runOnUiThread(() -> {
//            binding.tvTime.setText(String.format("%s", i));
//            binding.progressTime.setProgress(i);
//        }));

        binding.progressTime.setMax(10);
        binding.btStart.setOnClickListener(v -> startCountDown());
    }

    private void startCountDown() {
        m001VM.startCountDown();
    }
}