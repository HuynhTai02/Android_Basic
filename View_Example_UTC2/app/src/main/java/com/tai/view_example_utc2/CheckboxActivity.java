package com.tai.view_example_utc2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tai.view_example_utc2.databinding.ActCheckboxBinding;

public class CheckboxActivity extends AppCompatActivity {

    private ActCheckboxBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActCheckboxBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvent();
    }

    private void addEvent() {
        binding.btConfirm.setOnClickListener(v -> {
            String text = "Bạn đã chọn: ";

            if (binding.cbClip.isChecked()) {
                text += binding.cbClip.getText().toString() + ", ";
            }
            if (binding.cbFilm.isChecked()) {
                text += binding.cbFilm.getText().toString() + ", ";
            }
            if (binding.cbFpt.isChecked()) {
                text += binding.cbFpt.getText().toString() + ", ";
            }

//            text += binding.cbClip.isChecked() ? binding.cbClip.getText() + ", " : " ";
//            text += binding.cbFilm.isChecked() ? binding.cbFilm.getText() + ", " : " ";
//            text += binding.cbFpt.isChecked() ? binding.cbFpt.getText() + ", " : " ";

            binding.tvAnswer.setText(text);

        });
    }
}