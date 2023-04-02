package com.tai.assets_sharedpreferences_ex_utc2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.tai.assets_sharedpreferences_ex_utc2.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {
    private ActivityMain2Binding binding;
    public static final String PREFERENCES_NAME = "my_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents() {
        binding.btSave.setOnClickListener(v -> {
            // Save data into SharedPreferences
            SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();

            // Attach data
            editor.putInt("numb", 89);
            editor.putFloat("grades", 89.5f);
            editor.putBoolean("checked", true);
            editor.putString("say", "Hello");

            // Save SharedPreferences
            editor.apply();
        });

        binding.btLoadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Load data
                SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_NAME,MODE_PRIVATE);
                int numb = sharedPreferences.getInt("numb", 0);
                float grades = sharedPreferences.getFloat("grades", 0.0f);
                boolean checked = sharedPreferences.getBoolean("checked", false);
                String say = sharedPreferences.getString("say", "");

                binding.tvLoadData.setText("");
                binding.tvLoadData.append("Numb: " + numb +"\n");
                binding.tvLoadData.append("Grades: " + grades +"\n");
                binding.tvLoadData.append("Checked: " + checked +"\n");
                binding.tvLoadData.append("Say: " + say +"\n");
            }
        });
    }
}