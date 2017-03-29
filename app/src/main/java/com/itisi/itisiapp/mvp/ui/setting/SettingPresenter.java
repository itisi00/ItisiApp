package com.itisi.itisiapp.mvp.ui.setting;

import com.itisi.itisiapp.mvp.model.net.RetrofitFactory;
import com.itisi.itisiapp.mvp.ui.base.RxPresenter;

import javax.inject.Inject;

/**
 * author: itisi---
 * created by Administrator on 2017/3/29.
 * desc:
 */

public class SettingPresenter  extends RxPresenter<SettingContract.View> implements SettingContract.Presenter  {
    private RetrofitFactory mRetrofitFactory;

    @Inject
    public SettingPresenter(RetrofitFactory retrofitFactory) {
        mRetrofitFactory = retrofitFactory;
    }

    @Override
    public void getData() {

    }
}
