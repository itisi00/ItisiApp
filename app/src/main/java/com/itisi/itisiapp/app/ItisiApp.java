package com.itisi.itisiapp.app;

import android.app.Application;

import com.itisi.itisiapp.di.component.AppComponent;
import com.itisi.itisiapp.di.component.DaggerAppComponent;
import com.itisi.itisiapp.di.modeule.AppModule;

/**
 * 自定义Application 类
 * Created by Administrator on 2017/2/23.
 */
public class ItisiApp extends Application {

    private static ItisiApp instance;
    AppComponent mAppComponent;

    public static ItisiApp getInstance(){
       return instance;
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        if (mAppComponent==null){
            mAppComponent= DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        }

    }

}
