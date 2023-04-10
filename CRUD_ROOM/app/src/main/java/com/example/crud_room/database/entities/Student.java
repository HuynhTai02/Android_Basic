package com.example.crud_room.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//Entity
//Khi biên dịch Room sẽ dựa vào mỗi lớp Entity đã định nghĩa để tạo ra các Table tương ứng.
//Các thuộc tính bên trong lớp Entity chính là các cột của Table.
//tableName : đặt tên cho bảng
@Entity(tableName = "student")
public class Student implements Serializable {

    //Khóa chính, tự động tăng
    @PrimaryKey(autoGenerate = true)
    private int id;

    //set lại tên thuộc tính bằng cách: @ColumnInfo(name = "student_name")
    private String studentName;
    private String address;

    public Student(String studentName, String address) {
        this.studentName = studentName;
        this.address = address;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}