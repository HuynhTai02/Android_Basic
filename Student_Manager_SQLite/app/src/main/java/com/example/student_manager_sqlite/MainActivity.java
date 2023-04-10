package com.example.student_manager_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.student_manager_sqlite.databinding.ActivityMainBinding;
import com.example.student_manager_sqlite.db.entities.Student;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
    }

    private void initViews() {
        binding.btAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });
    }

    private void addStudent() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                Student student = new Student();
                student.id = "S0" + random.nextInt(100);
                student.name = "John ";
                student.age = 15;

                App.getInstance().getStudentDB().studentDAO().insertStudent(student);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Insert Student Success", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }
}