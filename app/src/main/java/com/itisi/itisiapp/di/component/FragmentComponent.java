package com.itisi.itisiapp.di.component;

import android.app.Activity;

import com.itisi.itisiapp.di.modeule.FragmentModule;
import com.itisi.itisiapp.di.scope.PerFragment;
import com.itisi.itisiapp.mvp.ui.main.chat.ChatFragment;
import com.itisi.itisiapp.mvp.ui.main.guizhou.GuiZhouFragment;
import com.itisi.itisiapp.mvp.ui.main.leisure.LeisureFragment;
import com.itisi.itisiapp.mvp.ui.main.news.NewsFragment;
import com.itisi.itisiapp.mvp.ui.main.test.Fragment_Main1;
import com.itisi.itisiapp.mvp.ui.main.home.HomeFragment;

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
    void inject(HomeFragment homeFragment );
    void inject(NewsFragment homeFragment );
    void inject(LeisureFragment homeFragment );
    void inject(GuiZhouFragment homeFragment );
    void inject(ChatFragment chatFragment  );

    //inject 其他 framgent
}
