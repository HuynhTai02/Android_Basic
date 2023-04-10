package com.example.crud_room.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.crud_room.database.StudentDatabase;
import com.example.crud_room.database.entities.Student;
import com.example.crud_room.databinding.ActivityUpdateBinding;

public class UpdateActivity extends AppCompatActivity {
    private ActivityUpdateBinding binding;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        initViews();
    }

    private void initData() {
        student = (Student) getIntent().getExtras().get("object_student");
        if (student != null){
            binding.edtName.setText(student.getStudentName());
            binding.edtAddress.setText(student.getAddress());
        }
    }

    private void initViews() {
        binding.btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStudent();
            }});

    }

    private void updateStudent() {
        String studentName = binding.edtName.getText().toString().trim();
        String address = binding.edtAddress.getText().toString().trim();

        if (TextUtils.isEmpty(studentName) || TextUtils.isEmpty(address)){
            return;
        }

        student.setStudentName(studentName);
        student.setAddress(address);
        //update user mới
        StudentDatabase.getInstance(this).studentDAO().editStudent(student);
        Toast.makeText(this,"Update Success",Toast.LENGTH_SHORT).show();

        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK, intentResult);
        finish();
    }
}