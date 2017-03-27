package com.itisi.itisiapp.mvp.ui.main.leisure;

import com.itisi.itisiapp.mvp.model.net.RetrofitFactory;
import com.itisi.itisiapp.mvp.ui.base.RxPresenter;

import javax.inject.Inject;

/**
 * author: itisi---
 * created by Administrator on 2017/3/27.
 * desc:
 */

public class LeisurePresenter extends RxPresenter<LeisureContract.View> implements LeisureContract.Presenter {

    private RetrofitFactory mRetrofitFactory;

    @Inject
    public LeisurePresenter(RetrofitFactory retrofitFactory) {
        mRetrofitFactory = retrofitFactory;
    }

    @Override
    public void loadData() {

    }
}
