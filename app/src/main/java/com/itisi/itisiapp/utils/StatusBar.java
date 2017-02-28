package com.itisi.itisiapp.utils;

import android.content.Context;

/**
 * StatusBar 工具类
 * Created by Administrator on 2017/2/27.
 */
public class StatusBar {
    /**
     * 获取状态来的高度 -1为获取失败
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context){
        /**
         * 获取状态栏高度——方法1
         * */
        int statusBarHeight1 = -1;
        //获取status_bar_height资源的ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight1;
    }
}
