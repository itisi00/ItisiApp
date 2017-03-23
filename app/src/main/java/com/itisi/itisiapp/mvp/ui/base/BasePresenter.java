package com.itisi.itisiapp.mvp.ui.base;

/**
 * author: itisi---
 * created by Administrator on 2017/3/23.
 * desc:
 */

public interface BasePresenter<V extends BaseView> {
    void attachView(V view);
    void detachView();
}
