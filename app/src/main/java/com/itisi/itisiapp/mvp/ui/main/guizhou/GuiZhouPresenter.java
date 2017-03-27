package com.itisi.itisiapp.mvp.ui.main.guizhou;

import com.itisi.itisiapp.mvp.model.net.RetrofitFactory;
import com.itisi.itisiapp.mvp.ui.base.RxPresenter;

import javax.inject.Inject;

/**
 * author: itisi---
 * created by Administrator on 2017/3/27.
 * desc:
 */

public class GuiZhouPresenter extends RxPresenter<GuiZhouContract.View> implements GuiZhouContract.Presenter {

    private RetrofitFactory mRetrofitFactory;

    @Inject
    public GuiZhouPresenter(RetrofitFactory retrofitFactory) {
        mRetrofitFactory = retrofitFactory;
    }

    @Override
    public void loadData() {

    }
}
