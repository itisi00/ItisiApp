package com.itisi.itisiapp.di.component;

import android.app.Activity;

import com.itisi.itisiapp.di.modeule.ActivityModule;
import com.itisi.itisiapp.di.scope.PerActivity;
import com.itisi.itisiapp.mvp.ui.about.AboutActivity;
import com.itisi.itisiapp.mvp.ui.account.AccountActivity;
import com.itisi.itisiapp.mvp.ui.agenda.AgendaActivity;
import com.itisi.itisiapp.mvp.ui.album.AlbumActivity;
import com.itisi.itisiapp.mvp.ui.birthday.BirthdayActivity;
import com.itisi.itisiapp.mvp.ui.blacklist.BlacklistActivity;
import com.itisi.itisiapp.mvp.ui.collection.CollectionActivity;
import com.itisi.itisiapp.mvp.ui.footprint.FootprintActivity;
import com.itisi.itisiapp.mvp.ui.main.MainActivity;
import com.itisi.itisiapp.mvp.ui.main.test.Test2Activity;
import com.itisi.itisiapp.mvp.ui.nation.NationActivity;
import com.itisi.itisiapp.mvp.ui.read.ReadActivity;
import com.itisi.itisiapp.mvp.ui.recruit.RecruitActivity;
import com.itisi.itisiapp.mvp.ui.rental.RentalActivity;
import com.itisi.itisiapp.mvp.ui.scenic.ScenicActivity;
import com.itisi.itisiapp.mvp.ui.select.SelectActivity;
import com.itisi.itisiapp.mvp.ui.setting.SettingActivity;
import com.itisi.itisiapp.mvp.ui.speciality.SpecialityActivity;
import com.itisi.itisiapp.mvp.ui.user.UserActivity;

import dagger.Component;

/**
 * author: itisi---
 * created by Administrator on 2017/3/23.
 * desc:
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {
    Activity getActivity();

    //主页
    void inject(MainActivity activity);
    void inject(RecruitActivity activity);
    void inject(RentalActivity activity);
    void inject(ReadActivity activity);
    void inject(SelectActivity activity);

    //贵州页面
    void inject(NationActivity activity);
    void inject(ScenicActivity activity);
    void inject(SpecialityActivity activity);
    void inject(BlacklistActivity activity);


    //测试
    void inject(Test2Activity activity);

    //    void inject(TabAndViewpagerActivity activity);
    //左侧菜单中的
    void inject(AgendaActivity activity);
    void inject(BirthdayActivity activity);
    void inject(AccountActivity activity);
    void inject(FootprintActivity activity);
    void inject(AlbumActivity activity);
    void inject(CollectionActivity activity);
    void inject(UserActivity activity);
    void inject(AboutActivity activity);
    void inject(SettingActivity activity);




    //注入其他activity
}
