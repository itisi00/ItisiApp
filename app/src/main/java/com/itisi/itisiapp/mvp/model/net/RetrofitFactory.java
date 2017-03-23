package com.itisi.itisiapp.mvp.model.net;

import com.itisi.itisiapp.api.GankService;

/**
 * author: itisi---
 * created by Administrator on 2017/3/22.
 * desc:
 */

public class RetrofitFactory {
    private GankService mGankService;

    public RetrofitFactory(GankService gankService) {
        mGankService = gankService;
    }

    public GankService getGankService() {
        return mGankService;
    }

    public void setGankService(GankService gankService) {
        mGankService = gankService;
    }
}
