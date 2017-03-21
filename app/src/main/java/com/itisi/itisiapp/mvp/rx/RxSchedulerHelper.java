package com.itisi.itisiapp.mvp.rx;

import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: itisi---
 * created by Administrator on 2017/3/21.
 * desc:RxJava 线程调度帮助类 以后再也不用 每次都指定生产线程 和消费线程了
 * 此处同意指定 本类默认生成线程是io 线程 消费线程是 main 线程
 */
public class RxSchedulerHelper {
    public static <T>ObservableTransformer<T,T>io_main(){
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(io.reactivex.Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
