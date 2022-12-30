package com.tai.demofragment.view;

public interface OnMainCallBack {
    void callBack(String key, Object data);
    void showFragment(String tag, Object data, boolean isBacked);
}
