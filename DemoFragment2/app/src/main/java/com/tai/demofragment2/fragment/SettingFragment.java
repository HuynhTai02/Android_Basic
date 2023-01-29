package com.tai.demofragment2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tai.demofragment2.OnMainCallback;
import com.tai.demofragment2.databinding.FragmentSettingBinding;


public class SettingFragment extends Fragment {

    public static final String TAG = SettingFragment.class.getName();
    public static final String KEY_UPDATE_ICON_SETTING = "KEY_UPDATE_ICON_SETTING";

    private FragmentSettingBinding binding;
    private Context context;
    private OnMainCallback callback;

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
        //binding = FragmentListBinding.inflate(inflater, container,false);
        binding = FragmentSettingBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        callback.callBack(KEY_UPDATE_ICON_SETTING, null);
    }
}
