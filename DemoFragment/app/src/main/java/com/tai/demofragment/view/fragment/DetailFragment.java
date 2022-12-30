package com.tai.demofragment.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.tai.demofragment.databinding.FragmentDetailBinding;

public class DetailFragment extends BaseFragment<FragmentDetailBinding> {
    public static final String TAG = DetailFragment.class.getName();
    public static final String KEY_UPDATE_ICON_DETAIL = "KEY_UPDATE_ICON_DETAIL" ;

    @Override
    protected FragmentDetailBinding initViewBinding(LayoutInflater inflater) {
        return FragmentDetailBinding.inflate(inflater);
    }

    @Override
    protected void initViews() {
        binding.ivAnimal.setOnClickListener(this);
        if (data != null) {
            Object[] animal = (Object[]) data;
            int drawableId = (int) animal[0];
            String name = (String) animal[1];

            binding.ivAnimal.setImageResource(drawableId);
            binding.tvName.setText(name);
        }
        callBack.callBack(KEY_UPDATE_ICON_DETAIL, null);

    }

    @Override
    public void clickView(View v) {

    }
}
