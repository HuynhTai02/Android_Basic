package com.tai.demofragment.view.act;

import android.graphics.PorterDuff;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.tai.demofragment.R;
import com.tai.demofragment.databinding.ActivityMainBinding;
import com.tai.demofragment.view.OnMainCallBack;
import com.tai.demofragment.view.fragment.DetailFragment;
import com.tai.demofragment.view.fragment.ListFragment;

public class MainActivity extends BaseAct<ActivityMainBinding> implements OnMainCallBack {
    @Override
    protected void initViews() {
        binding.menu.ivList.setOnClickListener(v -> showFragment(ListFragment.TAG, null, false));
        binding.menu.ivDetail.setOnClickListener(v -> showFragment(DetailFragment.TAG, null, true));
        binding.menu.ivSetting.setOnClickListener(v -> changeSelectedItem((ImageView) v));

        showFragment(ListFragment.TAG, null, false);

    }

    @Override
    public void callBack(String key, Object data) {
        if (key.equals(ListFragment.KEY_UPDATE_ICON_LIST)) {
            changeSelectedItem(binding.menu.ivList);
        } else if (key.equals(DetailFragment.KEY_UPDATE_ICON_DETAIL)) {
            changeSelectedItem(binding.menu.ivDetail);
        }
    }

    private void changeSelectedItem(ImageView v) {
        binding.menu.ivList.setColorFilter(ContextCompat.getColor(this, R.color.purple_200), PorterDuff.Mode.SRC_IN);
        binding.menu.ivDetail.setColorFilter(ContextCompat.getColor(this, R.color.purple_200), PorterDuff.Mode.SRC_IN);
        binding.menu.ivSetting.setColorFilter(ContextCompat.getColor(this, R.color.purple_200), PorterDuff.Mode.SRC_IN);
        v.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);
    }

    @Override
    protected ActivityMainBinding initViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }
}