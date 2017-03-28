package com.itisi.itisiapp.utils;

import com.itisi.itisiapp.app.ItisiApp;
import com.sdsmdg.tastytoast.TastyToast;

/**
 * author: itisi---
 * created by Administrator on 2017/3/28.
 * desc: 基于TastyToast 的toast再次封装
 */

public class ToastUtil {

    public static void Success(String msg){
       TastyToast.makeText(ItisiApp.getInstance(),msg,1,TastyToast.SUCCESS).show();
    }
    public static void Error(String msg){
        TastyToast.makeText(ItisiApp.getInstance(),msg,1,TastyToast.ERROR).show();
    }
    public static void Info(String msg){
        TastyToast.makeText(ItisiApp.getInstance(),msg,1,TastyToast.INFO).show();
    }
    public static void Warning(String msg){
        TastyToast.makeText(ItisiApp.getInstance(),msg,1,TastyToast.WARNING).show();
    }
    public static void Confusing(String msg){
        TastyToast.makeText(ItisiApp.getInstance(),msg,1,TastyToast.CONFUSING).show();
    }

}
