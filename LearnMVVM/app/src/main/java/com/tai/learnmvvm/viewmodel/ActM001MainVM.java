package com.tai.learnmvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ActM001MainVM extends ViewModel {
    private Thread th;
    //    private OnCountDownCallBack callBack;
    //Sử dụng Livedata thay thế CallBack giữa View và ViewModel
    //Bước 1: Khai báo MutableLiveData tại ViewModel
    private final MutableLiveData<Integer> timeData = new MutableLiveData<>(0);

//    public void setCallBack(OnCountDownCallBack callBack) {
//        this.callBack = callBack;
//    }
//
//    public void startCountDown() {
//        if (th == null || !th.isAlive()) {
//            th = new Thread(() -> {
//                for (int i = 10; i >= 0; i--) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                        return;
//                    }
//                    callBack.updateUI(i);
//
//                }
//            });
//            th.start();
//        }
//    }
//
//    public interface OnCountDownCallBack {
//        void updateUI(int i);
//    }

    public MutableLiveData<Integer> getTimeData() {
        return timeData;
    }

    public void startCountDown() {
        if (th == null || !th.isAlive()) {
            th = new Thread(() -> {
                for (int i = 10; i >= 0; i--) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                    //Bước 3: Cập nhật value mới cho timeData tại ViewModel
                    timeData.postValue(i);
                }
            });
            th.start();
        }
    }
}
