package com.itisi.itisiapp.mvp.rx.pojo;

/**
 * author: itisi---
 * created by Administrator on 2017/3/21.
 * desc:事件总线 消息对象
 */
public class Msg {
    /**
     * 事件标记
     */
    public int tag;
    /**
     * 携带的消息内容
     */
    public Object object;

    public Msg(int tag, Object object) {
        this.tag = tag;
        this.object = object;
    }
}
