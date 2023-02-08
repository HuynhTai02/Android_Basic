package com.tai.internalstorage;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tai.internalstorage.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<StoryModel> listStory;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
    }

    private void initViews() {

        addPhotoTopics();
        readStoryFiles();
        binding.btNext.setOnClickListener(v -> {
            if (index > listStory.size() - 1) {
                index = 0;
            }
            setContentStory();
        });

        binding.btBack.setOnClickListener(v -> {
            index--;
            if (index < 0) {
                index = listStory.size() - 1;
            }
            setContentStory();
        });
    }

    private void readStoryFiles() {
        //Tạo một đối tượng AssetManager để truy cập tới thư mục Assets.
        AssetManager assetManager = getAssets();
        listStory = new ArrayList<>();
        try {
            InputStream in = assetManager.open("story/Con gái.txt");
            InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);

            String name = null;
            StringBuilder content = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                if (name == null) {
                    name = line;
                } else if (line.contains("','0');")) {
                    StoryModel model = new StoryModel(name, content.toString());
                    listStory.add(model);
                    name = null;
                    content = new StringBuilder();
                } else if (!line.isEmpty()) {
                    content.append(line).append("\n");
                }
                line = reader.readLine();
            }
            reader.close();
            isr.close();
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        setContentStory();
    }

    private void setContentStory() {
        binding.tvName.setText(listStory.get(index).getName());
        binding.tvContent.setText(listStory.get(index).getContent());
    }

    private void addPhotoTopics() {
        //Tạo một đối tượng AssetManager để truy cập tới thư mục Assets.
        AssetManager assetManager = getAssets();
        try {
            // Tạo một đối tượng AssetManager để truy cập tới thư mục Assets.
            String[] listFileName = assetManager.list("photo/");

            // Xóa tất cả các view trong LinearLayout "binding.lnTopic".
            binding.lnTopic.removeAllViews();

            //Duyệt qua từng tên file trong danh sách
            for (String photoPath : listFileName) {
                //Tạo một view mới từ Layout "item_topic".
                @SuppressLint("InflateParams")
                View itemView = LayoutInflater.from(this).inflate(R.layout.item_topic, null);
                ImageView imageView = itemView.findViewById(R.id.imv_photo);
                TextView tvName = itemView.findViewById(R.id.tv_photo);

                InputStream in = assetManager.open("photo/" + photoPath);
                Bitmap img = BitmapFactory.decodeStream(in);
                //Đặt Bitmap vào ImageView để hiển thị hình ảnh.
                imageView.setImageBitmap(img);
                tvName.setText(photoPath.replace(".png", ""));

                //Thêm view vào LinearLayout "binding.lnTopic".
                binding.lnTopic.addView(itemView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Truy cập 1 ảnh
//    private void initViews() {
//        // Truy cập đến thư mục Assets
//        AssetManager assetManager = getAssets();
//        try {
//            // Trỏ InputStream đến Assets
//            // để đọc ảnh trong đường dẫn
//            InputStream in = assetManager.open("photo/Tam quốc.png");
//
//            // Chuyển đổi dữ liệu đọc từ InputStream
//            // thành một đối tượng Bitmap bằng phương thức decodeStream.
//            Bitmap img = BitmapFactory.decodeStream(in);
//
//            // Đặt Bitmap vào ImageView để hiển thị ảnh
//            binding.imvPhoto.setImageBitmap(img);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    // Truy cập tất cả ảnh trong đường dẫn
//    private void initViews() {
//        AssetManager assetManager = getAssets();
//
//        try {
//            String listFileName[] = assetManager.list("photo/");
//            InputStream in = assetManager.open("photo/" + listFileName[1]);
//
//            Bitmap img = BitmapFactory.decodeStream(in);
//
//            binding.imvPhoto.setImageBitmap(img);
//            binding.tvPhoto.setText(listFileName[1].replace(".png", ""));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}