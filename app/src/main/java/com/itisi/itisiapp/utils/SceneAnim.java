package com.itisi.itisiapp.utils;

import android.app.Activity;

import com.itisi.itisiapp.R;

/**
 * author: itisi---
 * created by Administrator on 2017/3/29.
 * desc: 转场动画 老式的
 */
public class SceneAnim {
    /**
     * 打开一个新的界面 缩放 和透明度动画
     * @param activity
     */
    public static void openActivityByScaleAlpha(Activity activity){
        activity.overridePendingTransition(R.anim.zoom,0);
    }
    /**
     * 关闭一个新的界面 缩放 和透明度动画
     * @param activity
     */
    public static void closeActivityByScaleAlpha(Activity activity){
        activity.overridePendingTransition(0,R.anim.scale_out);
    }

}
