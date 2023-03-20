package com.huynhngoctai.test.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.huynhngoctai.test.R;
import com.huynhngoctai.test.adapter.RestaurantAdapter;
import com.huynhngoctai.test.databinding.ActivityRestaurantBinding;
import com.huynhngoctai.test.dialog.CustomDialog;
import com.huynhngoctai.test.model.Restaurant;

import java.util.ArrayList;

public class RestaurantActivity extends AppCompatActivity {
    private ActivityRestaurantBinding binding;
    RestaurantAdapter restaurantAdapter;
    ArrayList<Restaurant> restaurantArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initAdapter();
        addEvents();
    }

    private void initAdapter() {
        initData();
        restaurantAdapter = new RestaurantAdapter(RestaurantActivity.this,
                R.layout.item_restaurant, restaurantArrayList);
        binding.lvrestaurant.setAdapter(restaurantAdapter);

    }

    private void addEvents() {
        binding.lvrestaurant.setOnItemClickListener((adapterView, view, i, l) -> {
            Restaurant restaurant = (Restaurant) restaurantAdapter.getItem(i);
            Dialog dialog = new CustomDialog(RestaurantActivity.this, restaurant.getIdPhoto(), restaurant.getPlaceName(), restaurant.getRatingValue(), restaurant.getAddress(), restaurant.getDishName());
            dialog.show();
        });

        binding.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        restaurantArrayList = new ArrayList<Restaurant>();
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        int idImg = intent.getIntExtra("idImg",101);
        if (type.equals("LUNCH_BOX")) {
            binding.img.setImageResource(idImg);
            restaurantArrayList.add(new Restaurant("Cơm Tấm Đại Đồng", "Cơm Sườn",
                    R.drawable.bf_comquemuoikho, 4.1, "100+",
                    "38 Đường Số 17, P. Linh Trung, Q. Thủ Đức, Tp.HCM"));
            restaurantArrayList.add(new Restaurant("Bula Bento - Cơm Trưa", "Cơm Chiên Cá Hồi",
                    R.drawable.bf_bepnhaxuquang, 4.0, "499+",
                    "22 Đoàn Kết, P. Bình Thọ, Q. Thủ Đức, Tp.HCM"));
            restaurantArrayList.add(new Restaurant("Cơm Bình Dân Quang Ngân", "Cơm Gà Kho Xả Ớt",
                    R.drawable.bf_danhdacuadily,
                    4.0, "100+", "130 Linh Trung, P. Linh Trung, Q. Thủ Đức, Tp.HCM"));
            restaurantArrayList.add(new Restaurant("Cơm Gà - Tô Vĩnh Diện",
                    "Cơm Đùi Gà Chiên Nước Mắm", (R.drawable.bf_comquemuoikho),
                    4.0, "999+", "15 Tô Vĩnh Diện, P. Linh Chiểu, Q. Thủ Đức, Tp.HCM"));
            restaurantArrayList.add(new Restaurant("Cơm Niêu Phương Bắc", "Cơm Cá Cơm Rim Mặn",
                    R.drawable.bf_lemekong, 4.1, "100+",
                    "87 Hoàng Diệu 2, P. Linh Trung, Q. Thủ Đức, Tp.HCM"));
            restaurantArrayList.add(new Restaurant("Cơm Niêu Phương Bắc", "Cơm Cá Cơm Rim Mặn",
                    R.drawable.bf_beardpapa, 4.1, "100+",
                    "87 Hoàng Diệu 2, P. Linh Trung, Q. Thủ Đức, Tp.HCM"));
            restaurantArrayList.add(new Restaurant("Cơm Tấm Dì Ba", "Cơm Sườn Muối Ớt",
                    R.drawable.bf_themixhouse, 4.1, "999+",
                    "197B Lê Văn Việt, P. Hiệp Phú, Q. 9, Tp.HCM"));
        } else {
            restaurantArrayList.add(new Restaurant("Bún Đậu Mẹt Tre - Hoàng Diệu 2", "Bún Đậu Tá Lả",
                    R.drawable.lunch_hotpot, 4.0, "499+",
                    "177 Hoàng Diệu 2, P. Linh Trung, Q. Thủ Đức, Tp.HCM"));
            restaurantArrayList.add(new Restaurant("Bún Đậu A Chảnh - Tô Vĩnh Diện", "Bún Đậu Thập Cẩm",
                    R.drawable.lunch_kingbbq_alacarte, 4.5, "500+",
                    "26 Tô Vĩnh Diện, P. Linh Chiểu, Q. Thủ Đức, Tp.HCM"));
            restaurantArrayList.add(new Restaurant("Bún Đậu Mắm Tôm Tiểu Muội", "Bún Đậu Thập Cẩm",
                    R.drawable.lunch_elgaucho, 4.0, "400+",
                    "39 Hoàng Diệu 2, P. Linh Trung, Q. Thủ Đức, Tp.HCM"));
            restaurantArrayList.add(new Restaurant("Bún Thịt Nướng Dì 7", "Bún Thịt Nướng Đặc Biệt",
                    R.drawable.lunch_seoulgarden, 4.0, "300+",
                    "15 Tô Vĩnh Diện, P. Linh Chiểu, Q. Thủ Đức, Tp.HCM"));
            restaurantArrayList.add(new Restaurant("Bún Quậy Phú Quốc - Hoàng Diệu 2", "Bún Quậy Chả Tôm",
                    R.drawable.lunch_kingbbq_alacarte2, 4.6, "200+",
                    "204A Hoàng Diệu 2, P. Linh Chiểu, Q. Thủ Đức, Tp.HCM"));
        }
    }
}