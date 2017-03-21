package com.itisi.itisiapp.mvp.rx.event;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: itisi---
 * created by Administrator on 2017/3/21.
 * desc: 指定事件线程
 */
public enum EventThread {
    /**
     * 主线程
     */
    MAIN_THREAD,
    /**
     * 新的线程
     */
    NEW_THREAD,
    /**
     * 读写线程
     */
    IO,
    /**
     * 计算线程
     */
    COMPUTATION,
    /**
     * 在当前线程中按照队列方式执行
     */
    TRAMPOLINE;
    public static Scheduler  getScheduler(EventThread threadMode){
        Scheduler  scheduler;
        switch (threadMode){
            case MAIN_THREAD:
                scheduler= AndroidSchedulers.mainThread();
                break;
            case NEW_THREAD:
                scheduler= Schedulers.newThread();
                break;
            case IO:
                scheduler=Schedulers.io();
                break;
            case COMPUTATION:
                scheduler= Schedulers.io();
                break;
            case TRAMPOLINE:
                scheduler=Schedulers.trampoline();
                break;
            default:
                scheduler= AndroidSchedulers.mainThread();
        }
        return scheduler;
    }

}
