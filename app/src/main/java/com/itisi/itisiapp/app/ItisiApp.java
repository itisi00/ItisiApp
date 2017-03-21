package com.itisi.itisiapp.app;

import android.app.Application;

import com.itisi.itisiapp.mvp.rx.RxBus;

/**
 * 自定义Application 类
 * Created by Administrator on 2017/2/23.
 */
public class ItisiApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RxBus.getInstance().init(this); //初始化事件总线
    }

}
