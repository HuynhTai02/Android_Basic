package com.example.sqlite_ex2.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    public static final String TBL_NAME = "Product";
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "product.sqlite";

    public static final String COL_CODE = "ProductCode";
    public static final String COL_NAME = "ProductName";
    public static final String COL_PRICE = "ProductPrice";

    public MyDBHelper(@NonNull Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + " (" + COL_CODE + " INTEGER " +
                "PRIMARY KEY AUTOINCREMENT," + COL_NAME + " VARCHAR(50), " + COL_PRICE + " REAL)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_NAME);
    }

    public void execSql(String sql) {
        //Insert Update Delete....
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }

    public Cursor querryData(String sql) {
        //SELECT
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        return sqLiteDatabase.rawQuery(sql, null);
    }

    public int getNumberOfRows(String sql) {
        Cursor cursor = querryData(sql);
        int numbOfRows = cursor.getCount();
        cursor.close();

        return numbOfRows;
    }

    public void createSampleData() {
        int numb = getNumberOfRows("SELECT * FROM " + TBL_NAME);
        if (numb == 0) {
            execSql("INSERT INTO " + TBL_NAME + " VALUES (null,'Tiger',19000)");
            execSql("INSERT INTO " + TBL_NAME + " VALUES (null,'Heineken',22000)");
            execSql("INSERT INTO " + TBL_NAME + " VALUES (null,'Sài Gòn',15000)");
            execSql("INSERT INTO " + TBL_NAME + " VALUES (null,'Huda',18000)");
        }
    }
}
