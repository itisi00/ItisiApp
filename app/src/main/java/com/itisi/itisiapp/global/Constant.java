package com.itisi.itisiapp.global;

/**
 * 常量
 * Created by Administrator on 2017/2/23.
 */
public interface Constant {
    /**
     * 请求地址
     */
    interface URL{
        //**************************干货***********************************//
        /**
         * 基地址----干货
         */
        String BaseURL_GANK="http://xxx.com/api/";
        // 干货下面的一系列地址



        //*******************api.baidu.com stroe 什么的****************************//
        String BaseURL_BaiDu="";
        // 百度下面的一系列地址

        //*******************我们自己服务器的****************************//
        String BaseURL_My="http://itisi.com/api/";

    }

    /**
     *
     */
    interface RESUESTCODE{

        int PERMISSIONS_REQUEST_READ_CONTACTS=1;
    }
}
