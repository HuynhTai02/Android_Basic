package com.tai.learnmvvm.view.act;

import android.text.TextWatcher;

public interface TextChangeAdapter extends TextWatcher {
    default void beforeTextChanged(CharSequence s, int start, int count, int after) { }
    default void onTextChanged(CharSequence s, int start, int before, int count) { }
}
