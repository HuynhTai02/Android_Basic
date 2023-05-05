package com.huynhngoctai.mvvm.view.fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.huynhngoctai.mvvm.databinding.M000SplashFragmentBinding;
import com.huynhngoctai.mvvm.viewmodel.CommonVM;

public class M000SplashFrg extends BaseFragment<M000SplashFragmentBinding, CommonVM> {
    private static final long TIME_DELAY = 2000;
    public static String TAG = M000SplashFrg.class.getName();

    @Override
    protected M000SplashFragmentBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return M000SplashFragmentBinding.inflate(inflater, container, false);
    }

    @Override
    protected Class<CommonVM> initViewModel() {
        return CommonVM.class;
    }

    @Override
    protected void initViews() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gotoMainScreen();
            }
        }, TIME_DELAY);
    }

    private void gotoMainScreen() {
    }
}
