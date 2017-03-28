package com.itisi.itisiapp.mvp.ui.birthday;

import com.itisi.itisiapp.mvp.model.net.RetrofitFactory;
import com.itisi.itisiapp.mvp.ui.base.RxPresenter;

import javax.inject.Inject;

/**
 * author: itisi---
 * created by Administrator on 2017/3/28.
 * desc:
 */

public class BirthdayPresenter  extends RxPresenter<BirthdayContract.View> implements BirthdayContract.Presenter {

    private RetrofitFactory mRetrofitFactory;

    @Inject
    public BirthdayPresenter(RetrofitFactory retrofitFactory) {
        mRetrofitFactory = retrofitFactory;
    }


    @Override
    public void getData() {
        mView.showError("");

    }
}
