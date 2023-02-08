package com.tai.sharepreference;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.tai.sharepreference.databinding.ActivityMainBinding;

import java.io.FileOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_USER = "KEY_USER";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
    }

    private void initViews() {
        binding.ckbSave.setOnClickListener(v -> doSaveAcc());
        binding.btSave1.setOnClickListener(v -> savePhotoDataStorage());
        binding.btSave2.setOnClickListener(v -> savePhotoExternalStorage());
//        SharedPreferences preferences = getSharedPreferences("pref_saving", MODE_PRIVATE);
//        String phone = preferences.getString(KEY_USER, "");
//        binding.edtUser.setText(phone);
//        if (!phone.isEmpty()) {
//            binding.ckbSave.setChecked(true);
//        }

        String phone = CommonUtils.getInstance().getPref(KEY_USER);
        if (phone != null) {
            binding.edtUser.setText(phone);
            binding.ckbSave.setChecked(true);
        }

    }

    private void savePhotoExternalStorage() {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 101);
        }
        try {
            AssetManager assetManager = getAssets();
            InputStream in = assetManager.open("photo/Con gái.png");
            String dataPath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getPath();
            String photoPath = dataPath + "/Con gái.png";

            FileOutputStream out = new FileOutputStream(photoPath);

            byte[] buff = new byte[1024];
            int len = in.read(buff);
            while (len > 0) {
                out.write(buff, 0, len);
                len = in.read(buff);
            }
            out.close();
            in.close();
            Toast.makeText(this, "Photo  is coppied", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int rs : grantResults) {
            if (rs != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Please allow this permissions to save data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void doSaveAcc() {
        if (binding.edtUser.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please input Phone first!", Toast.LENGTH_SHORT).show();
            return;
        }

//        SharedPreferences preferences = getSharedPreferences("pref_saving", MODE_PRIVATE);
//        if (binding.ckbSave.isChecked()) {
//            preferences.edit().putString(KEY_USER, binding.edtUser.getText().toString()).apply();
//        } else {
//            preferences.edit().remove(KEY_USER).apply();
//        }

        if (binding.ckbSave.isChecked()) {
            CommonUtils.getInstance().savePref(KEY_USER, binding.edtUser.getText().toString());
        } else {
            CommonUtils.getInstance().clearPref(KEY_USER);
        }
    }

    private void saveToDataStorage() {
        if (binding.edtContent.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please input phone first!", Toast.LENGTH_SHORT).show();
            return;
        }
        String dataPath = Environment.getDataDirectory().getPath() + "/data/" + getPackageName();
        String filePath = dataPath + "/content.txt";
        try {
            FileOutputStream out = new FileOutputStream(filePath);
            byte[] buff = binding.edtContent.getText().toString().getBytes();
            out.write(buff, 0, buff.length);
            out.close();
            Toast.makeText(this, "File is written", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void savePhotoDataStorage() {
        try {
            AssetManager assetManager = getAssets();
            InputStream in = assetManager.open("photo/Con gái.png");
            String dataPath = Environment.getDataDirectory().getPath() + "/data/" + getPackageName();
            String photoPath = dataPath + "/Con gái.png";

            FileOutputStream out = new FileOutputStream(photoPath);

            byte[] buff = new byte[1024];
            int len = in.read(buff);
            while (len > 0) {
                out.write(buff, 0, len);
                len = in.read(buff);
            }
            out.close();
            in.close();
            Toast.makeText(this, "Photo  is coppied", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}