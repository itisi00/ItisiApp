package com.itisi.itisiapp.mvp.rx.annotation;

import com.itisi.itisiapp.mvp.rx.event.EventThread;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.reactivex.annotations.NonNull;

/**
 * author: itisi---
 * created by Administrator on 2017/3/21.
 * desc:RxBus 注解接口 抄的 暂时不知道 怎么用 自定义注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Subscribe {
    @NonNull int tag();
    EventThread thread() default EventThread.MAIN_THREAD;
}
