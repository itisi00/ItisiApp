package com.itisi.itisiapp.mvp.ui.rental;

import com.itisi.itisiapp.mvp.model.net.RetrofitFactory;
import com.itisi.itisiapp.mvp.ui.base.RxPresenter;

import javax.inject.Inject;

/**
 * author: itisi---
 * created by Administrator on 2017/3/29.
 * desc:
 */

public class RentalPresenter extends RxPresenter<RentalContract.View> implements RentalContract.Presenter  {
    private RetrofitFactory mRetrofitFactory;

    @Inject
    public RentalPresenter(RetrofitFactory retrofitFactory) {
        mRetrofitFactory = retrofitFactory;
    }


    @Override
    public void getData() {

    }
}
