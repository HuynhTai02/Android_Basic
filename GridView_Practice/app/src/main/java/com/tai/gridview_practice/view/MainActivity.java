package com.tai.gridview_practice.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import com.tai.gridview_practice.R;
import com.tai.gridview_practice.adapter.BeerAdapter;
import com.tai.gridview_practice.databinding.ActivityMainBinding;
import com.tai.gridview_practice.model.Beer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private BeerAdapter beerAdapter;
    private ArrayList<Beer> beerArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initAdapter();
        addEvents();
    }

    private void addEvents() {
        binding.gvBeer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ActivityDetail.class);
                intent.putExtra("name", beerArrayList.get(position).getNameBeer());
                intent.putExtra("idImg", beerArrayList.get(position).getThumbBeer());
                startActivity(intent);
            }
        });
    }

    private void initAdapter() {
        initData();
        beerAdapter = new BeerAdapter(MainActivity.this, R.layout.item_beer, beerArrayList);
        binding.gvBeer.setAdapter(beerAdapter);
    }

    private void initData() {
        beerArrayList = new ArrayList<>();
        beerArrayList.add(new Beer(R.drawable.heiniken, "Heiniken"));
        beerArrayList.add(new Beer(R.drawable.hanoi, "Hà Nội"));
        beerArrayList.add(new Beer(R.drawable.tiger, "Tiger"));
        beerArrayList.add(new Beer(R.drawable.beer333, "Beer333"));
        beerArrayList.add(new Beer(R.drawable.larue, "Larue"));
        beerArrayList.add(new Beer(R.drawable.sapporo, "Sapporo"));
    }
}