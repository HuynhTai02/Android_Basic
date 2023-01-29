package com.tai.demothread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.tai.demothread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final int MSG_DONE_COUNTING = 106;
    private ActivityMainBinding binding;

    private static final String TAG = MainActivity1.class.getName();
    private static final int MSG_UPDATE_TEXT = 105;
    private Thread th;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
    }

    private void initViews() {
        binding.btStart.setOnClickListener(v -> startCounting());
        binding.btStop.setOnClickListener(v -> stopCounting());
    }

    private void startCounting() {
        //Kiểm tra trạng thái thread
        if (th == null || !th.isAlive()) {
            //Tạo Thread
            th = new Thread(rb);
            th.start();
        }
    }

    private void stopCounting() {
        if (th != null && th.isAlive()) {
            th.interrupt();
        }
    }

    //Tạo ra môi trường để chứa công việc cần thực thi trên thread
    private final Runnable rb = this::execTask;

    private void execTask() {
        for (int i = 0; i <= 30; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                //e.printStackTrace();
                Log.e(TAG, "Thread has interrupted");

                Message msg = new Message();
                msg.what = MSG_DONE_COUNTING;
                msg.obj = "Counting has interrupted!";
                msg.setTarget(mHandler);
                msg.sendToTarget();

                return;
            }

            Log.i(TAG, "startCounting..." + i);

            Message msg = new Message();
            msg.what = MSG_UPDATE_TEXT;
            msg.arg1 = i;
            msg.setTarget(mHandler);
            msg.sendToTarget();

            //binding.tvCound.setText(String.format("%s", i));
            //Toast.makeText(MainActivity.this, String.format("%s", i), Toast.LENGTH_SHORT).show();
        }

        Message msg = new Message();
        msg.what = MSG_DONE_COUNTING;
        msg.obj = "Counting is done!";
        msg.setTarget(mHandler);
        msg.sendToTarget();

        Log.e(TAG, "Thread die...");
    }

    //Nơi nhận và xử lý các yêu cầu cầu của Thread gửi đến
    private final Handler mHandler = new Handler(this::handleMessage);

    private boolean handleMessage(Message msg) {
        if (msg.what == MSG_UPDATE_TEXT) {
            int i = msg.arg1;
            binding.tvCound.setText(String.format("%s", i));
            //Toast.makeText(MainActivity1.this, String.format("%s", i), Toast.LENGTH_SHORT).show();
        } else if (msg.what == MSG_DONE_COUNTING) {
            String rs = (String) msg.obj;
            binding.tvCound.setText(String.format("%s", rs));
            //Toast.makeText(MainActivity1.this, String.format("%s", i), Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}