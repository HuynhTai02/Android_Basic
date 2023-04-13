package com.example.custombroadcastreceiver2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.example.custombroadcastreceiver2.databinding.ActivityMainBinding;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private static final String MY_ACTION = "com.tai.ACTION";
    private static final String MY_TEXT = "com.tai.TEXT";

    private static final String MY_ACTION_OBJECT = "com.tai.ACTION_OBJECT";
    private static final String MY_STUDENT = "com.tai.STUDENT";

    private static final String MY_ACTION_LIST_OBJECT = "com.tai.ACTION_LIST_OBJECT";
    private static final String MY_LIST_STUDENT = "com.tai.LIST_STUDENT";

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onReceive(Context context, Intent intent) {
            if (MY_ACTION.equals(intent.getAction())) {
                String text = intent.getStringExtra(MY_TEXT);
                binding.tvReceived.setText(text);
            } else if (MY_ACTION_OBJECT.equals(intent.getAction())) {
                String jsonStudent = intent.getStringExtra(MY_STUDENT);
                Gson gson = new Gson();
                Student student = gson.fromJson(jsonStudent, Student.class);

                binding.tvReceivedOb.setText("Student id= " + student.getId() + "\n"+ "Student Name: " + student.getName());
            } else if (MY_ACTION_LIST_OBJECT.equals(intent.getAction())) {
                String jsonStudent = intent.getStringExtra(MY_LIST_STUDENT);
                List<Student> studentList = null;
                try {
                    studentList = getListStudent(jsonStudent);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                String listUserName = "";
                for (int i = 0; i < studentList.size(); i++) {
                    listUserName = listUserName + studentList.get(i).getName() + "\n";
                }

                binding.tvReceivedListOb.setText(listUserName);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(MY_ACTION);
        IntentFilter intentFilter1 = new IntentFilter(MY_ACTION_OBJECT);
        IntentFilter intentFilter2 = new IntentFilter(MY_ACTION_LIST_OBJECT);
        registerReceiver(broadcastReceiver, intentFilter1);
        registerReceiver(broadcastReceiver, intentFilter);
        registerReceiver(broadcastReceiver, intentFilter2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    private List<Student> getListStudent(String strJson) throws JSONException {
        List<Student> students = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(strJson);
        JSONObject jsonObject;
        Student student;
        Gson gson = new Gson();
        for(int i =0;i<jsonArray.length();i++){
            jsonObject = jsonArray.getJSONObject(i);
            student = gson.fromJson(jsonObject.toString(),Student.class);

            students.add(student);
        }
        return students;
    }
}