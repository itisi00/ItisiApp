package com.itisi.itisiapp.di.modeule;

import android.content.Context;

import com.itisi.itisiapp.api.GankService;
import com.itisi.itisiapp.mvp.model.net.RetrofitFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * author: itisi---
 * created by Administrator on 2017/3/22.
 * desc:
 */
@Module
public class AppModule {
    private Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return mContext;
    }

    @Provides
    @Singleton
    RetrofitFactory provideRetrofitFactory(GankService gankService){
        return new RetrofitFactory(gankService);
    }

}
