package com.example.crud_room.view;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.crud_room.R;
import com.example.crud_room.adapter.StudentAdapter;
import com.example.crud_room.database.StudentDatabase;
import com.example.crud_room.database.entities.Student;
import com.example.crud_room.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private StudentAdapter studentAdapter;
    private List<Student> students;
//    public final int MY_REQUEST_CODE = 1;

    ActivityResultLauncher<Intent> launcher;

    //Lưu trữ trạng thái hiện tại của việc sort
    private boolean isNameAscending = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                LoadData();
            }
        });

        initAdapter();
        initViews();
    }

    private void initAdapter() {
        studentAdapter = new StudentAdapter(new StudentAdapter.IClickItemStudent() {
            @Override
            public void editStudent(Student student) {
                clickEditStudent(student);
            }

            @Override
            public void deleteStudent(Student student) {
                clickDeleteStudent(student);
            }
        });
        students = new ArrayList<>();
        studentAdapter.setData(students);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rcvListStudent.setLayoutManager(linearLayoutManager);

        binding.rcvListStudent.setAdapter(studentAdapter);
        LoadData();
    }

    private void initViews() {
        binding.btnInsertStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertStudent();
            }
        });

        binding.btnDelAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllStudent();
            }
        });

        binding.imgBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchByNameStu();
                hideSoftKeyboard();
            }
        });

        binding.imgBtnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortName();
            }
        });

        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchByNameStu();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

    }

    private void sortName() {
        isNameAscending = !isNameAscending; // Toggle giá trị của biến boolean
        // Hiển thị ảnh đúng ban đầu
        binding.imgBtnSort.setImageResource(isNameAscending ? R.drawable.ic_sort_az : R.drawable.ic_sort_za);
        if (isNameAscending) {
            binding.imgBtnSort.setImageResource(R.drawable.ic_sort_za);
        } else {
            binding.imgBtnSort.setImageResource(R.drawable.ic_sort_az);
        }
        students = StudentDatabase.getInstance(HomeActivity.this).studentDAO().sortName(isNameAscending);
        studentAdapter.setData(students);
    }
    private void searchByNameStu() {
        String nameSearch = binding.edtSearch.getText().toString().trim();

        students.clear();
        students = StudentDatabase.getInstance(HomeActivity.this).studentDAO().searchStudentName(nameSearch);
        studentAdapter.setData(students);
    }

    private void deleteAllStudent() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Delete All Student")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StudentDatabase.getInstance(HomeActivity.this).studentDAO().deleteAllStudent();
                        Toast.makeText(HomeActivity.this, "Delete All Success", Toast.LENGTH_SHORT).show();

                        LoadData();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void insertStudent() {
        String studentName = binding.edtUsername.getText().toString().trim();
        String address = binding.edtAddress.getText().toString().trim();

        if (TextUtils.isEmpty(studentName) || TextUtils.isEmpty(address)) {
            return;
        }

        Student student = new Student(studentName, address);

        //kiểm tra nameStu đã tồn tại thì dừng
        if (isNameStuExist(student)) {
            Toast.makeText(this, "Name Stu Exist", Toast.LENGTH_SHORT).show();
            return;
        }

        StudentDatabase.getInstance(this).studentDAO().insertStudent(student);
        Toast.makeText(this, "Insert Success", Toast.LENGTH_SHORT).show();

        binding.edtUsername.setText("");
        binding.edtAddress.setText("");

        hideSoftKeyboard();

        LoadData();
    }

    private boolean isNameStuExist(Student student) {
        List<Student> list = StudentDatabase.getInstance(this).studentDAO().checkStudent(student.getStudentName());
        return list != null && !list.isEmpty();
    }

    private void LoadData() {
        students = StudentDatabase.getInstance(this).studentDAO().getAllStudent();
        //set dữ liệu cho adapter
        studentAdapter.setData(students);
    }

    private void clickEditStudent(Student student) {
        Intent intent = new Intent(HomeActivity.this, UpdateActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("object_student", student);
        intent.putExtras(bundle);
//        startActivityForResult(intent, MY_REQUEST_CODE);
        launcher.launch(intent);
    }

    private void clickDeleteStudent(Student student) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Delete User")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StudentDatabase.getInstance(HomeActivity.this).studentDAO().deleteStudent(student);
                        Toast.makeText(HomeActivity.this, "Delete Success", Toast.LENGTH_SHORT).show();

                        LoadData();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    //Nhận kết quả trả về khi update
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == MY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            LoadData();
//        }
//    }

    //ẩn key board
    public void hideSoftKeyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }
}