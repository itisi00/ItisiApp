package com.itisi.itisiapp.mvp.ui.about;

import com.itisi.itisiapp.mvp.model.net.RetrofitFactory;
import com.itisi.itisiapp.mvp.ui.base.RxPresenter;

import javax.inject.Inject;

/**
 * author: itisi---
 * created by Administrator on 2017/3/29.
 * desc:
 */

public class AboutPresenter extends RxPresenter<AboutContrat.View> implements AboutContrat.Presenter   {
    private RetrofitFactory mRetrofitFactory;

    @Inject
    public AboutPresenter(RetrofitFactory retrofitFactory) {
        mRetrofitFactory = retrofitFactory;
    }
    @Override
    public void getData() {

    }
}
