package com.tai.demofragment2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tai.demofragment2.OnMainCallback;
import com.tai.demofragment2.databinding.FragmentDetailBinding;

public class DetailFragment extends Fragment implements View.OnClickListener {

    public static String TAG = DetailFragment.class.getName();
    public static final String KEY_UPDATE_ICON_DETAIL = "KEY_UPDATE_ICON_DETAIL";


    private FragmentDetailBinding binding;
    private Context context;
    private OnMainCallback callback;
    private Object data;

    public void setData(Object data) {
        this.data = data;
    }

    public void setCallback(OnMainCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    protected void initViews() {
        callback.callBack(KEY_UPDATE_ICON_DETAIL, null);

        binding.imvSound.setOnClickListener(this);
        binding.ivAnimal.setOnClickListener(this);

        if (data != null) {
            Object[] animal = (Object[]) data;
            int drawableId = (int) animal[0];
            String name = (String) animal[1];

            binding.ivAnimal.setImageResource(drawableId);
            binding.tvName.setText(name);
        }
    }

    @Override
    public void onClick(View v) {
        //Hiệu ứng khi click
        v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
    }
}

