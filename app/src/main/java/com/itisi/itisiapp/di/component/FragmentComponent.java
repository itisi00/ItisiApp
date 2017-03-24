package com.itisi.itisiapp.di.component;

import android.app.Activity;

import com.itisi.itisiapp.di.modeule.FragmentModule;
import com.itisi.itisiapp.di.scope.PerFragment;
import com.itisi.itisiapp.mvp.ui.main.Fragment_Main1;

import dagger.Component;

/**
 * author: itisi---
 * created by Administrator on 2017/3/24.
 * desc:
 */
@PerFragment
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();
    void inject(Fragment_Main1 fragment_main1);

    //inject 其他 framgent
}
