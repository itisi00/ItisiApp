package com.itisi.itisiapp.app;

import java.io.File;

/**
 * 常量
 * Created by Administrator on 2017/2/23.
 */
public interface Constants {

    /**
     * 网络连接超时 读取超时 时长 30s
     */
    int CONNECT_TIMEOUT = 20;
    int READ_TIMEOUT = 20;
    int WRITE_TIMEOUT = 30;
    String PATH_DATA = ItisiApp.getInstance().getCacheDir().getAbsolutePath()+ File.separator+"data";
    String PATH_CACHE =  PATH_DATA + "/NetCache";



}
