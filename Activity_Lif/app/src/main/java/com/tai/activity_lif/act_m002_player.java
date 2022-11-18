package com.tai.activity_lif;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tai.activity_lif.databinding.ActM002PlayerBinding;

public class act_m002_player extends AppCompatActivity {
    private ActM002PlayerBinding binding;
    private MediaPlayer mPlayer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActM002PlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
    }

    private void initViews() {
        binding.ivPlay.setOnClickListener(view -> playMusic());
    }

    private void playMusic() {
        if (mPlayer == null) {
            mPlayer = MediaPlayer.create(this, R.raw.ic_song);
            mPlayer.start();

        } else {
            if (mPlayer.isPlaying()) {
                mPlayer.pause();
                binding.ivPlay.setImageLevel(0);
            } else {
                mPlayer.start();
                binding.ivPlay.setImageLevel(1);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mPlayer != null && !mPlayer.isPlaying()) {
            mPlayer.start();
            binding.ivPlay.setImageLevel(1);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.pause();
            binding.ivPlay.setImageLevel(0);

        }
    }
}
