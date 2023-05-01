package com.huynhngoctai.testgooglemap;

import android.app.Application;
//Mục đích của việc định nghĩa lớp App này là để tạo ra một đối tượng App duy nhất,
// giúp cho việc truy cập và quản lý các tài nguyên ứng dụng được dễ dàng hơn.
public class App extends Application {
    private static App instance;
    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
