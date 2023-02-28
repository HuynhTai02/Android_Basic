package com.tai.learnmvvm.view.act;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

public abstract class BaseAct<V extends ViewBinding, M extends ViewModel> extends AppCompatActivity implements View.OnClickListener {
    protected V binding;
    protected M viewModel;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = initViewBinding();
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(getClassVM());

        initViews();
    }

    protected abstract void initViews();

    protected abstract Class<M> getClassVM();


    protected abstract V initViewBinding();

    @Override
    public final void onClick(View v) {
        v.setAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in));
    }

    protected void showNotify(String msg){
        Toast.makeText(this, "msg", Toast.LENGTH_SHORT).show();
    }

    protected void showNotify(int msgID){
        Toast.makeText(this, "msgID", Toast.LENGTH_SHORT).show();
    }
}
