package com.itisi.itisiapp.mvp.ui.account;

import com.itisi.itisiapp.mvp.model.net.RetrofitFactory;
import com.itisi.itisiapp.mvp.ui.base.RxPresenter;

import javax.inject.Inject;

/**
 * author: itisi---
 * created by Administrator on 2017/3/29.
 * desc:
 */

public class AccountPresenter extends RxPresenter<AccountContract.View> implements AccountContract.Presenter  {
    private RetrofitFactory mRetrofitFactory;

    @Inject
    public AccountPresenter(RetrofitFactory retrofitFactory) {
        mRetrofitFactory = retrofitFactory;
    }
    @Override
    public void getData() {

    }
}
