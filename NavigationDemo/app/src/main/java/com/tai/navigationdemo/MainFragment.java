package com.tai.navigationdemo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.tai.navigationdemo.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {
    private FragmentMainBinding binding;
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
    }

    private void initViews() {
        binding.btFirst.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_mainFragment2_to_firstFragment));
        binding.btSecond.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_mainFragment2_to_secondFragment));
        binding.btThird.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_mainFragment2_to_thirdFragment));

    }
}