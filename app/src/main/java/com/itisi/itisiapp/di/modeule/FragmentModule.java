package com.itisi.itisiapp.di.modeule;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.itisi.itisiapp.di.scope.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * author: itisi---
 * created by Administrator on 2017/3/23.
 * desc:
 */
@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @PerFragment
    public Activity provideActivity(){
        return mFragment.getActivity();
    }

}
