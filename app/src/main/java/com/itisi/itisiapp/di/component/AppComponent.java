package com.itisi.itisiapp.di.component;

import android.content.Context;

import com.itisi.itisiapp.di.modeule.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * author: itisi---
 * created by Administrator on 2017/3/22.
 * desc:
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Context getContext();
//    void inject();
}
