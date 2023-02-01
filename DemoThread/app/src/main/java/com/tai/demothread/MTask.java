package com.tai.demothread;

import android.os.AsyncTask;

//MTASK là một cấu trúc tối ưu hóa AsyncTask 
//Có thể xử lý nhiều AsyncTask dễ dàng nhờ vào key.

public final class MTask extends AsyncTask<Object, Object, Boolean> {
    private final String key;
    private final OnCallBack callBack;

    public MTask(String key, OnCallBack callBack) {
        this.key = key;
        this.callBack = callBack;
    }

    public void requestUI(Object data) {
        publishProgress(data);
    }

    @Override
    protected void onPreExecute() {
        callBack.preExec(key);
    }

    @Override
    protected Boolean doInBackground(Object... params) {
        return (Boolean) callBack.execTask(key, params == null ? null : params[0], this);
    }

    @Override
    protected void onProgressUpdate(Object... data) {
        callBack.updateUI(key, data[0]);
    }

    @Override
    protected void onPostExecute(Boolean value) {
        callBack.completeTask(key, value);
    }

    public void start(Object data) {
        execute(data);
    }

    public void startAsync(Object data) {
        executeOnExecutor(THREAD_POOL_EXECUTOR, data);
    }

    public void stop() {
        cancel(true);
    }

    @Override
    protected void onCancelled() {
        callBack.cancelTask(key);
    }

    public interface OnCallBack {
        default void preExec(String key) {
            //do nothing
        }

        Object execTask(String key, Object param, MTask mtask);

        default void updateUI(String key, Object data) {
            //do nothing
        }

        default void completeTask(String key, Object data) {
            //do nothing
        }

        default void cancelTask(String key) {
            //do nothing
        }
    }
}
