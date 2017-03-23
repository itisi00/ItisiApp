package com.itisi.itisiapp.di.modeule;

import android.app.Activity;

import com.itisi.itisiapp.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * author: itisi---
 * created by Administrator on 2017/3/23.
 * desc:
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity(){
        return  mActivity;
    }
}
