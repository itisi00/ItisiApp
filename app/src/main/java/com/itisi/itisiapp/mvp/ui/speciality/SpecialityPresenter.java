package com.itisi.itisiapp.mvp.ui.speciality;

import com.itisi.itisiapp.mvp.model.net.RetrofitFactory;
import com.itisi.itisiapp.mvp.ui.base.RxPresenter;

import javax.inject.Inject;

/**
 * author: itisi---
 * created by Administrator on 2017/4/7.
 * desc:
 */

public class SpecialityPresenter extends RxPresenter<SpecialityContract.View> implements SpecialityContract.Presenter {

    private RetrofitFactory mRetrofitFactory;

    @Inject
    public SpecialityPresenter(RetrofitFactory retrofitFactory) {
        mRetrofitFactory = retrofitFactory;
    }


    @Override
    public void getData() {
        mView.showError("");

    }
}
