package com.tai.assets_sharedpreferences_ex_utc2;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.tai.assets_sharedpreferences_ex_utc2.databinding.ActivityMainBinding;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayAdapter<String> adapter;
    String[] fontList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadFonts();
        addEvents();
    }

    private void addEvents() {
        binding.lvFonts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/" + adapter.getItem(position));
                binding.tvContent.setTypeface(tf);
                playAudio();
            }
        });
    }

    private void playAudio() {
        try {
            AssetFileDescriptor assetFileDescriptor = getAssets().openFd("musics/ting.mp3");
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            assetFileDescriptor.close();
            mediaPlayer.prepare();
            mediaPlayer.start();
            ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFonts() {
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1);
        AssetManager manager = getAssets();
        try {
            fontList = manager.list("fonts");
            adapter.addAll(fontList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        binding.lvFonts.setAdapter(adapter);
    }
}