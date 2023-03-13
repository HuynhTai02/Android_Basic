package com.tai.view_example_utc2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;

import com.tai.view_example_utc2.databinding.ActRadioBinding;

public class RadioActivity extends AppCompatActivity {

    private ActRadioBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActRadioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvent();
    }

    private void addEvent() {
        binding.btnConfirm.setOnClickListener(v -> {
            String text = "Bạn đã đánh giá: ";

//            if (binding.radio1.isChecked()) {
//                text += binding.radio1.getText().toString();
//            } else if (binding.radio2.isChecked()) {
//                text += binding.radio2.getText().toString();
//            } else if (binding.radio3.isChecked()) {
//                text += binding.radio3.getText().toString();
//            } else if (binding.radio4.isChecked()) {
//                text += binding.radio4.getText().toString();
//            } else {
//                text += "Rỗng";
//            }

            int checkIdRadio = binding.groupRadio.getCheckedRadioButtonId();
            if (checkIdRadio > 0) {
                RadioButton radio = findViewById(checkIdRadio);
                text += radio.getText().toString();
            } else {
                text += "Rỗng";
            }
            binding.tvAnswer.setText(text);
        });
    }
}
