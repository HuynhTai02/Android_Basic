package com.huynhngoctai.testmediaplayer;

import android.media.MediaPlayer;

public class MediaManager {
    private MediaPlayer bgPlayer;
    private MediaPlayer gamePlayer;
    private static MediaManager instance;

    public static MediaManager getInstance() {
        if (instance == null) {
            instance = new MediaManager();
        }
        return instance;
    }

    public void playBg(int song, boolean isLooping) {
        if (this.bgPlayer != null) {
            this.bgPlayer.reset();
        }
        bgPlayer = play(song, isLooping);
    }

    public void playGame(int song, MediaPlayer.OnCompletionListener event) {
        playGame(song, false, event);
    }

    public void playGame(int song, boolean isLooping, MediaPlayer.OnCompletionListener event) {
        if (this.gamePlayer != null) {
            this.gamePlayer.reset();
        }
        gamePlayer = play(song, isLooping);
        gamePlayer.setOnCompletionListener(event);
    }

    public void playBg() {
        play(bgPlayer);
    }

    public void playGameSong() {
        play(gamePlayer);
    }

    public void pauseBg() {
        pause(bgPlayer);
    }

    public void pauseGameSong() {
        pause(gamePlayer);
    }

    public void stopBg() {
        bgPlayer = stop(bgPlayer);
    }

    public void stopGameSong() {
        gamePlayer = stop(gamePlayer);
    }

    public MediaPlayer play(int song, boolean isLooping) {
        MediaPlayer player = MediaPlayer.create(App.getInstance(), song);
        player.setLooping(isLooping);
        player.start();

        return player;
    }

    private void play(MediaPlayer player) {
        if (player != null && !player.isPlaying()) {
            player.start();
        }
    }

    public void pause(MediaPlayer player) {
        if (player != null && player.isPlaying()) {
            player.pause();
        }
    }

    private MediaPlayer stop(MediaPlayer player) {
        if (player != null) {
            player.reset();
        }

        return null;
    }
}
