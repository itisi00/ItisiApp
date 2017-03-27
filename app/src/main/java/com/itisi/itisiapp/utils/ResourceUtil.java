package com.itisi.itisiapp.utils;

import android.content.Context;

/**
 * author: itisi---
 * created by Administrator on 2017/3/27.
 * desc: 读取资源文件中的字符串
 */

public class ResourceUtil {
    /**
     * 读取资源文件中的字符串
     * @param context 上下文
     * @param strResourceId 字符串的id
     * @return
     */
    public static String readStringSource(Context context, int strResourceId){
        return context.getResources().getString(strResourceId);
    }
}
