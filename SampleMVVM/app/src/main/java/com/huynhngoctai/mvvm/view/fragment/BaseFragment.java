package com.huynhngoctai.mvvm.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.huynhngoctai.mvvm.view.OnMainCallBack;

public abstract class BaseFragment<B extends ViewBinding, V extends ViewModel>
        extends Fragment implements View.OnClickListener {
    public static final String TAG = BaseFragment.class.getName();
    protected Context context;
    protected B binding;
    protected V viewModel;
    protected Object mData;
    protected OnMainCallBack callBack;

    public final void setCallBack(OnMainCallBack callBack) {
        this.callBack = callBack;
    }

    public void setmData(Object data) {
        this.mData = data;
    }

    @Override
    public final void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater
            , @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {

        binding = initViewBinding(inflater, container);
        viewModel = new ViewModelProvider(this).get(initViewModel());
        initViews();
        return binding.getRoot();
    }

    protected abstract B initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);
    protected abstract Class<V> initViewModel();
    protected abstract void initViews();

    @Override
    public final void onClick(View v) {
        v.setAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
        clickView(v);
    }

    protected void clickView(View v) {
        //do nothing
    }
}