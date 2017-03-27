package com.itisi.itisiapp.di.component;

import android.app.Activity;

import com.itisi.itisiapp.di.modeule.ActivityModule;
import com.itisi.itisiapp.di.scope.PerActivity;
import com.itisi.itisiapp.mvp.ui.main.test.Test2Activity;

import dagger.Component;

/**
 * author: itisi---
 * created by Administrator on 2017/3/23.
 * desc:
 */
@PerActivity
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();
    void inject(Test2Activity activity);
//    void inject(TabAndViewpagerActivity activity);

    //注入其他activity
}
