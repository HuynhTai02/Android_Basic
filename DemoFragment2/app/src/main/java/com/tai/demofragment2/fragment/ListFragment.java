package com.tai.demofragment2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tai.demofragment2.OnMainCallback;
import com.tai.demofragment2.R;
import com.tai.demofragment2.databinding.FragmentListBinding;


public class ListFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = ListFragment.class.getName();
    public static final String KEY_UPDATE_ICON_LIST = "KEY_UPDATE_ICON_LIST";
    public static final String KEY_SHOW_DETAIL = "KEY_SHOW_DETAIL";


    private FragmentListBinding binding;
    private Context context;
    private OnMainCallback callback;

    public void setCallback(OnMainCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //binding = FragmentListBinding.inflate(inflater, container,false);
        binding = FragmentListBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    protected void initViews() {
        callback.callBack(KEY_UPDATE_ICON_LIST, null);

        binding.ivFrog.setOnClickListener(this);
        binding.ivPanda.setOnClickListener(this);
        binding.ivPug.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Hiệu ứng khi click
        v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
        if (v.getId() == R.id.iv_frog) {
            showDetailAmimal(R.drawable.ic_frog, "FROG");
        } else if (v.getId() == R.id.iv_panda) {
            showDetailAmimal(R.drawable.ic_panda, "PANDA");
        } else if (v.getId() == R.id.iv_pug) {
            showDetailAmimal(R.drawable.ic_pug, "PUG");
        }
    }

    private void showDetailAmimal(int drawableId, String name) {
        callback.callBack(KEY_SHOW_DETAIL, new Object[]{drawableId, name});
    }
}
