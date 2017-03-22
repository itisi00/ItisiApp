package com.itisi.itisiapp.api;

/**
 * Created by Administrator on 2017/3/20.
 */

public interface APPURL {
    //**************************干货***********************************//
    /**
     * 基地址----干货
     * 干货集中营 http://gank.io/api
     * http://gank.io/api/data/福利/10/4
     *                         type/pageSize/pageIndex
     * type: Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
     *
     */
    String BaseURL_GANK="http://gank.io/api/data/";

    /**
     * 根据干货类型加载分页加载数据
     *  type: Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
     */
    String gank="{type}/{count}/{index}";

    // 干货下面的一系列地址
    //*******************api.baidu.com stroe 什么的****************************//
    String BaseURL_BaiDu="";
    // 百度下面的一系列地址
    //*******************我们自己服务器的****************************//
    String BaseURL_My="http://itisi.com/api/";
}
