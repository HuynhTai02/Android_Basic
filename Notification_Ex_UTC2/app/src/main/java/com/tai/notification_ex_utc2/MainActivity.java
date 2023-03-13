package com.tai.notification_ex_utc2;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

import com.tai.notification_ex_utc2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createAlerDialog();
        customAlerDialog();
    }

    private void customAlerDialog() {
        binding.btClose.setOnClickListener(v -> {
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.item_custom_dialog);

            ImageButton ibtOk = dialog.findViewById(R.id.ibt_check);
            ibtOk.setOnClickListener(v12 -> finish());

            ImageButton ibtCancel = dialog.findViewById(R.id.ibt_close);
            ibtCancel.setOnClickListener(v1 -> dialog.dismiss());

            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        });

    }

    private void createAlerDialog() {
        binding.btExit.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Xác nhận thoát");
            builder.setMessage("Bạn chắc chắn muốn đóng ứng dụng?");
            builder.setIcon(android.R.drawable.ic_dialog_info);

            builder.setPositiveButton("Yes", (dialog, which) -> finish());

            builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());

            Dialog dialog = builder.create();
            //Click ngoài vùng dialog không tương tác được
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        });
    }
}