package com.itisi.itisiapp.mvp.ui.scenic;

import com.itisi.itisiapp.mvp.model.net.RetrofitFactory;
import com.itisi.itisiapp.mvp.ui.base.RxPresenter;

import javax.inject.Inject;

/**
 * author: itisi---
 * created by Administrator on 2017/4/7.
 * desc:
 */

public class ScenicPresenter extends RxPresenter<ScenicContract.View> implements ScenicContract.Presenter {

    private RetrofitFactory mRetrofitFactory;

    @Inject
    public ScenicPresenter(RetrofitFactory retrofitFactory) {
        mRetrofitFactory = retrofitFactory;
    }


    @Override
    public void getData() {
        mView.showError("");

    }
}
