package com.itisi.itisiapp.mvp.ui.read;

import com.itisi.itisiapp.mvp.model.net.RetrofitFactory;
import com.itisi.itisiapp.mvp.ui.base.RxPresenter;

import javax.inject.Inject;

/**
 * author: itisi---
 * created by Administrator on 2017/3/29.
 * desc:
 */

public class ReadPresenter  extends RxPresenter<ReadContract.View> implements ReadContract.Presenter  {
    private RetrofitFactory mRetrofitFactory;

    @Inject
    public ReadPresenter(RetrofitFactory retrofitFactory) {
        mRetrofitFactory = retrofitFactory;
    }

    @Override
    public void getData() {

    }
}
