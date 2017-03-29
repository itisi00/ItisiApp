package com.itisi.itisiapp.mvp.ui.select;

import com.itisi.itisiapp.mvp.model.net.RetrofitFactory;
import com.itisi.itisiapp.mvp.ui.base.RxPresenter;

import javax.inject.Inject;

/**
 * author: itisi---
 * created by Administrator on 2017/3/29.
 * desc:
 */

public class SelectPresenter  extends RxPresenter<SelectContract.View> implements SelectContract.Presenter  {
    private RetrofitFactory mRetrofitFactory;

    @Inject
    public SelectPresenter(RetrofitFactory retrofitFactory) {
        mRetrofitFactory = retrofitFactory;
    }

    @Override
    public void getData() {

    }
}
