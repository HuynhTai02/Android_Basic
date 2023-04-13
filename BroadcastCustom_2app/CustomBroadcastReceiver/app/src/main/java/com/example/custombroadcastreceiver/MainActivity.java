package com.example.custombroadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.custombroadcastreceiver.databinding.ActivityMainBinding;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private static final String MY_ACTION = "com.tai.ACTION";
    private static final String MY_ACTION_OBJECT = "com.tai.ACTION_OBJECT";
    private static final String MY_TEXT = "com.tai.TEXT";
    private static final String MY_STUDENT = "com.tai.STUDENT";

    private static final String MY_ACTION_LIST_OBJECT = "com.tai.ACTION_LIST_OBJECT";
    private static final String MY_LIST_STUDENT = "com.tai.LIST_STUDENT";

    //2Tạo broadcastReceiver để lắng nghe và xử lý các thông điệp được gửi đến
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

                binding.tvReceivedOb.setText("Student id= " + student.getId() + " - Student Name: " + student.getName());
            } else if (MY_ACTION_LIST_OBJECT.equals(intent.getAction())) {
                //Lấy chuỗi JSON được truyền qua Intent với key là "MY_LIST_STUDENT"
                // và gán vào biến jsonStudent bằng phương thức getStringExtra()
                String jsonStudent = intent.getStringExtra(MY_LIST_STUDENT);
                List<Student> studentList = null;
                try {
                    //Thực hiện phân tích cú pháp chuỗi JSON và chuyển đổi nó thành danh sách đối tượng Student
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

        initViews();
    }

    private void initViews() {
        binding.btSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSendBroadcast();
            }
        });

        binding.btSend0bject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSendBroadcastObject();
            }
        });

        binding.btSendList0bject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSendBroadcastLObject();
            }
        });
    }

    private void clickSendBroadcastLObject() {
        Intent intent = new Intent();
        intent.setAction(MY_ACTION_LIST_OBJECT);

        List<Student> students = new ArrayList<>();
        students.add(new Student("TaiHuynh", 1));
        students.add(new Student("TaiDev", 2));
        students.add(new Student("TaiMobile", 3));

        Gson gson = new Gson();
        JsonArray jsonArray = gson.toJsonTree(students).getAsJsonArray();
        String strJson = jsonArray.toString();

        intent.putExtra(MY_LIST_STUDENT, strJson);

        sendBroadcast(intent);
        Toast.makeText(this, "Send List Object Success", Toast.LENGTH_SHORT).show();
    }

    //khi truyền đối tượng Parcelable hoặc Serializable
    //cần phải setAction cho Intent để cho phép các thành phần khác trong ứng dụng hoặc các ứng dụng khác
    //có thể xử lý đúng các dữ liệu này
    private void clickSendBroadcastObject() {
        Intent intent = new Intent();
        intent.setAction(MY_ACTION_OBJECT); // set action for Intent
        Student student = new Student("TaiHuynh", 1);
        Gson gson = new Gson();
        String jsonStudent = gson.toJson(student);
        intent.putExtra(MY_STUDENT, jsonStudent);

        sendBroadcast(intent);
        Toast.makeText(this, "Send Object Success", Toast.LENGTH_SHORT).show();
    }


    //1Gửi thông điệp đi
    //Khi truyền dữ liệu cơ bản, không cần setAction cho Intent,
    //dữ liệu truyền trực tiếp qua các phương thức putExtra(), putString(), putInt()
    private void clickSendBroadcast() {
        Intent intent = new Intent(MY_ACTION);
        intent.putExtra(MY_TEXT, "I am a mobile developer");

        sendBroadcast(intent);
    }

    //3Đăng ký BroadcastReceiver đã tạo để lắng nghe các thông điệp được gửi đến
    // Khi đã có thông điệp được gửi đến => hệ thống sẽ gọi phương thức onReceive của BroadcastReceiver để xử lý
    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(MY_ACTION);
        IntentFilter intentFilter1 = new IntentFilter(MY_ACTION_OBJECT);
        IntentFilter intentFilter2 = new IntentFilter(MY_ACTION_LIST_OBJECT);
        registerReceiver(broadcastReceiver, intentFilter);
        registerReceiver(broadcastReceiver, intentFilter1);
        registerReceiver(broadcastReceiver, intentFilter2);
    }

    //4Huỷ đăng ký để ngừng lắng nghe các thông điệp được phát
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }

    private List<Student> getListStudent(String strJson) throws JSONException {
        List<Student> students = new ArrayList<>();
        //Chuyển đổi chuỗi JSON thành đối tượng JSON Array để xử lý các đối tượng JSON bên trong
        JSONArray jsonArray = new JSONArray(strJson);
        //Khởi tạo đối tượng JSONObject để lưu trữ một đối tượng JSON.
        JSONObject jsonObject;
        // Khởi tạo đối tượng Student để lưu trữ dữ liệu của một đối tượng JSON được chuyển đổi.
        Student student;
        //Khởi tạo đối tượng Gson để thực hiện chuyển đổi chuỗi JSON sang đối tượng Java.
        Gson gson = new Gson();
        for (int i = 0; i < jsonArray.length(); i++) {
            //Lấy đối tượng JSON thứ i trong mảng JSON
            jsonObject = jsonArray.getJSONObject(i);
            //Sử dụng Gson để chuyển đổi đối tượng JSON sang đối tượng Java của lớp sinh viên
            student = gson.fromJson(jsonObject.toString(), Student.class);

            students.add(student);
        }
        return students;
    }
}