package com.itisi.itisiapp.mvp.ui.agenda;

import com.itisi.itisiapp.mvp.model.net.RetrofitFactory;
import com.itisi.itisiapp.mvp.ui.base.RxPresenter;

import javax.inject.Inject;

/**
 * author: itisi---
 * created by Administrator on 2017/3/29.
 * desc:
 */

public class AgendaPresenter  extends RxPresenter<AgendaContract.View> implements AgendaContract.Presenter {
    private RetrofitFactory mRetrofitFactory;

    @Inject
    public AgendaPresenter(RetrofitFactory retrofitFactory) {
        mRetrofitFactory = retrofitFactory;
    }

    @Override
    public void getData() {

    }
}
