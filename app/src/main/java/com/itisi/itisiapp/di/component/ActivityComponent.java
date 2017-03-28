package com.itisi.itisiapp.di.component;

import android.app.Activity;

import com.itisi.itisiapp.di.modeule.ActivityModule;
import com.itisi.itisiapp.di.scope.PerActivity;
import com.itisi.itisiapp.mvp.ui.birthday.BirthdayActivity;
import com.itisi.itisiapp.mvp.ui.main.MainActivity;
import com.itisi.itisiapp.mvp.ui.main.test.Test2Activity;

import dagger.Component;

/**
 * author: itisi---
 * created by Administrator on 2017/3/23.
 * desc:
 */
@PerActivity
@Component(dependencies = AppComponent.class,modules = {ActivityModule.class})
public interface ActivityComponent {
    Activity getActivity();
    void inject(MainActivity activity);
    void inject(Test2Activity activity);
//    void inject(TabAndViewpagerActivity activity);
    void inject(BirthdayActivity activity);

    //注入其他activity
}
