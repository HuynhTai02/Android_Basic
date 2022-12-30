package com.tai.demofragment.view.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.tai.demofragment.R;
import com.tai.demofragment.databinding.FragmentListBinding;

public class ListFragment extends BaseFragment<FragmentListBinding> {
    public static final String TAG = ListFragment.class.getName();
    public static final String KEY_UPDATE_ICON_LIST = "KEY_UPDATE_ICON_LIST";

    @Override
    protected FragmentListBinding initViewBinding(LayoutInflater inflater) {
        return FragmentListBinding.inflate(inflater);
    }

    @Override
    protected void initViews() {
        callBack.callBack(KEY_UPDATE_ICON_LIST, null);
        binding.ivFrog.setOnClickListener(this);
        binding.ivPanda.setOnClickListener(this);
        binding.ivPug.setOnClickListener(this);
    }

    @Override
    protected void clickView(View v) {
        if (v.getId() == R.id.iv_frog) {
            showDetailAnimal(R.drawable.ic_frog, "Frog");
        } else if (v.getId() == R.id.iv_panda) {
            showDetailAnimal(R.drawable.ic_panda, "Panda");
        } else if (v.getId() == R.id.iv_pug) {
            showDetailAnimal(R.drawable.ic_pug, "Pug");
        }
    }

    private void showDetailAnimal(int drawableId, String name) {
        callBack.showFragment(DetailFragment.TAG, new Object[]{drawableId, name}, true);
    }
}
