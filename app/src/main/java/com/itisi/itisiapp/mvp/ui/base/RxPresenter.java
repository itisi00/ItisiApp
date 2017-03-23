package com.itisi.itisiapp.mvp.ui.base;


import com.itisi.itisiapp.mvp.rx.RxBus;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * author: itisi---
 * created by Administrator on 2017/3/23.
 * desc: 基于Rx的Prsenter的封装,控制订阅的生命周期
 */
public class RxPresenter<V extends BaseView> implements BasePresenter<V> {

    protected  V mView;
    protected CompositeDisposable mDisposable;

    @Override
    public void attachView(V view) {
        mView=view;
    }

    @Override
    public void detachView() {
        mView=null;
        unSubscribe();
    }

    /**
     * 取消订阅
     */
    private void unSubscribe() {
        if (mDisposable!=null){
            mDisposable.dispose();
        }
    }

    /**
     * 添加 可释放对象
     * @param disposable
     */
    protected void addSubscribe(Disposable disposable){
        if (mDisposable==null){
            mDisposable=new CompositeDisposable();
        }
        mDisposable.add(disposable);
    }

    /**
     * 未完成 ---
     * @param eventType
     * @param <U>
     */
    protected <U> void addRxBusSubscribe(Class<U> eventType, Consumer<U> consumer){
        if (mDisposable==null){
            mDisposable=new CompositeDisposable();
        }
        mDisposable.add(RxBus.getInstance().toDefaultObservable(eventType,consumer));
    }


}
