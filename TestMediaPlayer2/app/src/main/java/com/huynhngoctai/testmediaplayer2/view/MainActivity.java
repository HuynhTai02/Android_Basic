package com.huynhngoctai.testmediaplayer2.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.huynhngoctai.testmediaplayer2.Mp3Player;
import com.huynhngoctai.testmediaplayer2.OnSeekBarChange;
import com.huynhngoctai.testmediaplayer2.R;
import com.huynhngoctai.testmediaplayer2.adapter.SongAdapter;
import com.huynhngoctai.testmediaplayer2.databinding.ActivityMainBinding;
import com.huynhngoctai.testmediaplayer2.model.Song;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;
    private static final int LEVEL_IDLE = 0;
    private static final int LEVEL_PLAY = 1;
    private boolean appRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
    }

    private void initViews() {
        checkUserPermission();
    }

    private void checkUserPermission() {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 101);
        } else {
            //Load nhạc lên
            Mp3Player.getInstance().loadMusicOffline();
            //Khi nhạc phát xong
            //Lớp Mp3Player -> gọi đến phương thức OnCompletionListener
            // thông báo cho Main bài hát đã phát xong
            Mp3Player.getInstance().setCompleteCallBack(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    updateUI();
                }
            });
            //Hiển thị lên recylerview
            initListSong();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Mp3Player.getInstance().loadMusicOffline();
        } else {
            Toast.makeText(this, "Please allow this permission to use App", Toast.LENGTH_SHORT).show();
        }
    }

    private void initListSong() {
        binding.includeItemController.ivPlay.setOnClickListener(this);
        binding.includeItemController.ivNext.setOnClickListener(this);
        binding.includeItemController.ivBack.setOnClickListener(this);
        binding.includeItemController.seekbar.setOnSeekBarChangeListener((OnSeekBarChange) seekBar -> Mp3Player.getInstance().seekTo(seekBar.getProgress()));

        binding.rvSong.setLayoutManager(new LinearLayoutManager(this));
        binding.rvSong.setAdapter(new SongAdapter(Mp3Player.getInstance().getSongList(), this, v -> {
            v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this
                    , androidx.appcompat.R.anim.abc_fade_in));
            doClickItemSong((Song) v.getTag());
        }));

        appRunning = true;
        new Thread(this::updateSeekBar).start();
    }

    private void updateSeekBar() {
        while (appRunning) {
            try {
                Thread.sleep(500);
                runOnUiThread(() -> {
                    String currentTimeText = Mp3Player.getInstance().getCurrentTimeText();
                    String totalTimeText = Mp3Player.getInstance().getTotalTimeText();
                    int totalTime = Mp3Player.getInstance().getTotalTime();
                    int currentTime = Mp3Player.getInstance().getCurrentTime();
                    binding.includeItemController.seekbar.setMax(totalTime);
                    binding.includeItemController.seekbar.setProgress(currentTime);
                    binding.includeItemController.tvDuration.setText(String.format("%s/%s", currentTimeText, totalTimeText));
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void doClickItemSong(Song song) {
        Mp3Player.getInstance().playMusic(song);
        updateUI();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivPlay) {
            Mp3Player.getInstance().playMusic();
        } else if (v.getId() == R.id.ivNext) {
            Mp3Player.getInstance().next();
        } else if (v.getId() == R.id.ivBack) {
            Mp3Player.getInstance().back();
        }

        updateUI();
    }

    private void updateUI() {
        if (Mp3Player.getInstance().getState() == Mp3Player.STATE_PLAYING) {
            binding.includeItemController.ivPlay.setImageLevel(LEVEL_PLAY);
        } else {
            binding.includeItemController.ivPlay.setImageLevel(LEVEL_IDLE);
        }

        Song song = Mp3Player.getInstance().getCurrenSong();
        binding.includeItemController.tvName.setText(song.title);
        binding.includeItemController.tvArtist.setText(song.artist);
        ((SongAdapter) Objects.requireNonNull(binding.rvSong.getAdapter())).updateUI(Mp3Player.getInstance().getCurrentIndex());
    }
}