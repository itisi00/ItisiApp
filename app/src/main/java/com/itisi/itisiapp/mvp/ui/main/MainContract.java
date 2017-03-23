package com.itisi.itisiapp.mvp.ui.main;

import com.itisi.itisiapp.mvp.ui.base.BasePresenter;
import com.itisi.itisiapp.mvp.ui.base.BaseView;

/**
 * author: itisi---
 * created by Administrator on 2017/3/23.
 * desc:
 */

public interface MainContract {
    interface View extends BaseView{
        //定义自己特有的方法



    }
    interface Presenter extends BasePresenter<View>{
        //定义自己特有的方法
        void getData();

    }
}
