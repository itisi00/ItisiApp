package com.itisi.itisiapp.mvp.ui.main.chat;

import com.itisi.itisiapp.mvp.model.net.RetrofitFactory;
import com.itisi.itisiapp.mvp.ui.base.RxPresenter;

import javax.inject.Inject;

/**
 * author: itisi---
 * created by Administrator on 2017/3/27.
 * desc:
 */

public class ChatPresenter extends RxPresenter<ChatContract.View> implements ChatContract.Presenter {

    private RetrofitFactory mRetrofitFactory;

    @Inject
    public ChatPresenter(RetrofitFactory retrofitFactory) {
        mRetrofitFactory = retrofitFactory;
    }

    @Override
    public void loadData() {

    }
}
