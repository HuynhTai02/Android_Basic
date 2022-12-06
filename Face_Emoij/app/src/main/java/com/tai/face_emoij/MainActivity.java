package com.tai.face_emoij;

import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tai.face_emoij.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends BaseAct<ActivityMainBinding> {

    private AlertDialog alert;
    private CloseDialog dialog;

    @Override
    protected void initViews() {
        binding.imgFun.setOnClickListener(this);
        binding.imgHappy.setOnClickListener(this);
        binding.imgHurt.setOnClickListener(this);
        binding.imgInLove.setOnClickListener(this);
        binding.imgMakeFun.setOnClickListener(this);
        binding.imgSick.setOnClickListener(this);
        binding.imgSulk.setOnClickListener(this);
        binding.imgWhoa.setOnClickListener(this);
        binding.imgYahoo.setOnClickListener(this);
    }

    @Override
    protected void clickView(View view) {
        String text = view.getContentDescription().toString();
        Drawable drawable = ((ImageView) view).getDrawable();

        showToast(text, drawable);
    }

    private void showToast(String text, Drawable drawable) {
        Toast toast = new Toast(this);

        View view = LayoutInflater.from(this).inflate(R.layout.layout_custom_toast_face, null);
        ImageView ivFace = view.findViewById(R.id.img_face);
        TextView tvFace = view.findViewById(R.id.tv_face);

        tvFace.setText(text);
        ivFace.setImageDrawable(drawable);
        toast.setView(view);

        int wScreen = getResources().getDisplayMetrics().widthPixels;
        int hScreen = getResources().getDisplayMetrics().heightPixels;
        Random rd = new Random();
        int x = rd.nextInt(wScreen);
        int y = rd.nextInt(hScreen);
        toast.setGravity(Gravity.TOP | Gravity.START, x, y);

        toast.show();
    }

    @Override
    protected ActivityMainBinding initViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onBackPressed() {
//      showAlertDialog();

        showCloseDialog();
    }

    private void showCloseDialog() {
        if (dialog == null) {
            dialog = new CloseDialog(this);
            dialog.setOnCloseApp(this::finish);
        }

        dialog.show();
    }

    private void showAlertDialog() {
        if (alert == null) {
            alert = new AlertDialog.Builder(this).create();

            //Không thể kích sang bên ngoài
            alert.setCanceledOnTouchOutside(false);
            //Ấn backpress k tắt đc alertdialog
            alert.setCancelable(false);

            alert.setTitle("Alert");
            alert.setMessage("Close app?");

            alert.setButton(AlertDialog.BUTTON_POSITIVE, "OK", (dialog, which) -> {
                // Xử lý khi người dùng click vào OK
                super.onBackPressed();
            });

            alert.setButton(AlertDialog.BUTTON_NEGATIVE, "DON'T", (dialog, which) -> {
                // Do nothing
            });
        }
        alert.show();
    }
}