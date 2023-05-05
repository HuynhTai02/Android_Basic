package com.huynhngoctai.mvvm.view.act;

import com.huynhngoctai.mvvm.databinding.ActivityHomeBinding;
import com.huynhngoctai.mvvm.view.fragment.M000SplashFrg;
import com.huynhngoctai.mvvm.viewmodel.CommonVM;

public class HomeActivity extends BaseAct<ActivityHomeBinding, CommonVM> {

    @Override
    protected ActivityHomeBinding initViewBinding() {
        return ActivityHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    protected Class<CommonVM> initViewModel() {
        return CommonVM.class;
    }

    @Override
    protected void initViews() {
        showFragment(M000SplashFrg.TAG, null, false);
    }
}