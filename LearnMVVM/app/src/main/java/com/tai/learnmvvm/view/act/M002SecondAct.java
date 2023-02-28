package com.tai.learnmvvm.view.act;

import android.util.Log;

import androidx.lifecycle.Observer;

import com.tai.learnmvvm.databinding.M002SecondActBinding;
import com.tai.learnmvvm.viewmodel.M002SecondVM;

public class M002SecondAct extends BaseAct<M002SecondActBinding, M002SecondVM> {

    private static final String TAG = M002SecondAct.class.getName();

    @Override
    protected Class<M002SecondVM> getClassVM() {
        return M002SecondVM.class;
    }

    @Override
    protected M002SecondActBinding initViewBinding() {
        return M002SecondActBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initViews() {
        binding.edtFirstName.setText(viewModel.getFirstName().getValue());
        binding.edtLastName.setText(viewModel.getLastName().getValue());
        binding.scLang.setChecked(viewModel.getIsEN().getValue());

        binding.edtFirstName.addTextChangedListener((TextChangeAdapter) s -> updateInfo());
        binding.edtLastName.addTextChangedListener((TextChangeAdapter) s -> updateInfo());
        binding.scLang.setOnCheckedChangeListener((buttonView, isChecked) -> updateInfo());
        viewModel.fullNameLD().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String asn) {
                Log.i(TAG, "onChanged");
                binding.tvFullName.setText(asn);
            }
        });
    }

    private void updateInfo() {
        Log.i(TAG, "updateInfo");
        viewModel.setInfo(binding.edtFirstName.getText().toString(),
                binding.edtLastName.getText().toString(),
                binding.scLang.isChecked());
    }
}