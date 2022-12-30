package com.tai.demofragment.view.act;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewbinding.ViewBinding;

import com.tai.demofragment.R;
import com.tai.demofragment.view.OnMainCallBack;
import com.tai.demofragment.view.fragment.BaseFragment;
import com.tai.demofragment.view.fragment.DetailFragment;

import java.lang.reflect.Constructor;

public abstract class BaseAct<T extends ViewBinding> extends AppCompatActivity implements View.OnClickListener, OnMainCallBack {
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

    @Override
    public void showFragment(String tag, Object data, boolean isBacked) {
        try {
            //Cơ chế tạo fragment
            Class<?> clazz = Class.forName(tag);
            Constructor<?> constructor = clazz.getConstructor();
            BaseFragment<?> baseFragment = (BaseFragment<?>) constructor.newInstance();

            baseFragment.setCallBack(this);
            baseFragment.setData(data);

            // Nhúng fragment vào Activity
            FragmentTransaction trans = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fr_main, baseFragment, DetailFragment.TAG);

            if (isBacked) {
                trans.addToBackStack(null);//lưu của thằng cũ để khi vuốt quay về tại layout mới trả về thằng cũ
            }
            trans.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
