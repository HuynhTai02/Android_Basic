package com.tai.demofragment2.act;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

public abstract class BaseAct<T extends ViewBinding> extends AppCompatActivity implements View.OnClickListener {
    protected T binding;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = initViewBinding();
        setContentView(binding.getRoot());
        initViews();
    }

    protected abstract void initViews();

    protected abstract T initViewBinding();

    @Override
    public final void onClick(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in));
        clickView(v);
    }

    protected void clickView(View v) {
        // do nothing
    }
}
