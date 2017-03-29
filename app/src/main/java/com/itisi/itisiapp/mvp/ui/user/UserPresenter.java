package com.itisi.itisiapp.mvp.ui.user;

import com.itisi.itisiapp.mvp.model.net.RetrofitFactory;
import com.itisi.itisiapp.mvp.ui.base.RxPresenter;

import javax.inject.Inject;

/**
 * author: itisi---
 * created by Administrator on 2017/3/29.
 * desc:
 */

public class UserPresenter extends RxPresenter<UserContract.View> implements UserContract.Presenter  {
    private RetrofitFactory mRetrofitFactory;

    @Inject
    public UserPresenter(RetrofitFactory retrofitFactory) {
        mRetrofitFactory = retrofitFactory;
    }


    @Override
    public void getData() {

    }
}
