package com.tai.viewp2_ex_utc2.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tai.viewp2_ex_utc2.R;
import com.tai.viewp2_ex_utc2.adapter.BeerAdapter;
import com.tai.viewp2_ex_utc2.databinding.ActivityBeersBinding;
import com.tai.viewp2_ex_utc2.model.Beer;

import java.util.ArrayList;

public class BeersActivity extends AppCompatActivity {
    private ActivityBeersBinding binding;
    private BeerAdapter beerAdapter;
    ArrayList<Beer> beerArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBeersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initAdapter();
        addEvents();
    }

    private void addEvents() {

    }

    private void initAdapter() {
        initData();
        beerAdapter = new BeerAdapter(BeersActivity.this,
                R.layout.item_custom_listview_beer, beerArrayList);
        binding.lvBeers.setAdapter(beerAdapter);

    }

    private void initData() {
        beerArrayList = new ArrayList<>();
        beerArrayList.add(new Beer(R.drawable.heineken,"Heiniken", 19000.0));
        beerArrayList.add(new Beer(R.drawable.hanoi,"Hà Nội", 15000.0));
        beerArrayList.add(new Beer(R.drawable.tiger,"Tiger", 16000.0));
        beerArrayList.add(new Beer(R.drawable.beer333,"Beer333", 17000.0));
        beerArrayList.add(new Beer(R.drawable.larue,"Larue", 25000.0));
        beerArrayList.add(new Beer(R.drawable.sapporo,"Sapporo", 14000.0));
   }
}