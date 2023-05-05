package com.huynhngoctai.mvvm.view.act;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.huynhngoctai.mvvm.R;
import com.huynhngoctai.mvvm.view.OnMainCallBack;
import com.huynhngoctai.mvvm.view.fragment.BaseFragment;

import java.lang.reflect.Constructor;

public abstract class BaseAct<T extends ViewBinding, M extends ViewModel>
        extends AppCompatActivity implements View.OnClickListener, OnMainCallBack {
    public static String TAG = BaseAct.class.getName();
    protected T binding;
    protected M viewModel;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = initViewBinding();
        viewModel = new ViewModelProvider(this).get(initViewModel());
        setContentView(binding.getRoot());
        initViews();
    }

    protected abstract T initViewBinding();

    protected abstract Class<M> initViewModel();

    protected abstract void initViews();

    @Override
    public final void onClick(View v) {
        v.setAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in));
        clickView(v);
    }

    private void clickView(View v) {
    }

    protected final void notify(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected final void notify(int msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFragment(String tag, Object data, boolean isBacked) {
        try {
            Class<?> instance = Class.forName(tag); //Trỏ vào 1 fragment class
            BaseFragment<?, ?> fragment = (BaseFragment<?, ?>) instance.newInstance();

            fragment.setCallBack(this);
            fragment.setmData(data);

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            if (isBacked) {
                fragmentTransaction.addToBackStack(null); // go back to previous screen
            }
            fragmentTransaction.replace(R.id.fr_container, fragment, tag).commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
