package com.tai.demothread;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.tai.demothread.databinding.ActivityMain1Binding;

@SuppressWarnings("deprecation")
public class MainActivity1 extends AppCompatActivity implements MTask.OnCallBack {
    private static final String KEY_TASK_COUNTING = "KEY_TASK_COUNTING";
    private static final String KEY_TASK_COUNTDOWN = "KEY_TASK_COUNTDOWN";
    private ActivityMain1Binding binding;
    private static final String TAG = MainActivity1.class.getName();
    private MTask taskT;
    private MTask taskD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
    }

    private void initViews() {
        binding.btStart.setOnClickListener(v -> startCounting());
        binding.btStop.setOnClickListener(v -> stopCounting());
    }

    private void stopCounting() {
        if (taskT == null) {
            return;
        }
        taskT.cancel(true);
    }

    @SuppressLint("StaticFieldLeak")
    private void startCounting() {
        taskT = new MTask(KEY_TASK_COUNTING, this);
        taskT.startAsync(new int[]{10, 20});

        taskD = new MTask(KEY_TASK_COUNTDOWN, this);
        taskD.startAsync(new int[]{20, 10});
    }

    @Override
    public Object execTask(String key, Object param, MTask mtask) {
        if (key.equals(KEY_TASK_COUNTING)) {
            doCounting((int[]) param, taskT);
        } else if (key.equals(KEY_TASK_COUNTDOWN)) {
            doCountDown((int[]) param, taskD);
        }
        return null;
    }

    private boolean doCountDown(int[] params, MTask task) {
        int start = params[0];
        int end = params[1];
        for (int i = start; i >= end; i--) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                //e.printStackTrace();
                Log.e(TAG, "Thread has interrupted");
                return false;
            }
            taskD.requestUI(i);
        }
        Log.e(TAG, "Thread die");
        return true;
    }

    @Override
    public void updateUI(String key, Object data) {
        if (key.equals(KEY_TASK_COUNTING)) {
            binding.tvCound.setText(String.format("%s", data));
        } else if (key.equals(KEY_TASK_COUNTDOWN)) {
            binding.tvCound1.setText(String.format("%s", data));
        }
    }

    @Override
    public void completeTask(String key, Object data) {
        if (key.equals(KEY_TASK_COUNTING)) {
            binding.tvCound.setText(String.format("%s", "Counting is done"));
        } else if (key.equals(KEY_TASK_COUNTDOWN)) {
            binding.tvCound1.setText(String.format("%s", "CountDown is done"));
        }
    }

    @Override
    public void cancelTask(String key) {
        if (key.equals(KEY_TASK_COUNTING)) {
            binding.tvCound.setText(String.format("%s", "Counting has interrupted"));
        } else if (key.equals(KEY_TASK_COUNTDOWN)) {
            binding.tvCound1.setText(String.format("%s", "CountDown has interrupted"));
        }

    }

    private boolean doCounting(int[] params, MTask task) {
        int start = params[0];
        int end = params[1];
        for (int i = start; i <= end; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                //e.printStackTrace();
                Log.e(TAG, "Thread has interrupted");
                return false;
            }
            taskT.requestUI(i);
        }
        Log.e(TAG, "Thread die");
        return true;
    }
}