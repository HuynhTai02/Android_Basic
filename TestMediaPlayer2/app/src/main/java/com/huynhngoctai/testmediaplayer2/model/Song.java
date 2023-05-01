package com.huynhngoctai.testmediaplayer2.model;

import android.net.Uri;

public class Song {
    public String title;
    public String path;
    public String album;
    public String artist;
    public Uri uri;
    public Song(String title, String path, String album, String artist, Uri uri) {
        this.title = title;
        this.path = path;
        this.album = album;
        this.artist = artist;
        this.uri = uri;
    }
}
