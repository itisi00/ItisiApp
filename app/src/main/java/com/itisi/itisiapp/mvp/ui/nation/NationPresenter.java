package com.itisi.itisiapp.mvp.ui.nation;

import com.itisi.itisiapp.mvp.model.net.RetrofitFactory;
import com.itisi.itisiapp.mvp.ui.base.RxPresenter;

import javax.inject.Inject;

/**
 * author: itisi---
 * created by Administrator on 2017/4/7.
 * desc:
 */

public class NationPresenter extends RxPresenter<NationContract.View> implements NationContract.Presenter {

    private RetrofitFactory mRetrofitFactory;

    @Inject
    public NationPresenter(RetrofitFactory retrofitFactory) {
        mRetrofitFactory = retrofitFactory;
    }


    @Override
    public void getData() {
        mView.showError("");

    }
}
