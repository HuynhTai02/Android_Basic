package com.example.crud_room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.crud_room.database.dao.StudentDAO;
import com.example.crud_room.database.entities.Student;

//Database
//entities: để biết database này của entity nào
//version: để trong khi thao tác với sql khi cần thêm cột dữ liệu thì ta cần nâng lên version mới
@Database(entities = {Student.class}, version = 1)
public abstract class StudentDatabase extends RoomDatabase {

//    //b1: tăng version
//    //b2: magration
//    //nó phải là biến tĩnh
//    //thay đổi thuộc tính table
//    static Migration migration_from_1_to_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            //logic thêm 1 cột vào trong 1 bảng
//            database.execSQL("ALTER TABLE user ADD COLUMN year TEXT ");
//
//            //Thêm nhiều cột vào trong một bảng
//            //ALTER TABLE ten_bang ADD cot1 dinh_nghia_cot1, cot2 dinh_nghia_cot2,...cotn dinh_nghia_cotn;
//
//            //chinh sửa kiểu dữ liệu cột trong một bảng
//            // ALTER TABLE ten_bang ALTER COLUMN ten_cot kieu_cot;
//
//            //xóa cột trong bảng
//            // ALTER TABLE ten_bang DROP COLUMN ten_cot;
//        }
//    };
    private static final String DATABASE_NAME = "student.db";
    private static StudentDatabase instance; //Được sử dụng để theo dõi đối tượng StudentDatabase được tạo ra trong ứng dụng.

    //synchronized:tuần tự, được đồng bộ hóa
    //Để đảm bảo rằng chỉ có một đối tượng StudentDatabase được tạo ra tại một thời điểm trong ứng dụng
    //Phương thức getInstance() được sử dụng để trả về một đối tượng StudentDatabase
    public static synchronized StudentDatabase getInstance(Context context) {
        //Nếu đối tượng chưa được tạo => sử dụng Room.databaseBuilder để tạo đối tượng đó
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), StudentDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()  //Cho phép query trên main thread
//                    .addMigrations(migration_from_1_to_2) //update database
                    .build();
        }
        return instance;
    }

    //Được sử dụng để truy vấn và cập nhật cơ sở dữ liệu thông qua đối tượng DAO của lớp Student.
    public abstract StudentDAO studentDAO();
}