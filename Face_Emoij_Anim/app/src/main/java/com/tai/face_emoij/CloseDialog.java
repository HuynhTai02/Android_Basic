package com.tai.face_emoij;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.tai.face_emoij.databinding.LayoutCustomCloseDialogBinding;

public class CloseDialog extends Dialog {

    private final LayoutCustomCloseDialogBinding binding;
    private OnCloseApp callBack;

    public void setOnCloseApp(OnCloseApp callBack) {
        this.callBack = callBack;
    }

    public CloseDialog(@NonNull Context context) {
        super(context,R.style.Theme_Dialog);
        binding = LayoutCustomCloseDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
    }

    private void initViews() {
        setCancelable(false);
        binding.btOk.setOnClickListener(v -> closeApp());
        binding.btDont.setOnClickListener(v -> dismiss());// dismiss tắt dialog
    }

    private void closeApp() {
        callBack.closeApp();
    }

    public interface OnCloseApp {
        void closeApp();
    }
}
