package com.itisi.itisiapp.mvp.rx;


import com.itisi.itisiapp.mvp.rx.annotation.Subscribe;
import com.itisi.itisiapp.mvp.rx.annotation.UseRxBus;
import com.itisi.itisiapp.mvp.rx.event.EventThread;
import com.itisi.itisiapp.mvp.rx.pojo.Msg;
import com.orhanobut.logger.Logger;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * author: itisi---
 * created by Administrator on 2017/3/21.
 * desc: 事件总线 提供订阅 发布功能
 */
public class RxBus {
    //TAG默认值 事件标记
    public static final int TAG_DEFAULT = -1000;
    public static final int TAG_UPDATE = -1010;
    public static final int TAG_CHANGE = -1020;
    public static final int TAG_OTHER = -1030;
    public static final int TAG_ERROR = -1090;
    private static RxBus instance;
    /**
     * tagClass
     */
    private static Map<Class, Integer> tag4Class = new HashMap<>();
    /**
     * 发布者
     */
    private Subject bus;
    /**
     * 添加序列
     * 根据object 生成唯一 id
     */
    private Integer tag = -1000;
    /**
     * 存放订阅者信息
     */
    private  Map<Object, CompositeDisposable> subscriptions = new HashMap<>();
    /**
     * 单列嘛  先改为私有的 ===不能私有化?
     * PublishSubject 创建一个可以在订阅之后把数据传输给订阅者Subject
     * SerializedSubject 序列化Subject为线程安全的Subject RxJava2 暂无
     */
    private RxBus() {
        bus = PublishSubject.create().toSerialized();
    }
    public static RxBus getInstance() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }
    /**
     * 发送消息 默认事件
     *
     * @param obj 需要携带的消息内容
     */
    public void post(@NonNull Object obj) {
        post(TAG_DEFAULT, obj);
    }
    /**
     * 指定事件标记 发送事件
     *
     * @param tag 事件标记
     * @param obj 需要携带的消息内容
     */
    public void post(@NonNull int tag, @NonNull Object obj) {
        bus.onNext(new Msg(tag, obj));
    }
    /**
     * 订阅事件
     *
     * @return
     */
    private Observable<Object> toObservable() {
        return toObservable(Object.class);
    }
    /**
     * 订阅事件
     *
     * @param eventType 事件类型? 还是事件源?
     * @param <T>
     * @return
     */
    private <T> Observable<T> toObservable(Class<T> eventType) {
        return toObservable(TAG_DEFAULT, eventType);
    }
    /**
     * 订阅事件
     *
     * @param tag       事件标志
     * @param eventType 事件类型? 还是事件源?
     * @param <T>
     * @return
     */
    private <T> Observable<T> toObservable(final int tag, Class<T> eventType) {
        return bus.ofType(Msg.class)//判断接受事件类型
                .filter(new Predicate<Msg>() {
                    @Override
                    public boolean test(@NonNull Msg msg) throws Exception {
                        return msg.tag == tag;
                    }
                })
                .map(new Function<Msg, Object>() {
                    @Override
                    public Object apply(@NonNull Msg msg) throws Exception {
                        return msg.object;
                    }
                })
                .cast(eventType);
    }
    /**
     * 判断是否需要订阅,如果需要订阅那么自动控制什么周期
     *
     * @param object
     */
    public void init(@NonNull final Object object) {
        Flowable.just(object)
                .map(new Function<Object, UseRxBus>() {
                    @Override
                    public UseRxBus apply(@NonNull Object o) throws Exception {
                        return o.getClass().getAnnotation(UseRxBus.class);
                    }
                })
                .filter(new Predicate<UseRxBus>() {
                    @Override
                    public boolean test(@NonNull UseRxBus o) throws Exception {
                        return o != null;
                    }
                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        addTag4Class(object.getClass()); //这里根本不是传递进来的 o 而是:
                        //如果能走到这里 则说明 原始的object是满足要求的!!! 所以  害死人了
                        register(object);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();

                        Logger.e(throwable, "订阅事件,初始化方法出错,也不算错,只是调用了rxbus.init方法,但没有加注解而已@UseRxBus ");
                    }
                });
    }
    /**
     * 订阅者注册
     *
     * @param subscriber 订阅者
     */
    private void register(@NonNull final Object subscriber) {
        Flowable.just(subscriber)
                .filter(new Predicate<Object>() {
                    @Override
                    public boolean test(@NonNull Object o) throws Exception {
                        return subscriptions.get(o) == null;//订阅者没有在序列中
                    }
                })
                .flatMap(new Function<Object, Publisher<Method>>() {

                    @Override
                    public Publisher<Method> apply(@NonNull Object o) throws Exception {
                        return Flowable.fromArray(o.getClass().getDeclaredMethods());
                    }
                })
                .map(new Function<Method, Method>() {
                    @Override
                    public Method apply(@NonNull Method method) throws Exception {
                        method.setAccessible(true);
                        return method;
                    }
                })
                .filter(new Predicate<Method>() {
                    @Override
                    public boolean test(@NonNull Method method) throws Exception {
                        return method.isAnnotationPresent(Subscribe.class);
                    }
                })
                .subscribe(new Consumer<Method>() {
                    @Override
                    public void accept(@NonNull Method method) throws Exception {
                        addSubscription(method, subscriber);
                    }
                });
    }
    /**
     * 添加订阅
     *
     * @param m          方法
     * @param subscriber 订阅者
     */
    private void addSubscription(final Method m, final Object subscriber) {
        //获取方法内参数
        Class[] parameterType = m.getParameterTypes();
        //只获取第一个方法参数 否则默认为Object
        Class cla = Object.class;
        if (parameterType.length > 1) {
            cla = parameterType[0];
        }
        //获取注解
        Subscribe sub = m.getAnnotation(Subscribe.class);
        //订阅事件
        Disposable disposable = toObservable(getTag(subscriber.getClass(), sub.tag()), cla)
                .observeOn(EventThread.getScheduler(sub.thread()))
                .subscribe(new Consumer() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        m.invoke(subscriber, o);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        Logger.e(throwable, "订阅事件异常");
                    }
                });
        //放进map集合
        putSubscriptionsData(subscriber, disposable);
    }
    /**
     * 添加订阅者到map中  用来取消订阅
     *
     * @param subscriber 订阅者
     * @param disposable 订阅者 的 Subscription
     */
    private void putSubscriptionsData(Object subscriber, Disposable disposable) {
        CompositeDisposable subs = subscriptions.get(subscriber);
        if (subs == null) {
            subs = new CompositeDisposable();
        }
        subs.add(disposable);
        subscriptions.put(subscriber, subs);
    }
    private void addTag4Class(Class cla) {
        tag4Class.put(cla, tag);
        tag--;
    }
    /**
     * tag值 使用RxBus.getInstance().getTag(class,value)获取
     * 使用getTag主要用于后期维护方便,可以解释找到发布事情的对应处理
     *
     * @param cla   RxBus事件处理的类
     * @param value 事件处理的tag
     * @return tag
     */
    public int getTag(Class cla, int value) {
        return tag4Class.get(cla).intValue() + value;
    }
    /**
     * 接触订阅者
     *
     * @param subscriber
     */

    public void unRegister(final Object subscriber) {
        Flowable.just(subscriber)
                .filter(new Predicate<Object>() {
                    @Override
                    public boolean test(@NonNull Object o) throws Exception {
                        return o != null;
                    }
                })
                .map(new Function<Object, CompositeDisposable>() {

                    @Override
                    public CompositeDisposable apply(@NonNull Object o) throws Exception {
                        return subscriptions.get(o);
                    }
                })
                .filter(new Predicate<CompositeDisposable>() {

                    @Override
                    public boolean test(@NonNull CompositeDisposable compositeDisposable) throws Exception {
                        return compositeDisposable != null;
                    }
                })
                .subscribeWith(new Subscriber<CompositeDisposable>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(CompositeDisposable compositeDisposable) {
                        compositeDisposable.dispose();
                        subscriptions.remove(subscriber);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
