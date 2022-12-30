package com.tai.demofragment.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.tai.demofragment.view.OnMainCallBack;

public abstract class BaseFragment<V extends ViewBinding> extends Fragment implements View.OnClickListener {
    protected V binding;

    protected Context context;
    protected Object data;
    protected OnMainCallBack callBack;

    public void setCallBack(OnMainCallBack callBack) {
        this.callBack = callBack;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public final void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    //Lấy file XML UI của Fragment ra file code
    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = initViewBinding(inflater);
        return binding.getRoot();
    }

    protected abstract V initViewBinding(LayoutInflater inflater);

    @Override
    public final void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    protected abstract void initViews();

    @Override
    public final void onClick(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
        clickView(v);
    }

    protected void clickView(View v) {
        // do nothing
    }
}
