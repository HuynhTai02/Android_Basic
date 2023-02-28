package com.tai.learnmvvm.view.act;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.tai.learnmvvm.databinding.M001MainActBinding;
import com.tai.learnmvvm.viewmodel.ActM001MainVM;

public class ActM001MainOptimal extends BaseAct<M001MainActBinding, ActM001MainVM> {

    @Override
    protected Class<ActM001MainVM> getClassVM() {
        return ActM001MainVM.class;
    }

    @Override
    protected M001MainActBinding initViewBinding() {
        return M001MainActBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initViews() {
        //Ánh xạ ViewModel
        viewModel = new ViewModelProvider(this).get(ActM001MainVM.class);

        //Bước 2: Cho View observe timeData trong ViewModel
        //Bước 4: Khi dữ liệu được thay đổi thì dữ liệu sẽ cập nhật về onChanged
        //Để trả dữ liệu về ViewModel
        viewModel.getTimeData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer i) {
                binding.tvTime.setText(String.format("%s", i));
                binding.progressTime.setProgress(i);
            }
        });

        binding.progressTime.setMax(10);
        binding.btStart.setOnClickListener(v -> startCountDown());
    }

    private void startCountDown() {
        viewModel.startCountDown();
    }
}