package com.example.student_manager_sqlite.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.student_manager_sqlite.db.entities.Student;
import com.example.student_manager_sqlite.db.entities.StudentScore;

import java.util.List;

@Dao
public interface StudentDAO {
    @Query("SELECT * FROM Student")
    List<Student> getAllStudent();

    @Query("SELECT * FROM Student WHERE StudentId = :id")
    Student getStudent(String id);

    @Query("SELECT stu.*, sub.Name, p.Score " +
            "FROM Student stu INNER JOIN Subject sub ON stu.StudentId = sub.StudentId " +
            "INNER JOIN Point p ON  p.SubjectId = sub.SubjectId " +
            "WHERE stu.FullName = :name")
    StudentScore getScoreStudent(String name);

    @Insert
    void insertStudent(Student... students);

    @Delete
    void delete(Student student);
}
