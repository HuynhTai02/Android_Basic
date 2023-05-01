package com.huynhngoctai.testmediaplayer2.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.huynhngoctai.testmediaplayer2.R;
import com.huynhngoctai.testmediaplayer2.model.Song;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {

    private final List<Song> songList;
    private final Context context;

    //Bắt sự kiện click vào tiêu đề của một bài hát
    private final View.OnClickListener event;

    //Lưu trữ vị trí bài hát hiện tại đang được play trong songList
    private int currentSong;

    public SongAdapter(List<Song> songList, Context context, View.OnClickListener event) {
        this.songList = songList;
        this.context = context;
        this.event = event;
    }

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_song, parent, false);
        return new SongHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder holder, int position) {
        Song song = songList.get(position);

        if (position == currentSong) {
            holder.view.setBackgroundResource(R.color.blue);
        } else {
            holder.view.setBackgroundResource(R.color.white);
        }
        if(song.uri != null){
            holder.ivSong.setImageURI(song.uri);
        }else {
            holder.ivSong.setImageResource(R.drawable.ic_music_note);
        }
        holder.tvTitleSong.setText(song.title);
        holder.tvTitleSong.setTag(song);
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public void updateUI(int currentSong) {
        this.currentSong = currentSong;
        //thông báo khi có sự thay đổi về bài hát để cập nhật lại màu nền cho bài hát đang đc phát
        notifyItemRangeChanged(0, songList.size());
    }

    public class SongHolder extends RecyclerView.ViewHolder {
        View view;
        TextView tvTitleSong;
        ImageView ivSong;

        public SongHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView.findViewById(R.id.view);
            ivSong = itemView.findViewById(R.id.ivSong);
            tvTitleSong = itemView.findViewById(R.id.tvTitleSong);
            tvTitleSong.setOnClickListener(event);
        }
    }
}
