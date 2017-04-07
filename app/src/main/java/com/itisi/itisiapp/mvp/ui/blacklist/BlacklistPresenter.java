package com.itisi.itisiapp.mvp.ui.blacklist;

import com.itisi.itisiapp.mvp.model.net.RetrofitFactory;
import com.itisi.itisiapp.mvp.ui.base.RxPresenter;

import javax.inject.Inject;

/**
 * author: itisi---
 * created by Administrator on 2017/4/7.
 * desc:
 */

public class BlacklistPresenter extends RxPresenter<BlacklistContract.View> implements BlacklistContract.Presenter {

    private RetrofitFactory mRetrofitFactory;

    @Inject
    public BlacklistPresenter(RetrofitFactory retrofitFactory) {
        mRetrofitFactory = retrofitFactory;
    }


    @Override
    public void getData() {
        mView.showError("");

    }
}
