package com.tai.demofragment2.act;

import android.graphics.PorterDuff;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.tai.demofragment2.OnMainCallback;
import com.tai.demofragment2.R;
import com.tai.demofragment2.databinding.ActivityMainBinding;
import com.tai.demofragment2.fragment.DetailFragment;
import com.tai.demofragment2.fragment.ListFragment;
import com.tai.demofragment2.fragment.SettingFragment;

public class MainActivity extends BaseAct<ActivityMainBinding> implements OnMainCallback {

    @Override
    protected void initViews() {
        binding.menu.ivList.setOnClickListener(v -> {
            changleSelectedItem((ImageView) v);
            showListFragment();
        });

        binding.menu.ivDetail.setOnClickListener(v -> {
            changleSelectedItem((ImageView) v);
            showDetailFragment(null);
        });

        binding.menu.ivSetting.setOnClickListener(v -> {
            changleSelectedItem((ImageView) v);
            showSettingFragment();
        });

        showListFragment();
    }

    // Nhúng Fragment vào Act
    private void showDetailFragment(Object data) {
        DetailFragment fragment = new DetailFragment();
        fragment.setData(data);
        fragment.setCallback(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fr_main, fragment, DetailFragment.TAG).addToBackStack(null).commit();
    }

    private void showListFragment() {
        ListFragment fragment = new ListFragment();
        fragment.setCallback(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fr_main, fragment, ListFragment.TAG).commit();
    }

    private void showSettingFragment() {
        SettingFragment fragment = new SettingFragment();
        fragment.setCallback(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fr_main, fragment, SettingFragment.TAG).addToBackStack(null).commit();
    }

    private void changleSelectedItem(ImageView v) {
        //setColorFilter phương thức của ImageView
        binding.menu.ivList.setColorFilter(ContextCompat.getColor(this, R.color.purple_200), PorterDuff.Mode.SRC_IN);
        binding.menu.ivDetail.setColorFilter(ContextCompat.getColor(this, R.color.purple_200), PorterDuff.Mode.SRC_IN);
        binding.menu.ivSetting.setColorFilter(ContextCompat.getColor(this, R.color.purple_200), PorterDuff.Mode.SRC_IN);
        v.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);
    }

    @Override
    protected ActivityMainBinding initViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    public void callBack(String key, Object data) {
        switch (key) {
            case ListFragment.KEY_UPDATE_ICON_LIST:
                changleSelectedItem(binding.menu.ivList);
                break;
            case DetailFragment.KEY_UPDATE_ICON_DETAIL:
                changleSelectedItem(binding.menu.ivDetail);
                break;
            case SettingFragment.KEY_UPDATE_ICON_SETTING:
                changleSelectedItem(binding.menu.ivSetting);
                break;
            case ListFragment.KEY_SHOW_DETAIL:
                showDetailFragment(data);
                break;
        }
    }
}