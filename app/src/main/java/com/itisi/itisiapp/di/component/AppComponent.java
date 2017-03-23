package com.itisi.itisiapp.di.component;

import android.content.Context;

import com.itisi.itisiapp.di.modeule.ApiServiceModule;
import com.itisi.itisiapp.di.modeule.AppModule;
import com.itisi.itisiapp.mvp.model.net.RetrofitFactory;

import javax.inject.Singleton;

import dagger.Component;

/**
 * author: itisi---
 * created by Administrator on 2017/3/22.
 * desc:
 */
@Singleton
@Component(modules ={ AppModule.class, ApiServiceModule.class})
public interface AppComponent {
    Context getContext(); //提供app的 Context
    RetrofitFactory retrofitFactory(); //提供 网络请求类
    //提供数据库帮助类
//    void inject();
}
