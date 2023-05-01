package com.huynhngoctai.testmediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

import com.huynhngoctai.testmediaplayer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
    }

    private void initViews() {
        binding.ivBgSong.setOnClickListener(v -> playBgSong());

        binding.ivStopBg.setOnClickListener(v -> stopBgSong());

        binding.ivGameSong.setOnClickListener(v->playGameSong());
    }

    private void playGameSong() {
        MediaManager.getInstance().playGame(R.raw.song_rule, new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                MediaManager.getInstance().playGame(R.raw.song_ready, null);
            }
        });
    }

    private void stopBgSong() {
        MediaManager.getInstance().stopBg();
    }

    private void playBgSong() {
        MediaManager.getInstance().playBg(R.raw.song_bg_music, true);
    }

    @Override
    protected void onStart() {
        MediaManager.getInstance().playBg();
        super.onStart();
    }

    @Override
    protected void onStop() {
        MediaManager.getInstance().pauseBg();
        super.onStop();
    }
}