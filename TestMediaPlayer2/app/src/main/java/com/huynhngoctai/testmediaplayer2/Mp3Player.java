package com.huynhngoctai.testmediaplayer2;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.huynhngoctai.testmediaplayer2.model.Song;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Mp3Player {
    public static final int STATE_IDLE = 1;
    public static final int STATE_PLAYING = 2;
    public static final int STATE_PAUSED = 3;
    private int state = STATE_IDLE;
    private final MediaPlayer mediaPlayer;
    private static Mp3Player instance;
    private final List<Song> songList = new ArrayList<>();
    private int currentSong;

    //Đối tượng MediaPlayer.OnCompletionListener
    //dùng để bắt sự kiện khi một bài hát kết thúc và chuyển sang bài hát tiếp theo.
    private MediaPlayer.OnCompletionListener onCompletionListenerCallBack;
    public List<Song> getSongList() {
        return songList;
    }

    private Mp3Player() {
        //for singleton
        mediaPlayer = new MediaPlayer();

        //Lắng nghe sự kiện khi kết thúc bài hát
        mediaPlayer.setOnCompletionListener(mp -> {
            next();
            onCompletionListenerCallBack.onCompletion(null);
        });

        //Thiết lập các thuộc tính âm thanh
        // cho việc điều khiển bằng các nút bấm trên tai nghe có thể hoạt động.
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build());
    }

    public int getState() {
        return state;
    }

    public static Mp3Player getInstance() {
        if (instance == null) {
            instance = new Mp3Player();
        }
        return instance;
    }

    //https://stackoverflow.com/questions/44040828/get-album-cover-image
    public static Uri getArtUriFromMusicFile(Context context, File file) {
        final Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        final String[] cursor_cols = {MediaStore.Audio.Media.ALBUM_ID};

        final String where = MediaStore.Audio.Media.IS_MUSIC + "=1 AND "
                + MediaStore.Audio.Media.DATA + " = '"
                + file.getAbsolutePath() + "'";
        final Cursor cursor = context.getApplicationContext()
                .getContentResolver()
                .query(uri, cursor_cols, where, null, null);

//        If the cusor count is greater than 0 then parse the data and get the art id.
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            long albumId = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));

            Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
            Uri albumArtUri = ContentUris.withAppendedId(sArtworkUri, albumId);
            cursor.close();
            return albumArtUri;
        }
        return Uri.EMPTY;
    }

    public void loadMusicOffline() {
        //Đối tượng Cursor là con trỏ được sử dụng để truy vấn csdl
        Cursor cursor = App.getInstance()
                .getContentResolver()
                .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media.TITLE + " ASC");
        //Di chuyển con trỏ của đối tượng Cursor về dòng đầu tiên trong kq truy vấn
        cursor.moveToFirst();

        //Lấy ra vị trí (index) của các cột trong bảng
        int cTitle = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int cPath = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
        int cAlbum = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
        int cArtist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
        songList.clear();

        //Kiểm tra vị trí của con trỏ ở hàng cuối cùng chưa ?
        while (!cursor.isAfterLast()) {
            //Lấy các cột thông tin của hàng hiện tại
            String title = cursor.getString(cTitle);
            String path = cursor.getString(cPath);
            String album = cursor.getString(cAlbum);
            String artist = cursor.getString(cArtist);
            Uri uri = getArtUriFromMusicFile(App.getInstance(), new File(path));
            //Khởi tạo song với các thông số đã lấy được và thêm vào songList
            Song song = new Song(title, path, album, artist, uri);
            songList.add(song);
            //Nếu chưa tiếp tục di chuyển con trỏ đến hàng tiếp theo và tiếp tục lấy thông tin hàng đó
            cursor.moveToNext();
        }

        //Sau khi lấy xong tất cả dữ liệu
        //cần giải phóng tài nguyên
        cursor.close();
        Log.i(Mp3Player.class.getName(), "List Song: " + songList);
    }

    //Phát nhạc
    public void playMusic() {
        if (state == STATE_IDLE) {
            mediaPlayer.reset();
            try {
                mediaPlayer.setDataSource(songList.get(currentSong).path);
                mediaPlayer.prepare();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            mediaPlayer.start();
            state = STATE_PLAYING;
        } else if (state == STATE_PAUSED) {
            mediaPlayer.start();
            state = STATE_PLAYING;
        } else {
            mediaPlayer.pause();
            state = STATE_PAUSED;
        }
    }

    //Next Song
    public void next() {
        currentSong++;
        if (currentSong >= songList.size()) {
            currentSong = 0;
        }

        state = STATE_IDLE;
        playMusic();
    }

    //Back Song
    public void back() {
        currentSong--;
        if (currentSong <= 0) {
            currentSong = songList.size() - 1;
        }

        state = STATE_IDLE;
        playMusic();
    }

    public void playMusic(Song song) {
        currentSong = songList.indexOf(song);
        state = STATE_IDLE;

        playMusic();
    }

    //Lấy trị trí hiện tại bài hát đang được play
    public int getCurrentIndex() {
        return currentSong;
    }

    //Lấy ra bài hát hiện tại đang được play
    public Song getCurrenSong() {
        return songList.get(currentSong);
    }

    public String getCurrentTimeText() {
        try {
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat df = new SimpleDateFormat("mm:ss");
            return df.format(new Date(mediaPlayer.getCurrentPosition()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "--";
    }

    public String getTotalTimeText() {
        try {
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat df = new SimpleDateFormat("mm:ss");
            return df.format(new Date(mediaPlayer.getDuration()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "--";
    }

    //Tổng thời gian bài hát
    public int getTotalTime() {
        return mediaPlayer.getDuration();
    }

    //Thời gian hiện tại  bài hát đang được phát
    public int getCurrentTime() {
        return mediaPlayer.getCurrentPosition();
    }

    public void seekTo(int progress) {
        mediaPlayer.seekTo(progress);
    }

    //Phương thức được gọi khi bài hát được phát xong
    public void setCompleteCallBack(MediaPlayer.OnCompletionListener listener) {
        onCompletionListenerCallBack = listener;
    }
}
