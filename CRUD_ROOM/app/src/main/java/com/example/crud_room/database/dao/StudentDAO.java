package com.example.crud_room.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.crud_room.database.entities.Student;

import java.util.List;

//DAO: Data Access Object
//Đối tượng chịu trách nhiệm làm việc chính với các Entity trong cơ sở dữ liệu.
@Dao
public interface StudentDAO {
    @Insert
    void insertStudent(Student student);

    @Query("SELECT * FROM student")
    List<Student> getAllStudent();

    @Query("SELECT * FROM student WHERE studentName= :studentName")
    List<Student> checkStudent(String studentName);

    @Update
    void editStudent(Student student);

    @Delete
    void deleteStudent(Student student);

    @Query("DELETE FROM student")
    void deleteAllStudent();

    @Query("SELECT * FROM student WHERE studentName LIKE '%' || :name || '%'")
    List<Student> searchStudentName(String name);
    
    @Query("SELECT * FROM student ORDER BY studentName ASC")
    List<Student> sortNameAscending();

    @Query("SELECT * FROM student ORDER BY studentName DESC")
    List<Student> sortNameDescending();

    default List<Student> sortName(boolean isNameAscending) {
        if (isNameAscending) {
            return sortNameAscending();
        } else {
            return sortNameDescending();
        }
    }

}
