package com.itisi.itisiapp.mvp.ui.album;

import com.itisi.itisiapp.mvp.model.net.RetrofitFactory;
import com.itisi.itisiapp.mvp.ui.base.RxPresenter;

import javax.inject.Inject;

/**
 * author: itisi---
 * created by Administrator on 2017/3/29.
 * desc:
 */

public class AlbumPresenter  extends RxPresenter<AlbumContract.View> implements AlbumContract.Presenter  {
    private RetrofitFactory mRetrofitFactory;

    @Inject
    public AlbumPresenter(RetrofitFactory retrofitFactory) {
        mRetrofitFactory = retrofitFactory;
    }

    @Override
    public void getData() {

    }
}
