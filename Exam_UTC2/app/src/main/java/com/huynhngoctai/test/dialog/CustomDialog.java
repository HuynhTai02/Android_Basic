package com.huynhngoctai.test.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.huynhngoctai.test.databinding.ItemDialogBinding;

public class CustomDialog extends Dialog {
    private ItemDialogBinding binding;

    public CustomDialog(@NonNull Context context, int imgPhoto, String txtName, Double txtRating, String txtAddress, String dishName) {
        super(context);
        binding = ItemDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.dlPhoto.setImageResource(imgPhoto);
        binding.dlPlaceName.setText(txtName);
        binding.dlRatingvalue.setText(String.valueOf(txtRating));
        binding.dlAddress.setText(txtAddress);
        binding.dlDishName.setText(dishName);
    }
}

