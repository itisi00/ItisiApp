package com.itisi.itisiapp.mvp.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * author: itisi---
 * created by Administrator on 2017/3/22.
 * desc:
 */

public class BaseGankEntity<E> {
    private boolean error;
    /**
     * 这个api没有隔着对象
     */
    @SerializedName("msg")
    private String msg;
    @SerializedName("results")
    private E data;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public E getData() {
        return data;
    }
    public void setData(E data) {
        this.data = data;
    }

}
