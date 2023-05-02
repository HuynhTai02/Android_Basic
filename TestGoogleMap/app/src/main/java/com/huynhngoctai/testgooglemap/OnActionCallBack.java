package com.huynhngoctai.testgooglemap;

import com.google.android.gms.maps.model.LatLng;

public interface OnActionCallBack {
    void showAlertDialog(String distance, LatLng start, LatLng end);
}
