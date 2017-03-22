package com.itisi.itisiapp.mvp.ui.base;

import android.os.Bundle;

import com.itisi.itisiapp.mvp.rx.RxBus;

/**
 * 本类绑定了 RxBus 初始化事件
 * 如果Activity需要订阅事件 则可继承这个方法
 * 好处就是不用 每个类都写初始化方法
 *
 * 很多方法 都被我私有了 如果访问级别有错 再来调整
 */
public abstract class BaseRxBusActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.getInstance().init(this); //初始化事件总线
    }
}
