package com.tai.sqlite_ex1.view;

import android.animation.TimeAnimator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.tai.sqlite_ex1.R;
import com.tai.sqlite_ex1.databinding.ActivityMainBinding;
import com.tai.sqlite_ex1.model.Product;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    public static final String DATABASE_NAME = "product_db.db";
    public static final String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;
    public static final String TABLE_NAME = "Product";

    ArrayAdapter<Product> adapter;
    ArrayList<Product> arrayList;

    Product selectedProduct = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        copyDataBase();
        openDataBase();
//        loadDataBase();
        addEvents();
        registerForContextMenu(binding.lvProduct);
    }

    private void addEvents() {
        binding.lvProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedProduct = adapter.getItem(position);
                return false;
            }
        });
    }

    private void openDataBase() {
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
    }

    //Thao tác trên CSDL SQLite
    private void loadDataBase() {
        arrayList = new ArrayList<>();
        Product p;
        //CÁCH 1
//        Truy vấn csdl với “rawQuery”
//        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
//        Cursor cursor = database.rawQuery("SELECT * FROM Product WHERE ProductId = ? OR ProductId = ?"
//                , new String[] {"2", "3"});

        //CÁCH 2
//        Truy vấn csdl với “query”
//        Cursor cursor = database.query(TABLE_NAME, null,
//                "ProductId = ? OR ProductId = ?", new String[]{"1", "4"}, null, null, null);

        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int pID = cursor.getInt(0);
            String pName = cursor.getString(1);
            double pPrice = cursor.getDouble(2);
            //To do something
//            Log.i("Data: ", pID + pName + pPrice);

            p = new Product(pID, pName, pPrice);
            arrayList.add(p);
        }
        cursor.close();
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);
        binding.lvProduct.setAdapter(adapter);

    }

    //Sao chép csdl SQLite vào hệ thống
    private void copyDataBase() {
        try {
            // Get Path file product_db.db
            File dbFile = getDatabasePath(DATABASE_NAME);
            // Check database
            if (!dbFile.exists()) {
                // If exist => Read and Copy DB From Asset
                if (CopyDBFromAsset()) {
                    Toast.makeText(MainActivity.this, "Copy database successful!", Toast.LENGTH_LONG).show();
                }
                // Fail
                else {
                    Toast.makeText(MainActivity.this, "Copy database fail!", Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception e) {
            Log.e("Error: ", e.toString());
        }
    }

    private boolean CopyDBFromAsset() {
        //Get Path DB from asset
        String dbPath = getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
        try {
            // Read DB
            InputStream inputStream = getAssets().open(DATABASE_NAME);
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists()) {
                f.mkdir();
            }

            //write DB
            OutputStream outputStream = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                outputStream = Files.newOutputStream(Paths.get(dbPath));
            }
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                assert outputStream != null;
                outputStream.write(buffer, 0, length);
            }
            assert outputStream != null;
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    protected void onResume() {
        loadDataBase();
        super.onResume();
    }

    //=============================================================//
    //Menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.optionemenu,menu);
        getMenuInflater().inflate(R.menu.optionemenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mn_Add) {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnEdit) {
            Intent intent = new Intent(MainActivity.this, EditActivity.class);
            if (selectedProduct != null) {
                intent.putExtra("productInfo", selectedProduct);
                startActivity(intent);
            }
        }
        if (item.getItemId() == R.id.mnDel) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Confirm Delete");
            builder.setMessage("Do you want delete data?");
            builder.setIcon(android.R.drawable.ic_delete);

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int numbOfRows = database.delete(TABLE_NAME,"ProductId=?",
                            new String[]{String.valueOf(selectedProduct.getProductID())});
                    if (numbOfRows > 0) {
                        loadDataBase();
                        Toast.makeText(MainActivity.this, "Delete Success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Delete Fail", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            builder.setNegativeButton("No",null);
//            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            });

            builder.create().show();
        }
        return super.onContextItemSelected(item);
    }
}