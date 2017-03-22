package com.itisi.itisiapp.mvp.model.net;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.itisi.itisiapp.mvp.model.entity.BaseGankEntity;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;

/**
 * author: itisi---
 * created by Administrator on 2017/3/22.
 * desc:
 */

public abstract class BaseObserver<T> implements Observer<BaseGankEntity<T>> {
    private Context mContext;

    public BaseObserver(Context context) {
        mContext = context;
    }

    @Override
    public void onNext(BaseGankEntity<T> tBaseGankEntity) {
        if (!tBaseGankEntity.isError()) {
            T t=tBaseGankEntity.getData();
            onHandleSuccess(t);
        } else {
            onHandleError(TextUtils.isEmpty(tBaseGankEntity.getMsg())?"没有异常消息,但有异常":tBaseGankEntity.getMsg());//事实上干货这个api 没有msg这个字段
        }
    }

    @Override
    public void onError(Throwable e) {
        Logger.e(e, "onError");

    }

    @Override
    public void onComplete() {
        Logger.i("onComplete");
    }

    /**
     * 成功回调
     * @param t
     */
    protected abstract void onHandleSuccess(T t);


    /**
     * 错误回调
     * @param msg
     */
    protected void onHandleError(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }


}
