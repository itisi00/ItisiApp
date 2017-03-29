package com.itisi.itisiapp.mvp.ui.footprint;

import com.itisi.itisiapp.mvp.model.entity.GankFuLiEntity;
import com.itisi.itisiapp.mvp.ui.base.BasePresenter;
import com.itisi.itisiapp.mvp.ui.base.BaseView;

import java.util.List;

/**
 * author: itisi---
 * created by Administrator on 2017/3/29.
 * desc:
 */

public interface Footprintcontract {
    interface View extends BaseView {
        //定义自己特有的方法
        void showContent(List<GankFuLiEntity> list);

    }
    interface Presenter extends BasePresenter<View> {
        //定义自己特有的方法
        void getData();
    }
}
