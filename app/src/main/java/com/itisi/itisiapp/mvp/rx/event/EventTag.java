package com.itisi.itisiapp.mvp.rx.event;

/**
 * author: itisi---
 * created by Administrator on 2017/3/21.
 * desc:
 */
public enum EventTag {

    DEFAULT(0),
    SUCCESS(-100),
    FAILED(-300),
    UPDATE(-200),
    ERROR(-404);

    //字段
    private int value=0;
    //构造
    EventTag(int value){
        this.value=value;
    }

    public static int getTag(EventTag eventTag){
        int i=0;
        switch (eventTag){
            case DEFAULT:
                i=0;
                break;
            case SUCCESS:
                i=-100;
                break;
            case FAILED:
                i=-1;//搞不懂 为什么不是 -300  莫非思想很深邃????
                break;
            case UPDATE:
                i=-200;
                break;
            case ERROR:
                i=-400;
                break;
        }
        return i;
    }

}
